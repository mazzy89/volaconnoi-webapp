
package it.volaconnoi.logic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mazzy
 */
public interface UtilBeanInterface
{  
    public boolean checkValidEmail(String email);
    
    public String hashPassword(String password);
    
    public Date getFormattedDate(String date, String hours, String minutes, int offset);
            
    public boolean validateSignUpForm(String username,
                                      String password,
                                      String confirm_password,
                                      String email,
                                      String name,
                                      String surname,
                                      String address,
                                      String city,
                                      String zipcode,
                                      String country,
                                      String mobilenumber,
                                      HttpServletRequest request);
    
    public boolean validateUserInfoUpdateForm(String username,
                                              String old_password,
                                              String new_password,
                                              String confirm_new_password,
                                              String email,
                                              String address,
                                              String city,
                                              String zipcode,
                                              String mobilenumber,
                                              HttpServletRequest request); 
    
    public double distance(double lat1, double lon1, double lat2, double lon2);  

    public double deg2rad(double deg);

    public double rad2deg(double rad);
    
    public String dateToString(Date d);
   
    public long calculateDurationBeetweenDates(Date d1, Date d2);

    
    public String getRandomString();
}
