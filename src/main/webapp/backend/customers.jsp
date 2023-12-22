<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
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
            response.sendRedirect("login?action=logout");
            }
        %> 
        <div id="container">
            <div id="sidebar">
                <ul>
                    <li><font size="30"><a href="dashboard.jsp"><b>Dash</b></a></font></li>
                    <li><a href="controller?action=units">Units</a></li>
                    <li><a href="controller?action=categories">Categories</a></li>
                    <li><a href="controller?action=subcategories">Sub Categories</a></li>
                    <li><a href="controller?action=products">Products</a></li>
                    <li><a href="controller?action=customers">Customers</a></li>
                    <li><a href="controller?action=customerorders">Customer Orders</a></li>
                    <li><a href="controller?action=customerfeedback">Customer Feedback</a></li>
                    <li><a href="controller?action=employees">Employees</a></li>
                </ul>
            </div>
            <div id="content">
                <h1>Customer Page</h1>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>Customer Fname</th>
                            <th>Customer Lname</th>
                            <th>Customer email</th>
                            <th>Customer Pnumber</th>
                            <th>Action</th>
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

                <!-- Add customers -->
                <table>
                    <form action='controller' method="POST">
                        <input type="hidden" name="action" value="customers">
                        <input type="hidden" name="query" value="add">
                        <tr>
                            <td colspan="2"><h2>Add customer</h2></td>
                        </tr>
                        <tr>
                            <td>First name:</td>
                            <td><input type="text" name="customerfname"></td>
                        </tr>
                        <tr>
                            <td>Last name:</td>
                            <td><input type="text" name="customerlname"></td>
                        </tr>
                        <tr>
                            <td>Email address:</td>
                            <td><input type="email" name="customeremail"></td>
                        </tr>
                        <tr>
                            <td>Phone number:</td>
                            <td><input type="tel" name="customerpnumber"></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="customerpassword"></td>
                        </tr>
                        <tr>
                            <td><button type="submit">Submit</button></td>
                        </tr>
                    </form> 
                </table>


            </div>
        </div>
    </body>
</html>
