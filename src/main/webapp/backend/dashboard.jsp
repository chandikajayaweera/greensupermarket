<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
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
                    <li><a href="controller?action=customerfeedback">Customer Feedback</li>
                    <li><a href="controller?action=employees">Employees</a></li>
                </ul>
            </div>
            <div id="content">
                <!-- Your main content goes here -->
                <h1>Employee: ${employee.employeeFname} ${employee.employeeLname}<button onclick="location.href = 'controller?action=logout'">Logout</button></h1>
                <p>This is the main content area.</p>
            </div>
        </div>
    </body>
</html>