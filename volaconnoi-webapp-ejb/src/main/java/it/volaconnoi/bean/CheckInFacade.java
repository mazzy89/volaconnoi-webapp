/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.logic.CheckInFacadeLocal;
import it.volaconnoi.entity.CheckIn;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mazzy
 */
@Stateless
public class CheckInFacade extends AbstractFacade<CheckIn> implements CheckInFacadeLocal 
{
    @PersistenceContext(unitName = "it.volaconnoi_volaconnoi-webapp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public CheckInFacade() 
    {
        super(CheckIn.class);
    }
}
