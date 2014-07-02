/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import javax.ejb.Local;

/**
 *
 * @author Mazzy
 */
@Local
public interface BookingManagerBeanInterface
{    
    public String purchase(Route r, UserCredential u, int passengers, int luggages, double price, int points);        
}
