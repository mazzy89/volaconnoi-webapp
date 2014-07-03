
package it.volaconnoi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RESERVATION")
@NamedQueries(
{
    @NamedQuery(name = "Reservation.findValidReservation", query = "SELECT r FROM Reservation r WHERE r.id_reservation = :id_reservation AND r.cancelled = :status"),
    @NamedQuery(name = "Reservation.findReservationIfCheckedIn", query = "SELECT r FROM Reservation r WHERE r.user.username = :username AND "
                                                                                                                                + "r.id_reservation IN (SELECT c.id "
                                                                                                                                                     + "FROM CheckIn c)"
                                                                                                           + " ORDER BY r.date_reservation ASC"),
    @NamedQuery(name = "Reservation.findReservationRejected", query = "SELECT r FROM Reservation r WHERE r.user.username = :username AND "
                                                                                                      + "r.route.departure_date <= :dateNow AND "
                                                                                                      + "r.id_reservation NOT IN (SELECT c.id "
                                                                                                                               + "FROM CheckIn c)")
})
public class Reservation implements Serializable
{
    @Id
    @Column(name = "ID_RESERVATION")
    private String id_reservation;
    
    @Column(name = "PASSENGERS", nullable = false)
    private int passengers;
    
    @Column(name = "LUGGAGES", nullable = false)
    private int luggages;
    
    @Column(name = "PRICE", nullable = false)
    private float price;
    
    @Column(name = "DATE_PLACED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_reservation;
    
    @Column(name = "CANCELLED", nullable = false)
    private boolean cancelled;
    
    @ManyToOne
    private UserCredential user;
    
    @ManyToOne
    private Route route;
     
    public Reservation()
    {  
        
    }

    public String getId()
    {
        return id_reservation;
    }

    public void setId(String id)
    {
        this.id_reservation = id;
    }

    public int getPassengers()
    {
        return passengers;
    }

    public void setPassengers(int passengers)
    {
        this.passengers = passengers;
    }
    
    public int getLuggages()
    {
        return luggages;
    }

    public void setLuggages(int luggages)
    {
        this.luggages = luggages;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public Date getDate_reservation()
    {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation)
    {
        this.date_reservation = date_reservation;
    }
    
    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
    
    public UserCredential getUsername()
    {
        return user;
    }

    public void setUsername(UserCredential username)
    {
        this.user = username;
    }

    public Route getRoute()
    {
        return route;
    }

    public void setRoute(Route route)
    {
        this.route = route;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id_reservation);
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
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.id_reservation, other.id_reservation)) {
            return false;
        }
        return true;
    }

  
    @Override
    public String toString()
    {
        return "it.volaconoi.entity.Reservation[ id=" + id_reservation + " ]";
    }
}