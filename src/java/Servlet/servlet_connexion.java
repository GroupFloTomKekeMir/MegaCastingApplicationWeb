/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import entites.Personne;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kévin
 */
@WebServlet(name = "servlet_connexion", urlPatterns = {"/servlet_connexion"})
public class servlet_connexion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
            throws ServletException, IOException {
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
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
                        
		}
		
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
                
		String url = "jdbc:mysql://localhost/test";
		Connection cnx = null;
		
		try {			
			cnx = DriverManager.getConnection(url, "root", "");
			System.out.println("Connexion réussie !");
                        
             String mail = request.getParameter("mail");        
               
            dao.PersonneDAO personneDAO = new dao.PersonneDAO();
            Personne personne = personneDAO.trouver(cnx,mail);
            //
            if(personneDAO == null) {
               PrintWriter out = response.getWriter();
               out.println("NOK");
               out.close();
            } else {
                
                request.setAttribute("personne",personne ); // puissant

             //  request.setAttribute("personnes",dao.PersonneDAO.(cnx));
           
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
           
                
            }
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            finally{
        
        
                    if(cnx != null){
                            try{
                                cnx.close();
                               }
                            catch(SQLException ex){  }   
                    }
           }
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
