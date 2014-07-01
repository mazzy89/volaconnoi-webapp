/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.bean;

import it.volaconnoi.logic.CountryFacadeLocal;
import it.volaconnoi.entity.Country;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mazzy
 */
@Stateless
public class CountryFacade extends AbstractFacade<Country> implements CountryFacadeLocal
{
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public CountryFacade()
    {
        super(Country.class);
    }
    
}
