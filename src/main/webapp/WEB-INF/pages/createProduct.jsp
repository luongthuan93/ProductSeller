<%-- 
    Document   : createProduct
    Created on : Sep 30, 2020, 4:25:18 PM
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
        <h1>Product</h1>

        <!--    <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Publish Date</th>
                <th>Active</th>
                <th>Category</th>
                <th>Image</th>
            </tr>-->

        <mvc:form action="${pageContext.request.contextPath}/${action}" method="POST" enctype="multipart/form-data">

            <c:if test="${action == 'update'}">
                <input hidden name="product.id" value="${productUpdate.id}">
            </c:if>
            <table>

                <tr>
                    <td>Name: </td>
                    <td><input name="product.name" value="${productUpdate.name}"></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><textarea name="product.description" rows="3">${productUpdate.description}</textarea></td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td><input type="number" name="product.price" value="${productUpdate.price}"></td>
                </tr>
                <tr>
                    <td>Publish Date: </td>
                    <td><input name="product.publishDate" type="date" value="${productUpdate.publishDate}"></td>
                </tr>
                <tr>
                    <td>Active: </td>
                    <td><input type="text" name="product.active" value="${productUpdate.active}"></td>
                </tr>
                <tr>
                    <td>Category: </td>
                    <td>

                        <select name="product.category.id">
                            <c:forEach items="${categories}" var="c"> 
                                <c:if test="${productUpdate.category.id == c.id}">
                                    <option value="${c.id}" selected>${c.name}</option>
                                </c:if>
                                <c:if test="${productUpdate.category.id != c.id}">
                                    <option value="${c.id}" >${c.name}</option>
                                </c:if>    
                            </c:forEach>
                        </select>

                    </td>
                </tr>
                <tr>
                    <c:if test="${action != 'update'}">
                        <td>Upload File: </td>
                        <td>                      
                            <input type="file" name="file">                        
                        </td>
                    </c:if>
                    <c:if test="${action == 'update'}">
                        <td>Image: </td>
                    
                    <td>
                        <input hidden name="product.image.id" value="${productUpdate.image.id}">
                        <input hidden name="product.image.name" value="${productUpdate.image.name}">
                        <img src="${pageContext.request.contextPath}/resources/images/${productUpdate.image.name}" style="
                                 width: 200px;"/>                        
                    </td>
                    </c:if>    

                </tr>
                <tr>
                    <td>
                        <c:if test="${action == 'update'}">
                            <button type="submit">Update Product</button>
                        </c:if>
                        <c:if test="${action == 'add'}">
                            <button type="submit">Create Product</button>
                        </c:if>   

                    </td>
                </tr>
            </table>
        </mvc:form>

    </body>
</html>
