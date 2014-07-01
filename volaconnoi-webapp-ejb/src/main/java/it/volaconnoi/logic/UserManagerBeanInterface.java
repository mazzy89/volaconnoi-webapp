
package it.volaconnoi.logic;

import it.volaconnoi.entity.PhoneNumber;
import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.UserCredential;
import java.util.List;

/**
 *
 * @author Mazzy
 */
public interface UserManagerBeanInterface
{
    public UserCredential addNewUser(String username,
                                     String password,
                                     String email,
                                     String name,
                                     String surname,
                                     String address,
                                     String city,
                                     String zipCode,
                                     String country,
                                     String areaCodeHomeNumber,
                                     String homeNumber,
                                     String areaCodeMobileNumber,
                                     String mobileNumber);
    
    public UserCredential updateInfoUser(String username,
                                         String new_password,
                                         String email,
                                         String address,
                                         String city,
                                         String zip_code,
                                         String country,
                                         String home_number_code,
                                         String home_number,
                                         String mobile_number_code,
                                         String mobile_number);
    
    public void removeUser(String username);
    
    public UserCredential getUserByUsername(String username);
    
    public boolean isUserPresent(String username);
    
    public PhoneNumber getHomeNumber(List<PhoneNumber> phoneNumbersList);
    
    public List<Reservation> getReservationByClient(UserCredential u, boolean isCancelled);
    
    public List<Reservation> getFlightsMadeByUser(String username);
    
    public List<Reservation> getFlightsRejectedByUser(String username);
    
    public void addFidelityPoints(UserCredential u, int fidelity_points);
    
    public void setCancelledReservation(String id_reservation);
    
    public void updateFidelityPoints(UserCredential u, int fidelity_points);
}
