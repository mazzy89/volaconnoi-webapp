/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;


import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mazzy
 */
public interface RouteManagerBeanInterface
{                
    public void addRoute(String airlane,
                         String aircraft_id,
                         String airport_city_source,
                         String airport_city_dest,
                         String departure_date,
                         String arrival_date,
                         String departure_hour,
                         String departure_minutes,
                         String arrival_hour,
                         String arrival_minutes,
                         String class_travel,
                         String seats,
                         String rate);
    
    public Route getRouteById(String id);

    public List<Route> getRoutesList();
        
    public List<Reservation> getRouteReservationsListById(String id);
    
    public List<Route> getRoutesByInputParameters(String source, String destination, String date, String travel_class, String date_flexi);
    
    public List<Long> getDurationRoutes(List<Route> routesList);
    
    public void updateSeatsRoute(Reservation reserv);
   
    public Map<String,Float> getCityAirportByLowestRate();
    
    public List<String> getDistinctAirportCitySource();
    
    public List<String> getDistinctAirportCityDest(String airport_source);
    
    public List<Route> getListTimesByAirportSourceAirportDest(String airport_source, String airport_dest);
}
