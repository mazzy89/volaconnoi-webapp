/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Country;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mazzy
 */
@Local
public interface CountryFacadeLocal
{

    void create(Country country);

    void edit(Country country);

    void remove(Country country);

    Country find(Object id);

    List<Country> findAll();

    List<Country> findRange(int[] range);

    int count();
    
}
