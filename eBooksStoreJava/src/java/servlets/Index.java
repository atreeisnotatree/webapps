/*
    Document   : Index.java
    Author     : gheorgheaurelpacurar
    Copyright  : gheorgheaurelpacurar
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gheorgheaurelpacurar
 * Servlet class implements authentication and authorization process for eBooksStore
 * java web application. Session variable "validUser" is used to keep the value
 * of an authenticated user. The value should be true. If the variable has value false or 
 * not exist in session the user is unauthorized.
 * 
 */
public class Index extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException If Db has issues an exception will be thrown
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        // read username and password sent from the JSP
        String u = request.getParameter("authenticationpage_username");
        String p = request.getParameter("authenticationpage_password");
        // Try to create a connection and test if the user exists
        // set connection paramters to the DB
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        String user = "ebooks";
        String password = "ebooks";
        String url = "jdbc:derby://localhost:1527/EbookStore5;create=true";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        try
        {
            Class driverClass = Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String query = "SELECT NAME, PASSWORD, ROLE FROM EBOOKS.USERS WHERE NAME = '"+u+"' AND PASSWORD = '"+p+"'";
            resultSet = statement.executeQuery(query);
            boolean resultSetHasRows = resultSet.next(); 
            if (resultSetHasRows)
            {
                // save as actualUser variable the username
                request.getSession().setAttribute("actualUser", u);
                // save as actualUserRole its role
                String role = resultSet.getString("ROLE");
                request.getSession().setAttribute("actualUserRole", role);
                // create a variable to keep the authenticated user
                request.getSession().setAttribute("validUser", true);
                // delegate to JSP
                request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
            }
            else
            {
                // set validation attribute to false be sure security will not be broken
                request.getSession().setAttribute("validUser", false);
                // There is no user recorded with these username and password so we'll stay in this page
                request.getRequestDispatcher("./Index.jsp").forward(request, response);
            }
        } 
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();
        }
        finally
        {
            if (resultSet != null)
            {
                try
                {
                    resultSet.close();
                }
                catch (SQLException ex){Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);}
            }
            if (statement != null)
            {
                try
                {
                    statement.close();
                }
                catch (SQLException ex){Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);}
            }	
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException ex){Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);}
            }
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This servlet computes authentication and autorisation process";
    }// </editor-fold>

}
