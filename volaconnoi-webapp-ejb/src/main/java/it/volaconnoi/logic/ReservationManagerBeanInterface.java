/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import java.util.List;

/**
 *
 * @author Mazzy
 */
public interface ReservationManagerBeanInterface 
{
    public Reservation addReservation(Route r, UserCredential u, int passengers, int luggages, float price);
    
    public String generateIDReservation();
    
    public List<Reservation> getValidReservation(String id_reserv);
}
