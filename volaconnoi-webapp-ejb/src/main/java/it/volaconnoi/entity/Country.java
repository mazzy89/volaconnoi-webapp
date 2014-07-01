
package it.volaconnoi.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Mazzy
 */
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ISO", nullable = false)
    private String iso;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "NICENAME", nullable = false)
    private String nicename;
    @Column(name = "ISO_3", nullable = false)
    private String iso3;
    @Column(name = "NUMCODE")
    private Short numcode;
    @Column(name = "PHONE_CODE", nullable = false)
    private int phonecode;

    public Country()
    {
    }
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getIso()
    {
        return iso;
    }

    public void setIso(String iso)
    {
        this.iso = iso;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNicename()
    {
        return nicename;
    }

    public void setNicename(String nicename)
    {
        this.nicename = nicename;
    }

    public String getIso3()
    {
        return iso3;
    }

    public void setIso3(String iso3)
    {
        this.iso3 = iso3;
    }

    public Short getNumcode()
    {
        return numcode;
    }

    public void setNumcode(Short numcode)
    {
        this.numcode = numcode;
    }

    public int getPhonecode()
    {
        return phonecode;
    }

    public void setPhonecode(int phonecode)
    {
        this.phonecode = phonecode;
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
        if (!(object instanceof Country))
        {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "it.volaconoi.entity.Country[ id=" + id + " ]";
    }  
}
