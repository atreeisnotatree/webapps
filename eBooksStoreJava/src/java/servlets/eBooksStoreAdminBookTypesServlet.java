/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * @author atreeisnotatree
 */
public class eBooksStoreAdminBookTypesServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            // declare specific DB info
            String user = "ebooks" ;
            String password = "ebooks";
            String url = "jdbc:derby://localhost:1527/EbookStore5;create=true;";
            String driver = "org.apache.derby.jdbc.ClientDriver"; 
            // check push on Insert button
            if (request.getParameter("admin_book_types_insert") != null) { // insert values from fields
                // set connection paramters to the DB
                // read values from page fields
                String id = request.getParameter("admin_book_types_id");
                String type = request.getParameter("admin_book_types_type");
                // declare specific variables
                ResultSet resultSet = null;
                Statement statement = null;
                Connection connection = null;
                PreparedStatement pstmnt = null;
                try
                {
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password); 
                    // test if role field is empty & realize the insert
                    if(!("".equals(id))){
                        String DML = "INSERT INTO EBOOKS.BOOK_TYPES VALUES (?, ?)";
                        pstmnt = connection.prepareStatement(DML);
                        pstmnt.setString(1, id);
                        pstmnt.setString(2, type);
                        pstmnt.execute();
                    }

                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (statement != null)
                    {
                        try
                        {
                            statement.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (connection != null)
                    {
                        try
                        {
                            connection.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminBookTypesPage.jsp").forward(request, response);
                }
            }  // check push on Update button
            else if (request.getParameter("admin_book_types_update") != null){ // update
                // declare specific variables
                ResultSet resultSet = null;
                Statement statement = null;
                PreparedStatement pstmnt = null;
                Connection connection = null;
                try
                { 
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                    // identify selected checkbox and for each execute the update operation
                    String[] selectedCheckboxes = request.getParameterValues("admin_book_types_checkbox");
                    String id = request.getParameter("admin_book_types_id");
                    String type = request.getParameter("admin_book_types_type");
                    // if id is "" do nothing
                    if(!("".equals(id))){
                        connection.setAutoCommit(false);
                        for(String s : selectedCheckboxes){
                            // realize update of all selected rows
                            String DML = "UPDATE EBOOKS.BOOK_TYPES SET ID=?, TYPE=? WHERE ID=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setInt(1, Integer.valueOf(id));
                            pstmnt.setString(2, type);
                            pstmnt.setString(3, s);
                            pstmnt.execute();
                        }
                        connection.commit();
                        connection.setAutoCommit(true);
                    }    
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (statement != null)
                    {
                        try
                        {
                            statement.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (connection != null)
                    {
                        try
                        {
                            connection.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminBookTypesPage.jsp").forward(request, response);
                }
            } // check push on Delete button
            else if (request.getParameter("admin_book_types_delete") != null) { // delete 
                // declare specific variables
                ResultSet resultSet = null;
                PreparedStatement pstmnt = null;
                Connection connection = null;
                try
                { 
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                    // identify selected checkbox and for each execute the delete operation
                    String[] selectedCheckboxes = request.getParameterValues("admin_book_types_checkbox");
                    // multiple db critical operations should be executed into a transaction
                    connection.setAutoCommit(false);
                    for(String s : selectedCheckboxes){
                        // realize delete of all selected rows
                        String DML = "DELETE FROM EBOOKS.BOOK_TYPES WHERE ID=?";
                        pstmnt = connection.prepareStatement(DML);
                        pstmnt.setString(1, s);
                        pstmnt.execute();
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                    if (connection != null){
                        try {
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }              
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (connection != null){
                        try
                        {
                            connection.setAutoCommit(true);
                        }
                        catch (SQLException ex){                          
                            Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        finally{
                            try {
                                connection.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(eBooksStoreAdminBookTypesServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminBookTypesPage.jsp").forward(request, response);
                }
            } // check push on Cancel button
            else if (request.getParameter("admin_book_types_cancel") != null){ // cancel
                request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
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
