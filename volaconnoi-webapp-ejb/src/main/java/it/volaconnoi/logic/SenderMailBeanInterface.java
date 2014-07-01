/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Reservation;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

/**
 *
 * @author Mazzy
 */
public interface SenderMailBeanInterface 
{
    public void sendEmail(Reservation r) throws MessagingException, UnsupportedEncodingException;
}
