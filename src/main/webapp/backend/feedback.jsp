<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Customer Feedback Page</title>
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
                <!-- Your main content goes here -->
                <h1>Customer Feedback</h1>
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>Feedback ID</th>
                            <th>Customer ID</th>
                            <th>Date</th>
                            <th>Rate</th>
                            <th>Message</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="eachfeedback" items="${feedback}">
                            <tr>
                                <td>${eachfeedback.feedbackID}</td>
                                <td>${eachfeedback.customerID}</td>
                                <td>${eachfeedback.feedbackDate}</td>
                                <td>${eachfeedback.feedbackRating}</td>
                                <td>${eachfeedback.feedbackMessage}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>  
                
                
                
            </div>
        </div>
    </body>
</html>
