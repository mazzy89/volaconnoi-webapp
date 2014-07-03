/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.volaconnoi.entity.Airport;
import it.volaconnoi.entity.Route;
import it.volaconnoi.logic.AirportFacadeLocal;
import it.volaconnoi.logic.RouteManagerBeanInterface;
import it.volaconnoi.miscellaneous.AirportSerializer;
import it.volaconnoi.miscellaneous.RouteSerializer;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "GeneralInfoServlet", urlPatterns = {"/info/", 
                                                        "/info/airports", 
                                                        "/info/getcitiesbycountry", 
                                                        "/info/getairportbyname",
                                                        "/info/rates",
                                                        "/info/times",
                                                        "/info/getairportsdest",
                                                        "/info/getroutestimes"})
public class GeneralInfoServlet extends HttpServlet 
{
    @EJB
    private RouteManagerBeanInterface routeManagerBean;
    @EJB
    private AirportFacadeLocal airportFacade;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {                 
        String userPath = request.getServletPath();
        
        if(userPath.equals("/info/"))
        {
            request.getRequestDispatcher("/WEB-INF/view/generalinfo/info.jsp").forward(request, response);
        }
         
        if(userPath.equals("/info/airports"))
        {
            List<Airport> countries_airports = airportFacade.getAirportCountries();
            
            request.setAttribute("CountriesList", countries_airports);
            
            request.getRequestDispatcher("/WEB-INF/view/generalinfo/airports.jsp").forward(request, response);
        }
        
        if(userPath.equals("/info/getcitiesbycountry"))
        {
            String country = request.getParameter("country");
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Airport.class, new AirportSerializer()).create();
            
            List<Airport> list = airportFacade.getCitiesByAirportCountry(country);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(list)); 
            response.getWriter().flush();         
        }    
        
        if(userPath.equals("/info/getairportbyname"))
        {
            String name_airport = request.getParameter("name");
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Airport.class, new AirportSerializer()).create();
            
            List<Airport> list = airportFacade.getAirportCoordinatesByName(name_airport);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(list));
            response.getWriter().flush();
        }
        
        if(userPath.equals("/info/rates"))
        {
            Map<String,Float> map = routeManagerBean.getCityAirportByLowestRate();
                        
            request.setAttribute("city_airport_lowest", map);
            
            request.getRequestDispatcher("/WEB-INF/view/generalinfo/rates.jsp").forward(request, response);
        }
        
        if(userPath.equals("/info/times"))
        {
            List<String> list = routeManagerBean.getDistinctAirportCitySource();
            
            request.setAttribute("airport_source_list", list);
            
            request.getRequestDispatcher("/WEB-INF/view/generalinfo/times.jsp").forward(request, response);
        }
        
        if(userPath.equals("/info/getairportsdest"))
        {
            String airport_name_source = request.getParameter("airport");
            
            List<String> list = routeManagerBean.getDistinctAirportCityDest(airport_name_source);
            
            Gson gson = new Gson();
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(list));   
            response.getWriter().flush();
        }
        
        if(userPath.equals("/info/getroutestimes"))
        {
            String airport_source = request.getParameter("airport_source");
            String airport_dest = request.getParameter("airport_dest");
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Route.class, new RouteSerializer()).create();
            
            List<Route> route_list = routeManagerBean.getListTimesByAirportSourceAirportDest(airport_source, airport_dest);
                    
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(route_list));    
            response.getWriter().flush();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
