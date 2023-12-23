<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>Customer dashboard</title>
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
                    <li><font size="30"><a href="dashboard.jsp"><b>Dash</b></a></font></li>
                    <li><a href="controller?action=feedback">Feedback</a></li>
                </ul>
            </div>
            <div id="content">
                <!-- Your main content goes here -->
                <h1>Employee: ${customer.customerFname} ${customer.customerLname}<button onclick="location.href = 'login?action=logout'">Logout</button></h1>
                <p>This is the main content area.</p>
            </div>
        </div>
    </body>
</html>
