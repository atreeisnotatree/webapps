<%-- 
    Document   : eBooksStoreAdminUserRolesPage.JSP
    Author     : gheorgheaurelpacurar   
    Copyright  : gheorgheaurelpacurar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Electronic Books Store Manage Book Types Page</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
    </head>
    <body>
            <%-- test if actual user is authenticated and authorized --%>
        <c:choose>
                <c:when test="${validUser == true}">   
                    <%-- Build the table containing actual user roles and their roles in a master-detail view--%>
                    <%-- include menu --%>
                    <%@ include file="./utils/eBooksStoreMenu.jsp" %>
                    <%-- Master view --%>
                        <form action="${pageContext.request.contextPath}/eBooksStoreAdminBookTypesServlet" method="POST">
                        <sql:setDataSource 
                        var="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/EbookStore5;create=true;"
                        user="ebooks"  
                        password="ebooks"/>
                        <sql:query dataSource="${snapshot}" var="result">
                            SELECT ID, TYPE from EBOOKS.BOOK_TYPES
                        </sql:query>
                        <table border="1" width="100%">
                            <tr>
                            <td class="thc">select</td>    
                            <td class="thc">ID</td>
                            <td class="thc">TYPE</td>
                            </tr>
                            <c:forEach var="row" varStatus="loop" items="${result.rows}">
                            <tr>
                                <td class="tdc"><input type="checkbox" name="admin_book_types_checkbox" value="${row.id}"></td>
                                <td class="tdc"><c:out value="${row.id}"/></td>
                                <td class="tdc"><c:out value="${row.type}"/></td>
                            </tr>
                            </c:forEach>
                        </table>
                        <%-- Details --%>
                        <table class="tablecenterdwithborder">
                            <tr><td>
                            <table>
                                <tr>
                                    <td> ID </td>
                                    <td> <input type="text" name="admin_book_types_id"></input></td>
                                </tr>
                                <tr>
                                    <td> TYPE </td>
                                    <td> <input type="text" name="admin_book_types_type"></input></td>
                                </tr>
                            </table>
                            <%-- buttons --%>
                            <table>
                                    <tr><td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_book_types_insert" value="Insert"></td> 
                                        <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_book_types_update" value="Update"></td>
                                        <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_book_types_delete" value="Delete"></td> 
                                        <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_book_types_cancel" value="Cancel"></td>
                                    </tr>     
                            </table>
                            </td></tr>
                        </table>    
                        </form>
            </c:when>
            <c:otherwise>
                <c:redirect url="./index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>                
    </body>
    </body>
</html>
