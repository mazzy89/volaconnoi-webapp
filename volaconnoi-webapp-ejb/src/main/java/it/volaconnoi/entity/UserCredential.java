/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mazzy
 */
@Entity
@Table(name = "USER_CREDENTIAL")
@SecondaryTable(name = "CLIENT", pkJoinColumns=@PrimaryKeyJoinColumn(name="USERNAME"))
public class UserCredential implements Serializable
{
    @Id
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "GROUP_NAME", insertable = false, updatable = false)
    private String group_name;
    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;
    
    @Column(name = "NAME", nullable= false, table="CLIENT")
    private String name;
    @Column(name = "SURNAME", nullable= false, table = "CLIENT")
    private String surname;
    @Column(name = "ADDRESS", nullable= false , table = "CLIENT")
    private String address;
    @Column(name = "CITY", nullable = false, table = "CLIENT")
    private String city;
    @Column(name = "ZIP_CODE", nullable = false, table = "CLIENT")
    private String zip_code;
    @Column(name = "COUNTRY", nullable = false, table = "CLIENT")
    private String country;
    @Column(name = "FIDELITY_POINTS", nullable = false, table = "CLIENT")
    private int fidelity_points;
    
    @ElementCollection
    @CollectionTable(name = "CLIENT_PHONE_NUMBER", joinColumns = @JoinColumn(name = "USERNAME"))
    private List<PhoneNumber> phoneNumbers;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservationsList;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CheckIn> checkInList;
         
    public UserCredential()
    {
        
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getGroup_name()
    {
        return group_name;
    }

    public void setGroup_name(String group_name)
    {
        this.group_name = group_name;
    }
    
    public Date getCreate_date()
    {
        return create_date;
    }

    public void setCreate_date(Date create_date)
    {
        this.create_date = create_date;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZip_code()
    {
        return zip_code;
    }

    public void setZip_code(String zip_code)
    {
        this.zip_code = zip_code;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public int getFidelity_points()
    {
        return fidelity_points;
    }

    public void setFidelity_points(int fidelity_points)
    {
        this.fidelity_points = fidelity_points;
    }

    public List<PhoneNumber> getPhoneNumbers()
    {
        return phoneNumbers;
    }

    public void setPhoneNumbers (List<PhoneNumber> phoneNumbers)
    {
        this.phoneNumbers = phoneNumbers;
    }
    
    public List<Reservation> getReservationsList()
    {
        return reservationsList;
    }

    public void setReservationsList(List<Reservation> reservationsList)
    {
        this.reservationsList = reservationsList;
    }

    public List<CheckIn> getCheckInList()
    {
        return checkInList;
    }

    public void setCheckInList(List<CheckIn> checkInList) 
    {
        this.checkInList = checkInList;
    }
     
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the username fields are not set
        if (!(object instanceof UserCredential))
        {
            return false;
        }
        UserCredential other = (UserCredential) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "it.volaconoi.entity.UserCredential[ id=" + username + " ]";
    }
    
}
