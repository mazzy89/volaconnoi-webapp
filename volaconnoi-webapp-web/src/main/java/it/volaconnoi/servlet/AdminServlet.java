
package it.volaconnoi.servlet;

import it.volaconnoi.logic.AirlaneFacadeLocal;
import it.volaconnoi.logic.AirportFacadeLocal;
import it.volaconnoi.logic.RouteManagerBeanInterface;
import it.volaconnoi.entity.Reservation;
import it.volaconnoi.entity.Route;
import it.volaconnoi.logic.PricerBeanInterface;
import java.io.IOException;
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
@WebServlet(name = "AdminServlet", urlPatterns =
    {
        "/admin/",
        "/admin/addroute",
        "/admin/showinforoutes",
        "/admin/showreservations"
})
//@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class AdminServlet extends HttpServlet
{
    @EJB
    private PricerBeanInterface pricerBean;
    @EJB
    private AirlaneFacadeLocal airlaneFacade;
    @EJB
    private AirportFacadeLocal airportFacade;
    @EJB
    private RouteManagerBeanInterface routeManagerBean;


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
        
        if(userPath.equals("/admin/"))
        {
            request.getRequestDispatcher("/WEB-INF/adminarea/adminarea.jsp").forward(request, response);
        }
        
        if(userPath.equals("/admin/addroute"))
        {
            initAddRoute(request, response);
            
            request.getRequestDispatcher("/WEB-INF/adminarea/adminaddroute.jsp").forward(request, response);
        }
        
        if(userPath.equals("/admin/showinforoutes"))
        {            
            request.setAttribute("routes_list", routeManagerBean.getRoutesList());
            
            request.getRequestDispatcher("/WEB-INF/adminarea/adminshowinforoutes.jsp").forward(request, response);
        }
        
        if(userPath.equals("/admin/showreservations"))
        {                                   
            String id_route = request.getParameter("id");

            Route route = routeManagerBean.getRouteById(id_route);
            List<Reservation> reservations_list = route.getReservationsList();
            float total_builin_reserv = pricerBean.calculateBuiltInByRoute(reservations_list);

            request.setAttribute("route", route);
            request.setAttribute("reservations_list", reservations_list);
            request.setAttribute("total_builin_reserv", total_builin_reserv);

            request.getRequestDispatcher("/WEB-INF/adminarea/adminshowinforeservation.jsp").forward(request, response);
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
        
        if(userPath.equals("/admin/addroute"))
        {
            String airlane = request.getParameter("airlane");
            String aircraft_id = request.getParameter("aircraft_id");
            
            String airport_city_source = request.getParameter("airport_city_source");
            String airport_city_dest = request.getParameter("airport_city_dest");
            
            String departure_date = request.getParameter("from");
            String arrival_date = request.getParameter("to");
            
            String departure_hour = request.getParameter("departure_hour");
            String departure_minutes = request.getParameter("departure_minutes");
            
            String arrival_hour = request.getParameter("arrival_hour");
            String arrival_minutes = request.getParameter("arrival_minutes");
            
            String class_travel = request.getParameter("class_travel");
            String seats = request.getParameter("seats");
            String rate = request.getParameter("rate");
            
            routeManagerBean.addRoute(airlane, 
                                      aircraft_id, 
                                      airport_city_source, 
                                      airport_city_dest, 
                                      departure_date, 
                                      arrival_date, 
                                      departure_hour, 
                                      departure_minutes, 
                                      arrival_hour, 
                                      arrival_minutes,
                                      class_travel, 
                                      seats, 
                                      rate);
            
            initAddRoute(request, response);
            
            request.setAttribute("addSuccess", "Il volo è stato aggiunto correttamente al sistema!");
            
            request.getRequestDispatcher("/WEB-INF/adminarea/adminaddroute.jsp").forward(request, response);    
        }
    }

    public void initAddRoute(HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute("airlanes_active_list", airlaneFacade.getAirlanesActiveList());
            
        request.setAttribute("airports_list", airportFacade.getAirportsListOrderByCity());
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
    }// </editor-fold>
}
