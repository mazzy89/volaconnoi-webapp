/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mazzy
 */
@Entity
@Table(name = "CHECK_IN")
public class CheckIn implements Serializable 
{
    @Id
    @Column(name = "ID_CHECKIN")
    private String id;
    
    @ManyToOne
    private UserCredential user;
    
    @Column(name = "DATE_CHECKIN", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_checkin;

    public CheckIn()
    {
        
    }
    
    public String getId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public Date getDate_checkin() 
    {
        return date_checkin;
    }

    public void setDate_checkin(Date date_checkin)
    {
        this.date_checkin = date_checkin;
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckIn)) {
            return false;
        }
        CheckIn other = (CheckIn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "it.volaconnoi.entity.CheckIn[ id=" + id + " ]";
    }    
}
