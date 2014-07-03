
package it.volaconnoi.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mazzy
 */
@Entity
@Table(name = "ROUTE")
@NamedQueries(
{
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r ORDER BY r.departure_date ASC"),
    @NamedQuery(name = "Route.findByInputParameters", query = "SELECT r FROM Route r WHERE r.airport_city_source.city = :source AND "
                                                                                        + "r.airport_city_dest.city = :dest AND "
                                                                                        + "r.departure_date BETWEEN :startDate AND :endDate AND "
                                                                                        + "r.travel_class = :travel_class AND "
                                                                                        + "r.seats > 0 "
                                                                                        + "ORDER BY r.departure_date ASC")
})
public class Route implements Serializable
{
    @Id
    @Column(name = "ID_ROUTE")
    private String id_route;  
    @Column(name = "AIRLANE", nullable = false)
    private String airlane;  
    @Column(name = "AIRCRAFT_ID", nullable = false)
    private String aircraft_id;
    @OneToOne(optional = false)
    private Airport airport_city_source;   
    @OneToOne(optional = false)
    private Airport airport_city_dest; 
    @Column(name = "DEPARTURE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departure_date;  
    @Column(name = "ARRIVAL_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrival_date;   
    @Column(name = "TRAVEL_CLASS", nullable = false)
    private String travel_class;    
    @Column(name = "SEATS", nullable = false)
    private int seats;
    @Column(name = "PRICE", nullable = false)
    private float price;
    
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Reservation> reservationsList;
     
    public Route()
    {   
        
    }
      
    @PrePersist
    public void setId_route()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYYHHmm");
        
        String format_departure_date = sdf.format(this.getDeparture_date());
        
        String unique_id_route = this.getAirlane() +
                                 this.getAircraft_id() + 
                                 this.getAirport_city_source().getCity() +
                                 this.getAirport_city_dest().getCity() +
                                 format_departure_date;
        
        this.id_route = unique_id_route.replaceAll(" ", "");
    }

    
    public String getId_route()
    {
        return id_route;
    }
    
    public String getAirlane()
    {
        return airlane;
    }

    public void setAirlane(String airlane)
    {
        this.airlane = airlane;
    }

    public String getAircraft_id()
    {
        return aircraft_id;
    }

    public void setAircraft_id(String aircraft_id)
    {
        this.aircraft_id = aircraft_id;
    }

    public Airport getAirport_city_source()
    {
        return airport_city_source;
    }

    public void setAirport_city_source(Airport airport_city_source)
    {
        this.airport_city_source = airport_city_source;
    }

    public Airport getAirport_city_dest()
    {
        return airport_city_dest;
    }

    public void setAirport_city_dest(Airport airport_city_dest)
    {
        this.airport_city_dest = airport_city_dest;
    }

    public Date getDeparture_date()
    {
        
        return this.departure_date;
    }
    
    public void setDeparture_date(Date departure_date)
    {
        this.departure_date = departure_date;
    }

    public Date getArrival_date()
    {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date)
    {
        this.arrival_date = arrival_date;
    }

    public String getTravel_class()
    {
        return travel_class;
    }

    public void setTravel_class(String travel_class)
    {
        this.travel_class = travel_class;
    }

    public int getSeats()
    {
        return seats;
    }

    public void setSeats(int seats)
    {
        this.seats = seats;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public List<Reservation> getReservationsList()
    {
        return reservationsList;
    }

    public void setReservationsList(List<Reservation> reservationsList)
    {
        this.reservationsList = reservationsList;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id_route != null ? id_route.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id_route fields are not set
        if (!(object instanceof Route))
        {
            return false;
        }
        Route other = (Route) object;
        if ((this.id_route == null && other.id_route != null) || (this.id_route != null && !this.id_route.equals(other.id_route)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "it.volaconoi.entity.Route[ id=" + id_route + " ]";
    }    
}
