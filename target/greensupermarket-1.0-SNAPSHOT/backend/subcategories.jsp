<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SubCategory Page</title>
    </head>
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
    <body>
        <h1>SubCategory Page</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Category type</th>
                    <th>SubCategory name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="subcategory" items="${subcategories}">
                    <tr>
                        <td>${subcategory.categoryName}</td>
                        <td>${subcategory.subCategoryName}</td>
                        <td><form action="controller?action=subcategories&query=delete&subcategoryname=${subcategory.subCategoryName}" method="POST">
                                <button type="submit">Delete</button>
                            </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>Add new SubCategory</h2>
        <form action='controller' method="POST">
            <input type="hidden" name="action" value="subcategories">
            <input type="hidden" name="query" value="add">
            Enter category type: <input type="text" name="categoryname">
            Enter Subcategory name: <input type="text" name="subcategoryname">
            <button type="submit">Submit</button>
        </form>             

        
    </body>
</html>
