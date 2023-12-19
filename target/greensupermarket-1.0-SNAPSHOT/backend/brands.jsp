<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand Page</title>
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
        <h1>Brand Page</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Brand Name</th>
                    <th>Brand Logo</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="brand" items="${brands}">
                    <tr>
                        <td>${brand.brandName}</td>
                        <td><img src="${brand.brandLogoURL}"></td>
                        <td><form action="controller?action=brands&query=delete&brandname=${brand.brandName}" method="POST">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br><br>

        <h2>Add new brand</h2>
        <form action='controller' method="POST" enctype="multipart/form-data">
            <input type="hidden" name="action" value="brands">
            <input type="hidden" name="query" value="add">
            Enter brand name: <input type="text" name="brandname"><br><br>
            Enter brand Logo: <input type="file" name="brandlogo" accept="image/*" ><br><br>
            <button type="submit">Submit</button>
        </form>
        
        <h2>Change brand Logo</h2>
        <form action='controller' method="POST">
            <input type="hidden" name="action" value="brands">
            <input type="hidden" name="query" value="update">
            Enter brand name: <input type="text" name="brandname"><br><br>
            Enter brand Logo: <input type="text" name="brandlogo"><br><br>
            <button type="submit">Submit</button>
        </form>        
    </body>
</html>
