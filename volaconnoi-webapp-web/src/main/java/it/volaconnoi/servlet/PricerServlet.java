/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.servlet;

import com.google.gson.Gson;

import it.volaconnoi.logic.PricerBeanInterface;
import it.volaconnoi.logic.RouteManagerBeanInterface;
import java.io.IOException;
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
@WebServlet(name = "PricerServlet", urlPatterns =
    {
        "/pricer",
        "/fidelity"     
})
public class PricerServlet extends HttpServlet
{
    @EJB
    private RouteManagerBeanInterface routeManagerBean;
    @EJB
    private PricerBeanInterface pricerBean;
    
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
        
        if(userPath.equals("/pricer"))
        {
            String passengers = request.getParameter("passengers");
            String luggages = request.getParameter("luggages");
            String price = request.getParameter("start_price");

            String price_update = new Gson().toJson(this.pricerBean.calculatePriceByPassenger(Integer.parseInt(passengers), 
                                                                                              Integer.parseInt(luggages), 
                                                                                              Float.parseFloat(price)));

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(price_update);   
            response.getWriter().flush();
        }
        
        if(userPath.equals("/fidelity"))
        {
            String price = request.getParameter("start_final_price");
            String points = request.getParameter("points");
                 
            String price_update = new Gson().toJson(this.pricerBean.calculateDiscountPrice(Double.parseDouble(price.replace(",", ".")), 
                                                                                           Integer.parseInt(points)));
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(price_update);
            response.getWriter().flush();
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
