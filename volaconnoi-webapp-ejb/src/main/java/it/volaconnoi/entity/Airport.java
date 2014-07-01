
package it.volaconnoi.entity;

import com.google.gson.annotations.Expose;
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
@Table(name = "AIRPORT")
@NamedQueries(
{
    @NamedQuery(name = "Airport.findAllAirportsOrderByCity", query = "SELECT a FROM Airport a ORDER BY a.city ASC"),
    @NamedQuery(name = "Airport.findAirportByCity", query = "SELECT a FROM Airport a WHERE a.city = :city")
})
public class Airport implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AIRPORT_ID")
    private int id;
    @Column(name = "NAME", nullable = false)
    @Expose
    private String name;
    @Column(name = "CITY")
    @Expose
    private String city;
    @Column(name = "COUNTRY")
    @Expose
    private String country;
    @Column(name = "IATA_FAA")
    private String iata_faa_code;
    @Column(name = "ICAO")
    private String icao_code;
    @Column(name = "LATITUDE")
    @Expose
    private String latitude;
    @Column(name = "LONGITUDE")
    @Expose
    private String longitude;
    @Column(name = "ALTITUDE")
    private String altitude;
    @Column(name = "TIMEZONE")
    private String timezone;
    @Column(name = "DST")
    private String dst;
    
    public Airport()
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

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getIata_faa_code()
    {
        return iata_faa_code;
    }

    public void setIata_faa_code(String iata_faa_code)
    {
        this.iata_faa_code = iata_faa_code;
    }

    public String getIcao_code()
    {
        return icao_code;
    }

    public void setIcao_code(String icao_code)
    {
        this.icao_code = icao_code;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getAltitude()
    {
        return altitude;
    }

    public void setAltitude(String altitude)
    {
        this.altitude = altitude;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public String getDst()
    {
        return dst;
    }

    public void setDst(String dst)
    {
        this.dst = dst;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Airport other = (Airport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return "it.volaconoi.entity.Airport[ id=" + id + " ]";
    }  
}
