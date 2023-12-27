<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>JSP Page</title>
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
                return;
            }
            
            Object customerorder = session.getAttribute("customerorder");
            
            if (!(customerorder instanceof List)) {
            response.sendRedirect("controller?action=orders");
                return;
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
                <h1>Customer Orders</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Payment ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerOrder" items="${customerorder}">
                            <tr>
                                <td>${customerOrder.customerOrderID}</td>
                                <td>${customerOrder.customerOrderDate}</td>
                                <td>${customerOrder.customerOrderStatus}</td>
                                <td>${customerOrder.paymentID}</td>
                                <td><button onclick="location.href = 'controller?action=invoice&orderid=${customerOrder.customerOrderID}'">View invoice</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </body>
</html>
