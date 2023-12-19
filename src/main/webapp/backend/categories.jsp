<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Page</title>
    </head>
    <body>
        <%
            //HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //HTTP 1.0
            response.setHeader("pragma", "no-cache");
            //For proxy servers
            response.setHeader("Expires", "0"); 
	        
            if(session.getAttribute("employee") == null){
            response.sendRedirect("login.jsp");
            }
        %>        
        <h1>Category Page</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Category Name</th>
                    <th>Category Image</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td>${category.categoryName}</td>
                        <td><img src="${category.categoryImageURL}"></td>
                        <td><form action="controller?action=categories&query=delete&categoryname=${category.categoryName}" method="POST">
                                <button type="submit">Delete</button>
                            </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Add new Category</h2>
        <form action='controller' method="POST">
            <input type="hidden" name="action" value="categories">
            <input type="hidden" name="query" value="add">
            Enter category name: <input type="text" name="categoryname">
            Enter category Image URL: <input type="text" name="categoryimageurl">
            <button type="submit">Submit</button>
        </form>        

        <h2>Update a category</h2>
        <form action='controller' method="POST">
            <input type="hidden" name="action" value="categories">
            <input type="hidden" name="query" value="update">         
            Enter category name: <input type="text" name="categoryname">
            Enter category Image URL: <input type="text" name="categoryimageurl">
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
