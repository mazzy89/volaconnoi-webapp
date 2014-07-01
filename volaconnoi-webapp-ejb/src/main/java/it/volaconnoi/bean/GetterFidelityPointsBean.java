/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.logic.GetterFidelityPointsBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import it.volaconnoi.entity.Route;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mazzy
 */
@Stateless
public class GetterFidelityPointsBean implements GetterFidelityPointsBeanInterface
{
    @EJB
    private UtilBeanInterface utilBean;

    @Override
    public int getFidelityPointsByRoute(Route r)
    {        
        Double distance = utilBean.distance(Double.parseDouble(r.getAirport_city_source().getLatitude()),
                                            Double.parseDouble(r.getAirport_city_source().getLongitude()), 
                                            Double.parseDouble(r.getAirport_city_dest().getLatitude()), 
                                            Double.parseDouble(r.getAirport_city_dest().getLongitude()));
        
        int value = (int) (distance / 100);
        
        // Ogni 100km 5 punti      
        return (value*5); 
    }
}
