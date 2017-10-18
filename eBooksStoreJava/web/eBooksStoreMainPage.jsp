<%-- 
    Document   : eBooksStoreMainPage.jsp
    Author     : gheorgheaurelpacurar
    Copyright  : gheorgheaurelpacurar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main page</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
    </head>
    <body>   
        <c:choose>
            <c:when test="${validUser == true}">
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
            </c:when>
            <c:otherwise>
                <c:redirect url="./index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
    </body> 
</html>
