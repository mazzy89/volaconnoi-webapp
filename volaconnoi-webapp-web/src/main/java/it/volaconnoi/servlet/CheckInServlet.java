/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.servlet;

import it.volaconnoi.entity.CheckIn;
import it.volaconnoi.entity.Reservation;
import it.volaconnoi.logic.CheckInFacadeLocal;
import it.volaconnoi.logic.ReservationManagerBeanInterface;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mazzy
 */
@WebServlet(name = "CheckInServlet", urlPatterns = {"/checkin", "/CheckinConfirm"})
public class CheckInServlet extends HttpServlet 
{
    @EJB
    private CheckInFacadeLocal checkInFacade;
    @EJB
    private ReservationManagerBeanInterface reservationBean;
        
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
        
        if(userPath.equals("/checkin"))
        {           
            request.getRequestDispatcher("/WEB-INF/checkin/checkin.jsp").forward(request, response);
        }
        
        if(userPath.equals("/CheckinConfirm"))
        {
            String reservId = request.getParameter("reservId");
            
            CheckIn ci = new CheckIn();
            ci.setId(reservId);
            
            checkInFacade.create(ci);
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
        String reserv_id = request.getParameter("reserv_id");
                        
        List<Reservation> reserv = reservationBean.getValidReservation(StringUtils.upperCase(reserv_id));
        
        if(reserv != null)
        {
            request.setAttribute("reserv", reserv.get(0));
        }
        else
        {
            request.setAttribute("notfoundreserv", "Nessuna prenotazione corrispondete al PNR inserito!");
        }
        
        request.getRequestDispatcher("/WEB-INF/checkin/checkin.jsp").forward(request, response);        
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
