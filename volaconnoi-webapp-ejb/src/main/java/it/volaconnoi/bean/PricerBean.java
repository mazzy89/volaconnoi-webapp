/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.logic.PricerBeanInterface;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mazzy
 */
@Stateless
public class PricerBean implements PricerBeanInterface
{       
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public float calculatePriceByPassenger(int passengers, int luggages, float price)
    {
        return (float) (passengers*price + luggages*TAX);
    } 

    @Override
    public float calculateBuiltInByRoute(List<Reservation> list) 
    {
        float total = 0.0f;
        
        for(Reservation r : list)
        {
            total += r.getPrice();
        }
        
        return total;
    }
    
    /**
     * Calcola il prezzo scontato con l'appliczione dei punti fedeltà
     * 
     * @param price
     * @param points
     * @return 
     */
    @Override
    public float calculateDiscountPrice(double price, int points)
    {
        float discount = 0.1f; //sconto 10%
		
        int iter = (int) points / 50; //10% ogni 50 punti
				
        for (int i = 0; i < iter; i++) 
        {
            price = price*(1 - discount);
        }
        
        return (float)price;
    }
}
