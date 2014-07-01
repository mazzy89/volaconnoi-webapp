
package it.volaconnoi.servlet;

import it.volaconnoi.entity.PhoneNumber;
import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.CountryFacadeLocal;
import it.volaconnoi.logic.UserManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mazzy
 */
@WebServlet(name = "UserAreaServlet",
            urlPatterns = {"/user/",
                           "/user/info",
                           "/user/showreservations",
                           "/user/cancelledreservations",
                           "/user/cancel",
                           "/user/flightsmade",
                           "/user/flightsrejected",
                           "/user/update",
                           "/user/remove",
                           "/user/removed"})
//@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER"}))
public class UserAreaServlet extends HttpServlet
{
    @EJB
    private CountryFacadeLocal countryFacade;
    @EJB
    private UtilBeanInterface utilBean;
    @EJB
    private UserManagerBeanInterface userManagerBean;
    
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userPath = request.getServletPath();
       
        if(userPath.equals("/user/"))
        {
           UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());
           
           request.setAttribute("fidelity_points", user.getFidelity_points());
           
           request.setAttribute("date_daily", new Date());
           
           request.getRequestDispatcher("/WEB-INF/view/userarea/userarea.jsp").forward(request, response); 
        }
               
        if(userPath.equals("/user/showreservations"))
        {
            UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());
            
            request.setAttribute("reservations_user_valid", userManagerBean.getReservationByClient(user, false));
            
            request.getRequestDispatcher("/WEB-INF/view/userarea/reservations/valid.jsp").forward(request, response);
        }
        
        if(userPath.equals("/user/cancelledreservations"))
        {
            UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());
            
            request.setAttribute("reservations_user_cancelled", userManagerBean.getReservationByClient(user, true));
            
            request.getRequestDispatcher("/WEB-INF/view/userarea/reservations/cancelled.jsp").forward(request, response);
        }
        
        if(userPath.equals("/user/cancel"))
        {
            String id_reserv = request.getParameter("id");
            
            userManagerBean.setCancelledReservation(id_reserv);
            
            UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());

            request.setAttribute("reservations_user_cancelled", userManagerBean.getReservationByClient(user, true));
            
            request.getRequestDispatcher("/WEB-INF/view/userarea/reservations/cancelled.jsp").forward(request, response);
        }
        
        if(userPath.equals("/user/flightsmade")) // mostra i voli effettuati dall'utente ossia i voli il cui utente ha effettuato il checkin
        {
            List<Reservation> list = userManagerBean.getFlightsMadeByUser(request.getUserPrincipal().getName());
            
            request.setAttribute("flights_made", list);
            
            request.getRequestDispatcher("/WEB-INF/view/userarea/checkin/made.jsp").forward(request, response);
        }
        
        if(userPath.equals("/user/flightsrejected"))
        {
            List<Reservation> list = userManagerBean.getFlightsRejectedByUser(request.getUserPrincipal().getName());
            
            request.setAttribute("flights_rejected", list);
            
            request.getRequestDispatcher("/WEB-INF/view/userarea/checkin/rejected.jsp").forward(request, response);
        }
        
        if(userPath.equals("/user/info"))
        {
            UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());
            
            request.setAttribute("name", user.getName());
            request.setAttribute("surname", user.getSurname());
            request.setAttribute("address", user.getAddress());
            request.setAttribute("city", user.getCity());
            request.setAttribute("zip_code", user.getZip_code());
            request.setAttribute("country", user.getCountry());
                        
            request.setAttribute("phoneNumbersList", user.getPhoneNumbers());
            
            request.getRequestDispatcher("/WEB-INF/view/userarea/userinfo.jsp").forward(request, response); 
        }
        
        if(userPath.equals("/user/update"))
        {
            loadUpdateForm(request,response);
            request.getRequestDispatcher("/WEB-INF/view/userarea/userupdate.jsp").forward(request, response); 

        }
        
        if(userPath.equals("/user/remove"))
        {
            request.getRequestDispatcher("/WEB-INF/view/userarea/userremove.jsp").forward(request, response);
        }
        
        if(userPath.equals("/user/removed"))
        {
            userManagerBean.removeUser(request.getUserPrincipal().getName());
            
            request.getRequestDispatcher("/logout").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userPath = request.getServletPath();
        
        if(userPath.equals("/user/update"))
        {            
            String username = request.getUserPrincipal().getName();
            String old_password = request.getParameter("old_password");
            String new_password = request.getParameter("new_password");
            String confirm_new_password = request.getParameter("confirm_new_password");    
            String email = (String)request.getParameter("email");
            
            String address = (String)request.getParameter("address");
            String city = request.getParameter("city");
            String zipcode = request.getParameter("zip_code");
            String country = request.getParameter("country");
            String homenumber_code = request.getParameter("homenumber_code");
            String homenumber = request.getParameter("homenumber");
            String mobilenumber_code = request.getParameter("mobilenumber_code");
            String mobilenumber = request.getParameter("mobilenumber");
                
            boolean validationErrorFlag = true;
            
            validationErrorFlag = utilBean.validateUserInfoUpdateForm(username, 
                                                                      old_password, 
                                                                      new_password, 
                                                                      confirm_new_password, 
                                                                      email,
                                                                      address,
                                                                      city,
                                                                      zipcode,
                                                                      mobilenumber,
                                                                      request);         
            if(validationErrorFlag == true)
            {
                loadUpdateForm(request, response);
                request.getRequestDispatcher("/WEB-INF/view/userarea/userupdate.jsp").forward(request, response);
            }
            else
            {
                UserCredential user = userManagerBean.updateInfoUser(username, 
                                                                     new_password, 
                                                                     email, 
                                                                     address, 
                                                                     city, 
                                                                     zipcode, 
                                                                     country,
                                                                     homenumber_code, 
                                                                     homenumber,
                                                                     mobilenumber_code, 
                                                                     mobilenumber);
                
                loadUpdateForm(request,response);
                
                request.setAttribute("successUpdate", "Le informazioni sono state aggiornate correttamente!");
                request.getRequestDispatcher("/WEB-INF/view/userarea/userupdate.jsp").forward(request, response);
            }   
        }
    }
   
    public void loadUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());

        request.setAttribute("email", user.getEmail());
        request.setAttribute("name", user.getName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("address", user.getAddress());
        request.setAttribute("city", user.getCity());
        request.setAttribute("zip_code", user.getZip_code());
        request.setAttribute("country_user", user.getCountry());

        request.setAttribute("CountriesList", countryFacade.findAll());

        List<PhoneNumber> phoneNumbersList = user.getPhoneNumbers();

        for(PhoneNumber pn : phoneNumbersList)
        {
            if(pn.getType().equals("Fisso"))
            {
                request.setAttribute("home_number", pn);
            }
            else if(pn.getType().equals("Mobile"))
            {
                request.setAttribute("mobile_number", pn);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
