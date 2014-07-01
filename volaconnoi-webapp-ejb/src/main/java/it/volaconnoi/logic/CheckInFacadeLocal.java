/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.logic;

import it.volaconnoi.entity.CheckIn;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mazzy
 */
@Local
public interface CheckInFacadeLocal
{

    void create(CheckIn checkIn);

    void edit(CheckIn checkIn);

    void remove(CheckIn checkIn);

    CheckIn find(Object id);

    List<CheckIn> findAll();

    List<CheckIn> findRange(int[] range);

    int count();   
}
