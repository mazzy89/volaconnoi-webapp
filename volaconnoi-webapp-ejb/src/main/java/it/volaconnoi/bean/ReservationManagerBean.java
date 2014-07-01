/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.ReservationManagerBeanInterface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 *
 * @author Mazzy
 */
@Stateless
public class ReservationManagerBean implements ReservationManagerBeanInterface 
{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Reservation addReservation(Route r, UserCredential u, int passengers, int luggages, float price)
    {
        Reservation reserv = new Reservation();
        
        reserv.setPassengers(passengers);
        reserv.setLuggages(luggages);
        reserv.setPrice(price);
        reserv.setCancelled(false);
        
        reserv.setUsername(u);
        reserv.setRoute(r);
        
        u.getReservationsList().add(reserv);
        
        return reserv;
    }
    
    @Override
    public String generateIDReservation()
    {
        String id_reservation = RandomStringUtils.randomAlphabetic(3).toUpperCase() +
                                RandomUtils.nextInt(10, 99) +
                                RandomStringUtils.randomAlphabetic(2).toUpperCase();
        
        return (id_reservation);
    } 
       
    @Override
    public Reservation getValidReservation(String id_reservation)
    {        
        Reservation r = em.find(Reservation.class, id_reservation);
        
        return r;
    }
}
