/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Mazzy
 */
@Entity
@Table(name = "AIRLANE")
@NamedQueries(
{
    @NamedQuery(name = "Airlane.findAllActive", query = "SELECT a FROM Airlane a WHERE a.active = :active ORDER BY a.name ASC")
})
public class Airlane implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AIRLANE_ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ALIAS_AIRLANE")
    private String alias;
    @Column(name = "IATA")
    private String iata;
    @Column(name = "ICAO")
    private String icao;
    @Column(name = "CALL_SIGN")
    private String call_sign;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "ACTIVE")
    private String active;
    
    public Airlane()
    {
        
    }
   
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getIata()
    {
        return iata;
    }

    public void setIata(String iata)
    {
        this.iata = iata;
    }

    public String getIcao()
    {
        return icao;
    }

    public void setIcao(String icao)
    {
        this.icao = icao;
    }

    public String getCall_sign()
    {
        return call_sign;
    }

    public void setCall_sign(String call_sign)
    {
        this.call_sign = call_sign;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getActive()
    {
        return active;
    }

    public void setActive(String active)
    {
        this.active = active;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Airlane other = (Airlane) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return "it.volaconoi.entity.Airlane[ id=" + id + " ]";
    }
    
}
