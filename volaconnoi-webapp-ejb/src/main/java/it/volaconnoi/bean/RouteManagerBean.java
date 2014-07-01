/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.logic.AirportFacadeLocal;
import it.volaconnoi.logic.RouteManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;


/**
 *
 * @author Mazzy
 */
@Stateless
public class RouteManagerBean implements RouteManagerBeanInterface
{
    @EJB
    private AirportFacadeLocal airportFacade;
    @EJB
    private UtilBeanInterface utilBean;
    @PersistenceContext
    private EntityManager em;
    
    @Override
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
                         String rate)
    {
        Route route = new Route();
        
                       
        route.setAirlane(airlane);
        route.setAircraft_id(aircraft_id.toUpperCase());
        
        route.setAirport_city_source(airportFacade.find(Integer.parseInt(airport_city_source)));
        route.setAirport_city_dest(airportFacade.find(Integer.parseInt(airport_city_dest)));
        
        route.setDeparture_date(utilBean.getFormattedDate(departure_date, departure_hour, departure_minutes, 0));
        route.setArrival_date(utilBean.getFormattedDate(arrival_date, arrival_hour, arrival_minutes, 0));
        
        route.setTravel_class(class_travel);
        route.setSeats(Integer.parseInt(seats));
        route.setPrice(Float.parseFloat(rate));
        
        route.setReservationsList(new LinkedList<Reservation>());
                        
        em.persist(route);      	
    }
    
    @Override
    public List<Route> getRoutesList()
    {
        TypedQuery<Route> query = em.createNamedQuery("Route.findAll", Route.class);
        
        return query.getResultList();
    }
    
    @Override
    public Route getRouteById(String id)
    {
        return em.find(Route.class, id);
    }
    
    @Override
    public List<Reservation> getRouteReservationsListById(String id)
    {
        return getRouteById(id).getReservationsList();
    }

    @Override
    public List<Route> getRoutesByInputParameters(String source, String destination, String date, String travel_class, String date_flexi)
    {
        TypedQuery<Route> query = em.createNamedQuery("Route.findByInputParameters", Route.class);
        
        List<Route> routes_list = query
                               .setParameter("source", StringUtils.trim(WordUtils.capitalizeFully(source)))
                               .setParameter("dest", StringUtils.trim(WordUtils.capitalizeFully(destination)))
                               .setParameter("startDate", utilBean.getFormattedDate(date, "00", "00", !StringUtils.isNotEmpty(date_flexi) ? 0 : -2), TemporalType.TIMESTAMP)
                               .setParameter("endDate", utilBean.getFormattedDate(date, "23", "59", !StringUtils.isNotEmpty(date_flexi) ? 0 : +2), TemporalType.TIMESTAMP)
                               .setParameter("travel_class", travel_class)
                               .getResultList();

        return routes_list;
    }
    
    /**
     * Aggiorna i posti relativi al volo
     * 
     * @param reserv 
     */
    @Override
    public void updateSeatsRoute(Reservation reserv)
    {
       int passengers = reserv.getPassengers();
       
       Route route = reserv.getRoute();
              
       int update_seats = route.getSeats() - passengers;
       
       route.setSeats(update_seats);       
    }
        
    /**
     * Seleziona la tratta a minor prezzo che parte da un aeroporto
     * @return 
     */
    @Override
    public Map<String,Float> getCityAirportByLowestRate()
    {
        TypedQuery<Object[]> query = em.createQuery("SELECT r.airport_city_source.name, MIN(r.price) FROM Route r GROUP BY r.airport_city_source.name", Object[].class);
        
        List<Object[]> list = query.getResultList();
        
        Map<String, Float> resultMap = new HashMap<>(list.size());
          
        for(Object[] result : list)
        {
            resultMap.put((String)result[0], (Float)result[1]);
        }
        
        return resultMap;
    }
    
    @Override
    public List<String> getDistinctAirportCitySource()
    {
        Query query = em.createQuery("SELECT DISTINCT(r.airport_city_source.name) FROM Route r ORDER BY r.airport_city_source.name", Route.class);
        
        return query.getResultList();      
    }
    
    @Override
    public List<String> getDistinctAirportCityDest(String airport_source)
    {
        Query query = em.createQuery("SELECT DISTINCT(r.airport_city_dest.name) FROM Route r WHERE r.airport_city_source.name = :airport_source ORDER BY r.airport_city_dest.name", Route.class);
        
        return query.setParameter("airport_source", airport_source).getResultList();      
    }

    @Override
    public List<Route> getListTimesByAirportSourceAirportDest(String airport_source, String airport_dest)
    {
        TypedQuery<Route> query = em.createQuery("SELECT r FROM Route r WHERE r.airport_city_source.name = :airport_source AND r.airport_city_dest.name = :airport_dest", Route.class);
        
        
        return query
                    .setParameter("airport_source", airport_source)
                    .setParameter("airport_dest", airport_dest)
                    .getResultList();
    }
    
      
}
