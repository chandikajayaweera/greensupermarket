<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Dashboard</title>
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
        <h1>Employee: ${employee.employeeFname} ${employee.employeeLname} <button onclick="location.href='controller?action=logout'">Logout</button></h1>
        
        <br><br>
        <table>
            <tr>
                <th colspan="2"><u>MenuBar</u></th>
            </tr>
            <tr>
                <th>Products</th>
                <td><button onclick="location.href='controller?action=products'">View all</button></td>
            </tr>
            <tr>
                <th>Units</th>
                <td><button onclick="location.href='controller?action=units'">View all</button></td>
            </tr>
            <tr>
                <th>Categories</th>
                <td><button onclick="location.href='controller?action=categories'">View all</button></td>
            </tr>
            <tr>
                <th>Sub-Categories</th>
                <td><button onclick="location.href='controller?action=subcategories'">View all</button></td>
            </tr>
            <tr>
                <th>Customers</th>
                <td><button onclick="location.href='controller?action=customers'">View all</button></td>
            </tr>            
        </table>
    </body>
</html>