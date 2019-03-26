<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eBooksStore Management </title>
         <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
    </head>
    <body>
        <h1>Manage the books from Electronic Books Store</h1>
        <form action="${pageContext.request.contextPath}/eBooksStoreAdminEBooks">
            <%-- test if actual user is authenticated and authorized --%>
        <c:choose>
                <c:when test="${validUser == true}">   
                    <!-- include menu -->
                    <%@ include file="./utils/eBooksStoreMenu.jsp" %>
                    <%-- Master view --%>
                        <form action="${pageContext.request.contextPath}/eBooksStoreAdminEbooksServlet" method="POST">                       
                        <sql:setDataSource 
                        var="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/EbookStore5;create=true;"
                        user="ebooks"  
                        password="ebooks"/>
                        <sql:query dataSource="${snapshot}" var="result">
                            SELECT ISBN, DENUMIRE, ID_TYPE, ID_QUALITY, PAGES, ID_GENRE, PRET FROM EBOOKS.EBOOKS
                        </sql:query>
                        <table border="1" width="100%">
                            <tr>
                            <td width="4%" class="thc"> select </td>   
                            <td width="12%" class="thc"> ISBN </td>  
                            <td width="12%" class="thc">DENUMIRE</td>
                            <td width="12%" class="thc">PAGES</td>
                            <td width="12%" class="thc">PRET</td>
                        </table>    
                        <table border="1" width="100%">    
                            </tr>
                            <c:forEach var="row" varStatus="loop" items="${result.rows}">
                            <tr>
                                <td width="4%" class="tdc"><input type="checkbox" name="admin_ebooks_checkbox" value="${row.isbn}"></td>
                                <td width="12%" class="tdc"><c:out value="${row.isbn}"/></td>
                                <td width="12%" class="tdc"><c:out value="${row.denumire}"/></td>
                                <td width="12%" class="tdc"><c:out value="${row.pages}"/></td>
                                <td width="12%" class="tdc"><c:out value="${row.pret}"/></td>
                            </tr>
                            </c:forEach>
                        </table>
                        <%-- Details --%>
                        <sql:setDataSource 
                        var="snapshotgenres" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/EbookStore5;create=true;"
                        user="ebooks"  
                        password="ebooks"/>
                        <sql:query dataSource="${snapshotgenres}" var="resultgenres">
                            SELECT ID, GENRE FROM EBOOKS.BOOK_GENRES 
                        </sql:query>
                        <sql:setDataSource 
                        var="snapshotpaperqualities" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/EbookStore5;create=true;"
                        user="ebooks"  
                        password="ebooks"/>
                        <sql:query dataSource="${snapshotpaperqualities}" var="resultpaperqualities">
                            SELECT ID, QUALITY FROM EBOOKS.BOOK_PAPER_QUALITIES 
                        </sql:query>    
                        <sql:setDataSource 
                        var="snapshottypes" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/EbookStore5;create=true;"
                        user="ebooks"  
                        password="ebooks"/>
                        <sql:query dataSource="${snapshottypes}" var="resulttypes">
                            SELECT ID, TYPE FROM EBOOKS.BOOK_TYPES 
                        </sql:query>    
                        <table class="tablecenterdwithborder">
                            <tr>
                                <td>    
                                    <table>
                                        <tr>
                                            <td> ISBN: </td>
                                            <td> <input type="text" name="admin_ebooks_isbn"></input></td>
                                        </tr>                                        
                                        <tr>
                                            <td> DENUMIRE: </td>
                                            <td> <input type="text" name="admin_ebooks_denumire"></input></td>
                                        </tr>
                                        <tr>
                                            <td> PAGES NO: </td>
                                            <td> <input type="text" name="admin_ebooks_pages"></input></td>
                                        </tr>
                                        <tr>
                                            <td> PRICE: </td>
                                            <td> <input type="text" name="admin_ebooks_price"></input></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <%-- buttons --%>
                                    <table>

                                            <tr><td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_insert" value="Insert"></td> 
                                                <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_update" value="Update"></td>
                                                <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_delete" value="Delete"></td> 
                                                <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_ebooks_cancel" value="Cancel"></td>
                                            </tr>  
                                    </table>
                                </td>
                            </tr>
                        </table>    
                        </form>
            </c:when>
            <c:otherwise>
                <c:redirect url="./Index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
        </form>
    </body>
</html>

