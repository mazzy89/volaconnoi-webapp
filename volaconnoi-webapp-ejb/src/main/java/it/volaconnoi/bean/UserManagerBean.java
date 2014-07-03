
package it.volaconnoi.bean;

import it.volaconnoi.entity.CheckIn;
import it.volaconnoi.entity.PhoneNumber;
import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.UserManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;


/**
 *
 * @author Mazzy
 */
@Stateless
public class UserManagerBean implements UserManagerBeanInterface
{
    @EJB
    private UtilBeanInterface utilBean;

    @PersistenceContext
    private EntityManager em;
        
    @Override
    public UserCredential addNewUser(String username, 
                                     String password, 
                                     String email, 
                                     String name, 
                                     String surname, 
                                     String address, 
                                     String city, 
                                     String zip_code, 
                                     String country,
                                     String area_code_home_number, 
                                     String home_number,
                                     String area_code_mobile_number, 
                                     String mobile_number)
    {
        UserCredential user = new UserCredential();
        
        user.setUsername(username);
        user.setPassword(utilBean.hashPassword(password));
        user.setEmail(email);
        
        user.setName(StringUtils.capitalize(name));
        user.setSurname(WordUtils.capitalizeFully(surname));
        user.setAddress(WordUtils.capitalizeFully(address));
        user.setCity(WordUtils.capitalizeFully(city));
        user.setZip_code(zip_code);
        user.setCountry(country);
        user.setFidelity_points(0);
        
        user.setPhoneNumbers(new LinkedList<PhoneNumber>());
             
        if(StringUtils.isNotBlank(home_number))
            user.getPhoneNumbers().add(new PhoneNumber("Fisso", area_code_home_number, home_number));
        
        user.getPhoneNumbers().add(new PhoneNumber("Mobile", area_code_mobile_number, mobile_number));
        
        user.setReservationsList(new LinkedList<Reservation>());
        
        user.setCheckInList(new LinkedList<CheckIn>());
        
        em.persist(user);
                
        return user;
    }
    
    @Override
    public UserCredential updateInfoUser(String username,
                                         String new_password,
                                         String email,
                                         String address,
                                         String city,
                                         String zip_code,
                                         String country,
                                         String area_code_home_number,
                                         String home_number,
                                         String area_code_mobile_number,
                                         String mobile_number)
    {
        
        UserCredential user = getUserByUsername(username);
        
        if(StringUtils.isNotEmpty(new_password))
            user.setPassword(utilBean.hashPassword(new_password));
        
        user.setEmail(email);
        user.setAddress(WordUtils.capitalizeFully(address));
        user.setCity(WordUtils.capitalizeFully(city));
        user.setZip_code(zip_code);
        user.setCountry(country);
        
        List<PhoneNumber> phoneNumbersList = user.getPhoneNumbers();
           
        if(!StringUtils.isNotEmpty(home_number)) //è vuoto
        {
            PhoneNumber hn = getHomeNumber(phoneNumbersList);
            
            if(hn != null)
            {
                phoneNumbersList.remove(hn);
            }
            else if(StringUtils.isNotEmpty(home_number))
            {
                phoneNumbersList.add(new PhoneNumber("Fisso", area_code_home_number, home_number));
            }
        }
        else
        {
            PhoneNumber hn = getHomeNumber(phoneNumbersList);
            
            if (hn != null)
            {
                hn.setArea_code(area_code_home_number);
                hn.setPhone_number(home_number);
            }
            else
            {
                phoneNumbersList.add(new PhoneNumber("Fisso", area_code_home_number, home_number));
            }
        }

        for(PhoneNumber pn : phoneNumbersList)
        {
            if(pn.getType().equals("Mobile"))
            {
                pn.setArea_code(area_code_mobile_number);
                pn.setPhone_number(mobile_number);
            }
         }

        em.merge(user);
        
        return user;
    }

    @Override
    public void removeUser(String username)
    {
        em.remove(getUserByUsername(username));
        em.flush();
    }
    
    @Override
    public boolean isUserPresent(String username)
    {
        boolean isPresent = false;
        
        if(getUserByUsername(username) != null)
        {
            isPresent = true;
        }
        
        return isPresent;
    }
    
    @Override
    public UserCredential getUserByUsername(String username)
    {
        return (em.find(UserCredential.class, username));
    }

    @Override
    public PhoneNumber getHomeNumber(List<PhoneNumber> phoneNumbersList)
    {
        PhoneNumber hn = null;
        
        for(PhoneNumber pn : phoneNumbersList)
        {
            if(pn.getType().equals("Fisso"))
            {
                hn = pn;
                return hn;
            }
        }
        
        return hn;
    }
    
    @Override
    public List<Reservation> getReservationByClient(UserCredential u, boolean isCancelled)
    {
        List<Reservation> list = u.getReservationsList();
        
        List<Reservation> new_list = new LinkedList<>();
        
        for(Reservation r : list)
        {
            if(r.isCancelled() == isCancelled)
            {
                new_list.add(r);
            }
        }
        
        return new_list;
    }
    
    @Override
    public List<Reservation> getFlightsMadeByUser(String username) //voli effettuati dagli utenti
    {
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findReservationIfCheckedIn", Reservation.class);
        
        return query.setParameter("username", username).getResultList();
    }
    
    @Override
    public List<Reservation> getFlightsRejectedByUser(String username)
    {
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findReservationRejected", Reservation.class);
        
        return query
                    .setParameter("username", username)
                    .setParameter("dateNow", new Date(), TemporalType.TIMESTAMP)
                    .getResultList();
    }

    @Override
    public void addFidelityPoints(UserCredential u, int fidelity_points) 
    {
        u.setFidelity_points(u.getFidelity_points() + fidelity_points);
    } 
    
    @Override
    public void updateFidelityPoints(UserCredential u, int fidelity_points)
    {
        int actual_points = u.getFidelity_points() - fidelity_points;
        
        u.setFidelity_points(actual_points);
    }
    
    @Override
    public void setCancelledReservation(String id_reservation)
    {
        Reservation reserv = em.find(Reservation.class, id_reservation);
        
        reserv.setCancelled(true);
        
        Route route = reserv.getRoute();
        
        int upd_seats = route.getSeats() + reserv.getPassengers();
        
        route.setSeats(upd_seats);
        
        em.merge(route);
        em.merge(reserv);
    }
}

