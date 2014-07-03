
package it.volaconnoi.bean;

import it.volaconnoi.logic.UserManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mazzy
 */
@Stateless
public class UtilBean implements UtilBeanInterface
{
    @EJB
    private UserManagerBeanInterface userManagerBean;
    
    @PersistenceContext
    private EntityManager em;
        
    @Override
    public boolean checkValidEmail(String email)
    {
        boolean isValid = false;
        
        InternetAddress ia;
        
        try
        {
            ia = new InternetAddress(email);
            ia.validate();
            isValid = true;
        }
        catch (AddressException ex)
        {
            return isValid; 
        }
        
        return isValid;
     }

    @Override
    public String hashPassword(String password)
    {
        String passwordEncoded = null;
        
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            
            byte byteData[] = md.digest();
           
            Encoder encoder = Base64.getEncoder();
           
            passwordEncoded = encoder.encodeToString(byteData);
           
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(UtilBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return passwordEncoded;
    }
    
    
    @Override
    public Date getFormattedDate(String date, String hours, String minutes, int offset) 
    {    
        Date dateToStore = null;
        SimpleDateFormat sdf;
                
        try
        {
            sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            
            Calendar c = Calendar.getInstance();
            
            c.setTime(sdf.parse(date + " " + hours + ":" + minutes));
            
            c.add(Calendar.DATE, offset);
            
            dateToStore = c.getTime();
        } 
        catch (ParseException e) 
        {
            System.err.println("An error occured during the parsing of the date");
        }
        
        return dateToStore;
    }
        
    @Override
    public boolean validateSignUpForm(String username, 
                                      String password, 
                                      String confirmPassword, 
                                      String email, 
                                      String name, 
                                      String surname, 
                                      String address, 
                                      String city, 
                                      String zipCode, 
                                      String country, 
                                      String mobileNumber,
                                      HttpServletRequest request)
    {
        
        boolean errorFlag = false;
        
        boolean usernameInvalid;
        boolean usernameAlreadyExist;
        
        boolean passwordInvalid;
        boolean confirmPasswordError;
        
        boolean emailErrorInvalid;
        
        boolean nameErrorInvalid;
        boolean surnameErrorInvalid;
        boolean addressErrorInvalid;
        boolean cityErrorInvalid;
        boolean zipCodeErrorInvalid;
        boolean countryErrorInvalid;
        boolean mobileNumberErrorInvalid;
        
        if(!(StringUtils.isNotBlank(username)))
        {
            errorFlag = true;
            usernameInvalid = true;
            request.setAttribute("usernameInvalid", usernameInvalid);
        }
        else if(userManagerBean.isUserPresent(username))
        {
            errorFlag = true;
            usernameAlreadyExist = true;
            request.setAttribute("usernameAlreadyExist", usernameAlreadyExist);
        }
        
        if(!(StringUtils.isNotBlank(password)))
        {
            errorFlag = true;
            passwordInvalid = true;
            request.setAttribute("passwordInvalid", passwordInvalid);
        }
        else if(!(password.equals(confirmPassword)))
        {
            errorFlag = true;
            confirmPasswordError = true;
            request.setAttribute("confirmPasswordError", confirmPasswordError);
        }
        
        if(!(StringUtils.isNotBlank(email)) ||
           !checkValidEmail(email))
        {
            errorFlag = true;
            emailErrorInvalid = true;
            request.setAttribute("emailErrorInvalid", emailErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(name)))
        {
            errorFlag = true;
            nameErrorInvalid = true;
            request.setAttribute("nameErrorInvalid", nameErrorInvalid);          
        }
        
        if(!(StringUtils.isNotBlank(surname)))
        {
            errorFlag = true;
            surnameErrorInvalid = true;
            request.setAttribute("surnameErrorInvalid", surnameErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(address)))
        {
            errorFlag = true;
            addressErrorInvalid = true;
            request.setAttribute("addressErrorInvalid", addressErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(city)))
        {
            errorFlag = true;
            cityErrorInvalid = true;
            request.setAttribute("cityErrorInvalid", cityErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(zipCode)))
        {
            errorFlag = true;
            zipCodeErrorInvalid = true;
            request.setAttribute("zipCodeErrorInvalid", zipCodeErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(country)))
        {
            errorFlag = true;
            countryErrorInvalid = true;
            request.setAttribute("countryErrorInvalid", countryErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(mobileNumber)))
        {
            errorFlag = true;
            mobileNumberErrorInvalid = true;
            request.setAttribute("mobileNumberErrorInvalid", mobileNumberErrorInvalid);
        }

        return errorFlag;
    }

    @Override
    public boolean validateUserInfoUpdateForm(String username,
                                              String old_password, 
                                              String new_password, 
                                              String confirm_new_password, 
                                              String email,
                                              String address,
                                              String city,
                                              String zipcode,
                                              String mobilenumber,
                                              HttpServletRequest request)
    {
        boolean errorFlag = false;
        
        boolean oldPasswordError;
        boolean newPasswordNotMatched;
        boolean emailNotValidError;
        boolean addressErrorInvalid;
        boolean cityErrorInvalid;
        boolean zipCodeErrorInvalid;
        boolean mobileNumberErrorInvalid;
        
        if(StringUtils.isNotBlank(old_password))
        {
            if(!(hashPassword(old_password).equals(userManagerBean.getUserByUsername(username).getPassword())))
            {
                errorFlag = true;
                oldPasswordError = true;
                request.setAttribute("oldPasswordError", oldPasswordError);
            }
            else if(!(new_password.equals(confirm_new_password)))
            {
                errorFlag = true;
                newPasswordNotMatched = true;
                request.setAttribute("newPasswordNotMatched", newPasswordNotMatched);
            }
        }
        
        if(!checkValidEmail(email))
        {
            errorFlag = true;
            emailNotValidError = true;
            request.setAttribute("emailNotValidError", emailNotValidError);
        }
        
        if(!(StringUtils.isNotBlank(address)))
        {
            errorFlag = true;
            addressErrorInvalid = true;
            request.setAttribute("addressErrorInvalid", addressErrorInvalid);
        }
        
         if(!(StringUtils.isNotBlank(city)))
        {
            errorFlag = true;
            cityErrorInvalid = true;
            request.setAttribute("cityErrorInvalid", cityErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(zipcode)))
        {
            errorFlag = true;
            zipCodeErrorInvalid = true;
            request.setAttribute("zipCodeErrorInvalid", zipCodeErrorInvalid);
        }
        
        if(!(StringUtils.isNotBlank(mobilenumber)))
        {
            errorFlag = true;
            mobileNumberErrorInvalid = true;
            request.setAttribute("mobileNumberErrorInvalid", mobileNumberErrorInvalid);
        }
        
        return errorFlag;
    }
    
    @Override
    public double distance(double lat1, double lon1, double lat2, double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        
        dist = dist * 1.609344;

        return (dist);
    }
    
    @Override
    public double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    @Override
    public double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }

    @Override
    public String dateToString(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        
        return(sdf.format(d));      
    }
    
    @Override
    public long calculateDurationBeetweenDates(Date d1, Date d2)
    {
        Instant d1_instant = Instant.ofEpochMilli(d1.getTime());
        Instant d2_instant = Instant.ofEpochMilli(d2.getTime());
        
        Duration between = Duration.between(d1_instant, d2_instant);
        
        return between.toHours();
    }

    @Override
    public String getRandomString()
    {
        return (RandomStringUtils.randomAlphanumeric(5));
    }
}
