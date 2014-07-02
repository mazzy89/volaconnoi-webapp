/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.mdb;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.GetterFidelityPointsBeanInterface;
import it.volaconnoi.logic.ReservationManagerBeanInterface;
import it.volaconnoi.logic.RouteManagerBeanInterface;
import it.volaconnoi.logic.SenderMailBeanInterface;
import it.volaconnoi.logic.UserManagerBeanInterface;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSSessionMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Mazzy
 */
@MessageDriven(mappedName = "jms/bookingProcessorQueueReceiver", 
               activationConfig = {@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                                   @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BookingProcessorBean implements MessageListener
{   
    @EJB
    private ReservationManagerBeanInterface reservationManagerBean;
    @EJB
    private RouteManagerBeanInterface routeManagerBean;
    @EJB
    private SenderMailBeanInterface senderMailBean;
    @EJB
    private UserManagerBeanInterface userManagerBean;
    @EJB
    private GetterFidelityPointsBeanInterface getterFidelityPointsBean;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    @JMSConnectionFactory("jms/reservationProcessorQueueFactory")
    @JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
    private JMSContext context;
    
    @Override
    public void onMessage(Message message)
    {               
        Reservation reserv = null;
        
        try
        {       
            ObjectMessage om = (ObjectMessage) message;
            
            Map<String, Object> map = (HashMap<String,Object>) om.getObject();

            reserv = (Reservation) map.get("reservation");
            int points = (Integer) map.get("used_points");
            
            System.out.println("Initializing to Processing reservation...");
            
            UserCredential user = reserv.getUsername(); //estrae l'utente

            user.getReservationsList().add(reserv); //aggiunge la reservation nella lista delle reservation dell'utente
            
            userManagerBean.updateFidelityPoints(user, points); //aggiorna i punti fedeltà qualora l'utente ne abbia fatto uso
            userManagerBean.addFidelityPoints(user, getterFidelityPointsBean.getFidelityPointsByRoute(reserv.getRoute())); //assegna i punti fedeltà
            
            routeManagerBean.updateSeatsRoute(reserv); // aggiorna i posti nel volo
            
            reserv.setId(reservationManagerBean.generateIDReservation()); // genera id reservation
            
            storeInDb(reserv, user, reserv.getRoute()); //effettua persistenza
            
            senderMailBean.sendEmail(reserv);
            
            System.out.println("Commiting reservation " + reserv.getId() + "...");
            
            TemporaryQueue tempQueue = (TemporaryQueue) om.getJMSReplyTo();
            
            sendIdReversationToBookingManagerBean(tempQueue, reserv.getId(), om.getJMSCorrelationID()); //prende la destinazione temporanea ed il messaggio per la risposta
        }
        catch(UnsupportedEncodingException uee)
        {
            System.err.println("An error occured during the sending of the confirmation mail to " + reserv.getUsername().getEmail());
        }
        catch(MessagingException me)
        {
            System.err.println("An error occured during the sending of the email to " + reserv.getUsername().getEmail());
        }
        catch(JMSException e)
        {
            System.err.println("An error occured during the processing of the booking " + reserv.getId());
        }
    }
     
    
    private void storeInDb(Reservation r, UserCredential u, Route route)
    {        
        em.persist(r);
        em.merge(u);
        em.merge(route);
    }
    
    private Message createMessageForBookingManagerBean(String id_reservation, String id_correlation) throws JMSException
    {
        TextMessage msg = context.createTextMessage();
        
        msg.setText(id_reservation);
        msg.setJMSCorrelationID(id_correlation);
        
        return msg;
    }
    
    private void sendIdReversationToBookingManagerBean(Destination ReceiverDestination, String id_reservation, String id_correlation) throws JMSException
    {
        JMSProducer messageProducer = context.createProducer();
                        
        messageProducer.send(ReceiverDestination, createMessageForBookingManagerBean(id_reservation, id_correlation));
    }
}

