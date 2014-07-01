/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.logic.SenderMailBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Mazzy
 */
@Stateless
public class SenderMailBean implements SenderMailBeanInterface 
{
    @EJB
    private UtilBeanInterface utilBean;
    @Resource(mappedName = "mail/bookingConfirmationMailer")
    private Session session;
      
    @Override
    public void sendEmail(Reservation r) throws MessagingException, UnsupportedEncodingException
    {
        Message msg = new MimeMessage(session);
        
        msg.setSubject("Conferma prenotazione " + r.getId());
        
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(r.getUsername().getEmail(), 
                                                                       r.getUsername().getName()));

        msg.setFrom(new InternetAddress("apocalipse89@gmail.com",
                                        "VolaConNoi.it"));

        Multipart multipart = new MimeMultipart();

        BodyPart messageBodyPart_1 = new MimeBodyPart();  
        messageBodyPart_1.setText("Siamo lieti di informarLa " + r.getUsername().getName() + " " + r.getUsername().getSurname() +
                                " " + "che la sua prenotazione è andata a buon fine.\n\n\n");         
        multipart.addBodyPart(messageBodyPart_1);

        BodyPart messageBodyPart_2 = new MimeBodyPart();
        messageBodyPart_2.setText("Qui i suoi dati: \n\n" 
                                + "PNR: " + r.getId() + "\n"
                                + "Partenza da: " + r.getRoute().getAirport_city_source().getName() + " il " + utilBean.dateToString(r.getRoute().getDeparture_date()) + "\n"
                                + "Arrivo a: " + r.getRoute().getAirport_city_dest().getName() + " il " + utilBean.dateToString(r.getRoute().getArrival_date()) + "\n"
                                + "N. Passeggeri: " + r.getPassengers() + "\n"
                                + "Bagagli: " + r.getLuggages() + "\n");
        multipart.addBodyPart(messageBodyPart_2);

        msg.setContent(multipart);

        Transport.send(msg);  
    }
}
