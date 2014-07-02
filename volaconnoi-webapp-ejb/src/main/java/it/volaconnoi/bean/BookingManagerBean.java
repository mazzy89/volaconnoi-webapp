/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.BookingManagerBeanInterface;
import it.volaconnoi.logic.ReservationManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

/**
 *
 * @author Mazzy
 */
@Stateless
public class BookingManagerBean implements BookingManagerBeanInterface
{
    @EJB
    private UtilBeanInterface utilBean;
    @EJB
    private ReservationManagerBeanInterface reservationBean;
    
    @Inject
    @JMSConnectionFactory("jms/reservationProcessorQueueFactory")
    private JMSContext context;
    
    @Resource(mappedName = "jms/bookingProcessorQueueReceiver")
    private Queue bookingProcessorQueueReceiver;
    
    private JMSProducer messageProducer = null;
    private JMSConsumer messageConsumer = null;
        
    private Map<String,Object> map;
          
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String purchase(Route r, UserCredential u, int passengers, int  luggages, double price, int points)
    {  
        String id_reservation = null;
        Reservation reserv = reservationBean.addReservation(r, u, passengers, luggages, (float) price);
       
        try
        {            
            sendMessageToBookingProcessorBean(reserv, points); // invia il messaggio contenente la prenotazione e il numero di punti utilizzati                                   
            
            id_reservation = receiveMessageFromBookingProcessorBean();
        }
        catch(JMSException jmse)
        {
            return (id_reservation);
        }

        return id_reservation;
    }
    
    private void sendMessageToBookingProcessorBean(Object messageData, int points) throws JMSException
    {                                  
        messageProducer = context.createProducer();
        
        messageProducer.send(bookingProcessorQueueReceiver,createMessageForBookingProcessorBean(messageData, points));   
    }
    
    private Message createMessageForBookingProcessorBean(Object messageData, int points) throws JMSException
    {              
        ObjectMessage msg = context.createObjectMessage();
        
        TemporaryQueue tempQueue = context.createTemporaryQueue();
        
        messageConsumer = context.createConsumer(tempQueue);
                
        map = new HashMap<>();
        
        map.put("reservation", (Reservation) messageData);
        map.put("used_points", (Integer) points);
                
        msg.setObject((Serializable) map);
               
        msg.setJMSReplyTo(tempQueue); //setta  la coda temporanea dove arriverà la risposta
        msg.setJMSCorrelationID(utilBean.getRandomString()); //setta l'id 
        
        return msg;
    }
    
    private String receiveMessageFromBookingProcessorBean() throws JMSException
    {
        Message msg = messageConsumer.receive();

        TextMessage response = (TextMessage)msg;

        return response.getText(); //ricevi indietro l'id
    }  
}
