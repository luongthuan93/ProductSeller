<%-- 
    Document   : cart
    Created on : Oct 19, 2020, 5:15:15 PM
    Author     : NO1
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart Page</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 5px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h2>List of your cart</h2>
        
        <h3 id="dateInHtml"></h3>
        <script>
            n =  new Date();
            y = n.getFullYear();
            m = n.getMonth()+1;
            d = n.getDate();
            document.getElementById("dateInHtml").innerHTML ="Order Date: "+ m + "/" + d + "/" + y;
        </script>
        <table>
            <tr>
                <th>Name: </th>           
                <th>Price: </th>
                <th>Quantity: </th>
                <th></th>
            </tr>
            <mvc:form method="POST" modelAttribute="CartInfoForm" action="${pageContext.request.contextPath}/cart">

                <c:forEach items="${CartInfo.cartItems}" var="item" varStatus="count">
                    <tr>

                        <td>${item.name}</td>
                        <td>${item.price} $</td>
                        <td>
                            <input hidden name="cartItems[${count.index}].productId" value="${item.productId}">
                            <input name="cartItems[${count.index}].quantity" value="${item.quantity}">
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/removeCartItem/${item.productId}">Delete</a>
                            
                        </td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td colspan="3">
                            Total:
                        </td>
                        <td>
                            ${TotalPrice} $
                        </td>
                    </tr>
            </table>
            <br>
            <input type="submit" value="Update Quantity">

        </mvc:form>
        <button onclick="location.href = '<c:url value="/home"></c:url>'">Back to home</button>
        <button onclick="location.href = '<c:url value="/orderSubmit"></c:url>'">Continue Order Submit</button>
    </body>
</html>
