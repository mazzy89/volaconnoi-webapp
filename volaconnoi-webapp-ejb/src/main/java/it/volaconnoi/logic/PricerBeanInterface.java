/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import java.util.List;

/**
 *
 * @author Mazzy
 */
public interface PricerBeanInterface
{
    public static final double TAX = 37.99; //tassa bagaglio
    
    public float calculatePriceByPassenger(int passengers, int luggages, float price);
    
    public float calculateBuiltInByRoute(List<Reservation> list);
    
    public float calculateDiscountPrice(double price, int points);
}
