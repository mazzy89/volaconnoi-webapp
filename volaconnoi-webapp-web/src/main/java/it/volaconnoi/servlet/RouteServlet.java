/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.servlet;

import it.volaconnoi.entity.Route;
import it.volaconnoi.logic.RouteManagerBeanInterface;
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
@WebServlet(name = "ReservationServlet", urlPatterns =
    {
        "/search",
        "/result"
})
public class RouteServlet extends HttpServlet
{
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
        
        if(userPath.equals("/search"))
        {
            request.getRequestDispatcher("/WEB-INF/view/route/search.jsp").forward(request, response);
        }
        
        if(userPath.equals("/result"))
        {
            String source = request.getParameter("source");
            String destination = request.getParameter("destination");
            
            String from = request.getParameter("from");
            String travel_class = request.getParameter("travel_class");
            
            String date_flexi = request.getParameter("date_flexi");
                        
            List<Route> routes_list = routeManagerBean.getRoutesByInputParameters(source, 
                                                                                  destination, 
                                                                                  from, 
                                                                                  travel_class,
                                                                                  date_flexi);
            
            request.setAttribute("routes_list", routes_list); 
            request.setAttribute("duration_list", routeManagerBean.getDurationRoutes(routes_list));
            
            request.getRequestDispatcher("/WEB-INF/view/route/result.jsp").forward(request, response);
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
