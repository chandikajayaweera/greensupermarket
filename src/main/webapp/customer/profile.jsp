<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>Customer Profile</title>
    </head>
    <body>
        <%
            //HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //HTTP 1.0
            response.setHeader("pragma", "no-cache");
            //For proxy servers
            response.setHeader("Expires", "0"); 
	        
            if(session.getAttribute("customer") == null){
            response.sendRedirect("login?action=logout");
            }
        %>
        <jsp:include page="../template/frontend/navbar.jsp"/>
        <div id="container">
            <div id="sidebar">
                <ul>
                    <li><a href="dashboard.jsp">Dashboard</a></li>
                    <li><a href="controller?action=orders">Orders</a></li>
                    <li><a href="controller?action=profile">Profile</a></li>
                    <li><a href="controller?action=feedback">Feedback</a></li>
                </ul>
            </div>
            <div id="content">

                <!-- Your main content goes here -->
                <h1>Account information</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Email</th>
                            <th>Phone number</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${customer.customerFname}</td>
                            <td>${customer.customerLname}</td>
                            <td>${customer.customerEmail}</td>
                            <td>${customer.customerPnumber}</td>
                            <td>
                        </tr>
                    </tbody>
                </table>  

                <br><br>
                <h1>Update information</h1>   
                <form action="controller?action=profile&query=update&customerid=${customer.customerID}" method="POST">
                    <table>
                        <tr>
                            <td>First name</td>
                            <td><input type="text" value="${customer.customerFname}" name="fname"></td>
                        </tr>
                        <tr>
                            <td>Last name</td>
                            <td><input type="text" value="${customer.customerLname}" name="lname"></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input type="email" value="${customer.customerEmail}" name="email"></td>
                        </tr>
                        <tr>
                            <td>Phone number</td>
                            <td><input type="tel" value="${customer.customerPnumber}" name="pnumber"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Update"></td>
                        </tr>
                    </table>  
                </form>

                <br><br>
                <h1>Update password</h1>
                <form action="controller?action=profile&query=updatepassword&customerid=${customer.customerID}" method="POST">
                    <table>
                        <tr>
                            <td>Enter new password</td>
                            <td><input type="password" name="password"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Update"></td>
                        </tr>
                    </table>
                </form>


            </div>
        </div>        
    </body>
</html>
