/*
    Document   : eBooksStoreAdmineBooksServlet.java
    Author     : gheorgheaurelpacurar   
    Copyright  : gheorgheaurelpacurar
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
 * @author gheor
 */
public class eBooksStoreAdminEBooksServlet extends HttpServlet {

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
            if (request.getParameter("admin_ebooks_insert") != null) { // insert values from fields
                // set connection paramters to the DB
                // read values from page fields
                String isbn = request.getParameter("admin_ebooks_isbn");
                String denumire = request.getParameter("admin_ebooks_denumire");
                String price = request.getParameter("admin_ebooks_price");
                String pages = request.getParameter("admin_ebooks_pages");
                String id_type = request.getParameter("admin_ebooks_id_type");
                String id_genre = request.getParameter("admin_ebooks_id_genres");
                String id_quality = request.getParameter("admin_ebooks_id_paper_qualities");
                
                // declare specific DBMS operationsvariables
                ResultSet resultSet = null;
                Statement statement = null;
                Connection connection = null;
                PreparedStatement pstmnt = null;
                try
                { 
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                    String DML = "INSERT INTO EBOOKS.EBOOKS (ISBN, DENUMIRE, PAGES, PRET) VALUES (?, ?, ?, ?)";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setString(1, isbn);
                    pstmnt.setString(2, denumire);
                    pstmnt.setInt(3, Integer.valueOf(pages));
                    pstmnt.setInt(4, Integer.valueOf(price));
//                    pstmnt.setInt(5, Integer.valueOf(id_quality));
//                    pstmnt.setInt(6, Integer.valueOf(id_genre));
//                    pstmnt.setInt(7, Integer.valueOf(price));
                    pstmnt.executeUpdate();
                    
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    // display a message for NOT OK
                    Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (statement != null)
                    {
                        try
                        {
                            statement.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (connection != null)
                    {
                        try
                        {
                            connection.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminEBooksPage.jsp").forward(request, response);
                }
            }  // check push on Update button
            else if (request.getParameter("admin_ebooks_update") != null){ // update
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
                    String[] selectedCheckboxes = request.getParameterValues("admin_ebooks_checkbox");
                    String isbn = request.getParameter("admin_ebooks_isbn");
                    String denumire = request.getParameter("admin_ebooks_denumire");
                    String pages = request.getParameter("admin_ebooks_pages");
                    String price = request.getParameter("admin_ebooks_price");
//                    String id_type = request.getParameter("admin_ebooks_id_type");
//                    String id_paper_qualities = request.getParameter("admin_ebooks_id_paper_qualities");
//                    String id_genres = request.getParameter("admin_ebooks_id_genres");

                        // operate updates for all selected rows
                    if(!("".equals(isbn))){
                    connection.setAutoCommit(false);
                    for(String s : selectedCheckboxes){
                            // realize update of all selected rows
                            String DML = "UPDATE EBOOKS.EBOOKS SET ISBN=?, DENUMIRE=?, PAGES=?, PRET=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setString(2, denumire);
                            pstmnt.setInt(3, Integer.valueOf(pages));
                            pstmnt.setInt(4, Integer.valueOf(price));
                            pstmnt.setString(5, s);
                            pstmnt.execute();
                        }
                        connection.commit();
                        connection.setAutoCommit(true);
                    }
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    // display a message for NOT OK
                    Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (statement != null)
                    {
                        try
                        {
                            statement.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (connection != null)
                    {
                        try
                        {
                            connection.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminEBooksPage.jsp").forward(request, response);
                }
            } // check push on Delete button
            else if (request.getParameter("admin_ebooks_delete") != null) { // delete 
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
                    String[] selectedCheckboxes = request.getParameterValues("admin_ebooks_checkbox");
                    // more critical DB operations should be made into a transaction
                    connection.setAutoCommit(false);
                    for(String s : selectedCheckboxes){
                        // realize delete of all selected rows
                        String ISBN = s;
                        String DML = "DELETE FROM EBOOKS.EBOOKS WHERE ISBN=?";
                        pstmnt = connection.prepareStatement(DML);
                        pstmnt.setString(1, ISBN);
                        pstmnt.execute();
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                    if (connection != null){
                        try {
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex1);
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
                            Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (connection != null){
                        try
                        {
                            connection.setAutoCommit(true);
                        }
                        catch (SQLException ex){                          
                            Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        finally{
                            try {
                                connection.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(eBooksStoreAdminEBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminEBooksPage.jsp").forward(request, response);
                }
            } // check push on Cancel button
            else if (request.getParameter("admin_ebooks_cancel") != null){ // cancel
                request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
            } // check push on admin user roles button
//            else if (request.getParameter("admin_userroles_open") != null) { // insert values from fields
//                request.getRequestDispatcher("./eBooksStoreAdminUserRolesPage.jsp").forward(request, response);
//            }            
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
        return "Servlet serves eBooksStoreAdminEBooks.JSP page";
    }// </editor-fold>

}
