<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
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
        <h1>Customer Page</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Fname</th>
                    <th>Customer Lname</th>
                    <th>Customer email</th>
                    <th>Customer Pnumber</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.customerID}</td>
                        <td>${customer.customerFname}</td>
                        <td>${customer.customerLname}</td>
                        <td>${customer.customerEmail}</td>
                        <td>${customer.customerPnumber}</td>
                        <td><form action="controller?action=customers&query=delete&customerid=${customer.customerID}" method="POST">
                                <button type="submit">Delete</button>
                            </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <br><br>

        <h2>Add customer</h2>
        <form action='controller' method="POST">
            <input type="hidden" name="action" value="customers">
            <input type="hidden" name="query" value="add">
            Enter customer Fname: <input type="text" name="customerfname">
            Enter customer Lname: <input type="text" name="customerlname">
            Enter customer Email: <input type="email" name="customeremail">
            Enter customer pnumber: <input type="text" name="customerpnumber">
            Enter customer password: <input type="password" name="customerpassword">
            <button type="submit">Submit</button>
        </form>        
        
    </body>
</html>
