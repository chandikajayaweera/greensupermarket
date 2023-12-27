<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

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
                <h1>Your invoice</h1>
                Order ID: ${customerorder.customerOrderID}<br>
                Order date: ${customerorder.customerOrderDate}<br>
                Order status: ${customerorder.customerOrderStatus}<br>
                Payment ID: ${customerorder.paymentID}<br>
                <br>
                <h1>Address</h1>
                <c:if test="${not empty shippingdetails}">
                    <c:set var="firstShippingDetail" value="${shippingdetails[0]}" />
                    Recipient name: ${firstShippingDetail.recipientName}<br>
                    Address line 1: ${firstShippingDetail.line1}<br>
                    Address line 2: ${firstShippingDetail.line2}<br>
                    City: ${firstShippingDetail.city}<br>
                    State: ${firstShippingDetail.state}<br>
                    Country: ${firstShippingDetail.countryCode}<br>
                    Postal code: ${firstShippingDetail.postalCode}<br>
                </c:if>
                <br><br>

                <c:set var="orderItems" value="${sessionScope.orderitems}" />
                <c:set var="products" value="${sessionScope.products}" />
                
                <%-- Check if orderItems is not null and not empty --%>
                <c:if test="${not empty orderItems}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Product Image URL</th>
                                <th>Order Item Quantity</th>
                                <th>Order Item Unit Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderItem" items="${orderItems}">
                                <tr>
                                    <td>${orderItem.productName}</td>
                                    <td><img src="${orderItem.productImageURL}" width="150" height="150"></td>
                                    <td>${orderItem.orderItemQuantity}</td>
                                    <td>${orderItem.orderItemUnitPrice}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
