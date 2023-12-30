<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <div class="columns has-text-centered has-background-primary-light">
                <aside class="column is-2 aside menu hero is-fullheight has-background-primary-dark">
                    <ul class="menu-list">
                        <li class="pb-5 mx-5"><a class="is-size-4 has-text-weight-bold has-text-white" href="controller?action=orders">Orders</a></li>
                        <li class="pb-5 mx-5"><a class="is-size-4 has-text-weight-bold has-text-white" href="controller?action=profile">Profile</a></li>
                        <li class="pb-5 mx-5"><a class="is-size-4 has-text-weight-bold has-text-white" href="controller?action=feedback">Feedback</a></li>
                        <li class="m-5"><button class="button is-size-5 has-text-weight-bold is-primary" onclick="location.href = 'login?action=logout'">Logout</button></li>
                    </ul>
                </aside>
                <div class="column is-10">
                    <div class="title p-3">
                        <h1>Customer Orders</h1>
                    </div>
                    <div class="content m-5">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Payment ID</th>
                                    <th>View Invoice</th>
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
            </div>
        </div>
    </body>
</html>
