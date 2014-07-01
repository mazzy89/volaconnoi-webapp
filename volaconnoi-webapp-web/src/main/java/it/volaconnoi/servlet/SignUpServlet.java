/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.servlet;

import it.volaconnoi.logic.CountryFacadeLocal;
import it.volaconnoi.entity.UserCredential;
import it.volaconnoi.logic.UserManagerBeanInterface;
import it.volaconnoi.logic.UtilBeanInterface;

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
@WebServlet(name = "SignUpServlet", urlPatterns =
    {
        "/signup"
})
public class SignUpServlet extends HttpServlet
{
    @EJB
    private CountryFacadeLocal countryFacade;
    @EJB
    private UserManagerBeanInterface userManagerBean;
    @EJB
    private UtilBeanInterface utilBean;
    
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
        initSignUpServlet(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String email = request.getParameter("email");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String zipcode = request.getParameter("zip_code");
        String country = request.getParameter("country");
        String homenumber_code = request.getParameter("homenumber_code");
        String homenumber = request.getParameter("homenumber");
        String mobilenumber_code = request.getParameter("mobilenumber_code");
        String mobilenumber = request.getParameter("mobilenumber");
                    
        boolean validationErrorFlag = false;
            
        validationErrorFlag = utilBean.validateSignUpForm(username, 
                                                          password, 
                                                          confirm_password, 
                                                          email, 
                                                          name, 
                                                          surname, 
                                                          address, 
                                                          city, 
                                                          zipcode, 
                                                          country, 
                                                          mobilenumber, 
                                                          request);
            
        if(validationErrorFlag == true)
        {
            doGet(request, response);
        }
        else
        {
            UserCredential client = userManagerBean.addNewUser(username, 
                                                               password, 
                                                               email, 
                                                               name, 
                                                               surname, 
                                                               address, 
                                                               city, 
                                                               zipcode, 
                                                               country,
                                                               homenumber_code, 
                                                               homenumber,
                                                               mobilenumber_code, 
                                                               mobilenumber);
                        
            request.setAttribute("signUpSuccess", "Registrazione avvenuta con successo " + client.getUsername() + "!"   +" Tra 5 secondi sarai automaticamente reinderizzato alla home...");
            
            response.setHeader("Refresh", "5;url="+request.getContextPath()+"/j_security_check?j_username=" + username + "&j_password=" + password);
            
            request.getRequestDispatcher("/WEB-INF/view/signupsuccess.jsp").forward(request, response);
        } 
    }
    
    public void initSignUpServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("CountriesList", countryFacade.findAll());
        request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response);
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
