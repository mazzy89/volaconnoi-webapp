/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.Airlane;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mazzy
 */
@Local
public interface AirlaneFacadeLocal
{

    void create(Airlane airlane);

    void edit(Airlane airlane);

    void remove(Airlane airlane);

    Airlane find(Object id);

    List<Airlane> findAll();

    List<Airlane> findRange(int[] range);

    int count();
    
    List<Airlane> getAirlanesActiveList();
    
}
