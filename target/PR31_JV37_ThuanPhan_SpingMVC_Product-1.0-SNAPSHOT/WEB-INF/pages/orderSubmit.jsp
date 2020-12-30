<%-- 
    Document   : OrderSubmit
    Created on : Oct 20, 2020, 4:46:21 PM
    Author     : NO1
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Order Submit Information</h1>
        <mvc:form action="${pageContext.request.contextPath}/orderSubmit" modelAttribute="Order" method="POST">
            <table>
                <tr>
                    <td>Date: </td>
                    <td>
                        
                        <input type="date" id="dateInHtml" name="orderDate">
                        <script>
                            n =  new Date();
                            y = n.getFullYear();
                            m = n.getMonth()+1;
                            d = n.getDate();
                            document.getElementById("dateInHtml").defaultValue = y + "-" + m + "-" + d;
                            
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td>
                        <input type="text" name="name">
                    </td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td>
                        <input type="text" name="address">
                    </td>
                </tr>
                <tr>
                    <td>Phone </td>
                    <td>
                        <input type="tel" name="phone">
                    </td>
                </tr>
            </table>
            <input type="submit" value="Submit Order">
        </mvc:form>

    </body>
</html>
