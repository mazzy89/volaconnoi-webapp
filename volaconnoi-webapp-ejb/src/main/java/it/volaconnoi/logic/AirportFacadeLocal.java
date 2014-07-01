/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Airport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mazzy
 */
@Local
public interface AirportFacadeLocal
{

    void create(Airport airport);

    void edit(Airport airport);

    void remove(Airport airport);

    Airport find(Object id);

    List<Airport> findAll();

    List<Airport> findRange(int[] range);

    int count();
    
    List<Airport> getAirportsListOrderByCity();
    
    List<Airport> getAirportCountries();
    
    List<Airport> getCitiesByAirportCountry(String country);
    
    List<Airport> getAirportCoordinatesByName(String name);

}
