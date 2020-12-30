<%-- 
    Document   : home
    Created on : Sep 29, 2020, 10:41:11 AM
    Author     : NO1
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h1>List Product: </h1>
        <div>
            <c:if test="${message != null && message !=''}">
                <div style="color: red">${message}</div>
                <br>
            </c:if>
        </div>
        <button onclick="location.href='<c:url value="/create-product"/>'">Create new product</button>
        
<!--        public ProductEntity(int id, String name, String description, String price, Date publishDate, boolean active, CategoryEntity category, ImageEntity image)-->
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Publish Date</th>
                <th>Active</th>
                <th>Category</th>
                <th>Image</th>
                <th></th>
                <th></th>
                
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.publishDate}</td>
                    <td>${product.active}</td>
                    <td>${product.category.name}</td>
                    <td>
                        <c:forEach items="${product.images}" var="image">
                            <img src="${pageContext.request.contextPath}/resources/images/${image.name}" style="width: 200px;"> 
                        <br>
                        ${image.name}
                        </c:forEach>
                    </td>  
                    <td>
                        <button onclick="location.href='<c:url value="/update-product/${product.id}"></c:url>'">Update</button>
                        <button onclick="location.href='<c:url value="/delete-product/${product.id}"></c:url>'">Delete</button>
                        
                    </td>
                    <td>
                        <button onclick="location.href='<c:url value="/add-to-cart/${product.id}"></c:url>'">Add to cart</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
                    