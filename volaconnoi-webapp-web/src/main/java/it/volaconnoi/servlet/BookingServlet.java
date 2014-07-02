/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.servlet;

import it.volaconnoi.entity.PhoneNumber;
import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.BookingManagerBeanInterface;
import it.volaconnoi.logic.CountryFacadeLocal;
import it.volaconnoi.logic.PricerBeanInterface;
import it.volaconnoi.logic.RouteManagerBeanInterface;
import it.volaconnoi.logic.UserManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author Mazzy
 */
@WebServlet(name = "BookingServlet", urlPatterns =
    {
        "/booking",
        "/confirm",
        "/commit"
})
public class BookingServlet extends HttpServlet
{
    @EJB
    BookingManagerBeanInterface bookingBean;
    @EJB
    private UtilBeanInterface utilBean;
    @EJB
    private PricerBeanInterface pricerBean;
    @EJB
    private CountryFacadeLocal countryFacade; 
    @EJB
    private UserManagerBeanInterface userManagerBean;
    @EJB
    private RouteManagerBeanInterface routeManagerBean;
    
    private HttpSession session;
 
    
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
                
        session = request.getSession(true);
        
        if(userPath.equals("/booking"))
        {
            String route_id = request.getParameter("id");
            
            session.setAttribute("route", routeManagerBean.getRouteById(route_id));
            
            if(request.getUserPrincipal() != null)
            {
                UserCredential user = userManagerBean.getUserByUsername(request.getUserPrincipal().getName());
                
                session.setAttribute("user", user);
            }
            
            request.setAttribute("CountriesList", countryFacade.findAll());
            
            request.setAttribute("tax", PricerBeanInterface.TAX);
                                            
            request.getRequestDispatcher("/WEB-INF/view/booking/booking.jsp").forward(request, response);
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
        
        session = request.getSession(true);

        if(userPath.equals("/confirm"))
        {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String zip_code = request.getParameter("zip_code");
            String country = request.getParameter("country");
            String mobilenumber_code = request.getParameter("mobilenumber_code");
            String mobilenumber = request.getParameter("mobilenumber");
            String passengers = request.getParameter("passengers");
            String luggages = request.getParameter("luggages");
            
            Route route = (Route) session.getAttribute("route");
            
            if(request.getUserPrincipal() == null) //se l'utente non è loggato crealo ed inseriscilo nella sessione
            {
                UserCredential user = new UserCredential();
                
                user.setUsername(username);
                user.setPassword(utilBean.hashPassword(password));
                user.setEmail(email);
                user.setName(name);
                user.setSurname(surname);
                user.setAddress(address);
                user.setCity(city);
                user.setZip_code(zip_code);
                user.setCountry(country);
                user.setFidelity_points(0);
                
                user.setPhoneNumbers(new LinkedList<PhoneNumber>());
                                
                user.getPhoneNumbers().add(new PhoneNumber("Mobile", mobilenumber_code, mobilenumber));
                
                user.setReservationsList(new LinkedList<Reservation>());

                session.setAttribute("user", user);
            }
                                    
            session.setAttribute("passengers", Integer.parseInt(passengers));
            session.setAttribute("luggages", Integer.parseInt(luggages));
                        
            session.setAttribute("price", (double)(route.getPrice()*Integer.parseInt(passengers) + 
                                                   Integer.parseInt(luggages)*PricerBeanInterface.TAX));
                         
            request.getRequestDispatcher("/WEB-INF/view/booking/confirm.jsp").forward(request, response);
        }
        
        if(userPath.equals("/commit"))
        {
            String id_committed_reservation;
            String points = null;   
            
            if(StringUtils.isNotEmpty(request.getParameter("points")))
            {
                points = request.getParameter("points");
            }
            else
            {
                points = "0";
            } 
             
            double final_price = pricerBean.calculateDiscountPrice((Double)session.getAttribute("price"), 
                                                                   Integer.parseInt(points));
            
            id_committed_reservation = bookingBean.purchase((Route) session.getAttribute("route"), 
                                                            (UserCredential) session.getAttribute("user"), 
                                                            (Integer) session.getAttribute("passengers"), 
                                                  (Integer) session.getAttribute("luggages"), 
                                                            final_price,
                                                            Integer.parseInt(points));
            
            if(id_committed_reservation != null)
            {                
                request.setAttribute("id_reservation", id_committed_reservation);
                
                session.removeAttribute("user");
                session.removeAttribute("route");
                session.removeAttribute("passengers");
                session.removeAttribute("luggages");
                session.removeAttribute("price");
                
                request.getRequestDispatcher("/WEB-INF/view/booking/success.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("/WEB-INF/view/booking/error.jsp").forward(request, response);
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

//    private BookingManagerBeanInterface lookupBookingBeanLocal() 
//    {
//        try 
//        {
//            Context c = new InitialContext();
//            return (BookingManagerBeanInterface) c.lookup("java:global/volaconnoi-webapp-ear/volaconnoi-webapp-ejb-1.0-SNAPSHOT/BookingManagerBean!it.volaconnoi.logic.BookingManagerBeanInterface");
//        } 
//        catch (NamingException ne) 
//        {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
//            throw new RuntimeException(ne);
//        }
//    }
}
