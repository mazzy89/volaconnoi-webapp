/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.entity.Airport;
import it.volaconnoi.logic.AirportFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mazzy
 */
@Stateless
public class AirportFacade extends AbstractFacade<Airport> implements AirportFacadeLocal
{
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public AirportFacade()
    {
        super(Airport.class);
    }
    
    @Override
    public List<Airport> getAirportsListOrderByCity()
    {
        TypedQuery<Airport> query = em.createNamedQuery("Airport.findAllAirportsOrderByCity", Airport.class);
        
        return query.getResultList();
    }

    @Override
    public List<Airport> getAirportCountries()
    {
        Query query = em.createQuery("SELECT DISTINCT(a.country) FROM Airport a ORDER BY a.country ASC");
        
        return (List<Airport>)query.getResultList();
        
    }
    
    @Override
    public List<Airport> getCitiesByAirportCountry(String country)
    {
        Query query = em.createQuery("SELECT a FROM Airport a WHERE a.country = :country ORDER BY a.city ASC");
        
        return (List<Airport>) query.setParameter("country", country).getResultList();                                 
    }

    @Override
    public List<Airport> getAirportCoordinatesByName(String name)
    {
        Query query = em.createQuery("SELECT a from Airport a WHERE a.name = :name");
        
        return (List<Airport>)query.setParameter("name", name).setMaxResults(1).getResultList();
    }
}
