<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

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
                        <h1>Invoice #${customerorder.customerOrderID}</h1>
                    </div>

                    <div class="content m-5">
                        <div class="pb-5 has-text-left m-4">
                            <strong>Order ID:</strong> ${customerorder.customerOrderID}<br>
                            <strong>Payment ID:</strong> ${customerorder.paymentID}<br>
                            <strong>Date:</strong> ${customerorder.customerOrderDate}<br><br>
                            <h4><strong>Billing Information</strong></h4>
                            <c:if test="${not empty shippingdetails}">
                                <c:forEach var="shippingDetail" items="${shippingdetails}">
                                    <strong>Recipient name:</strong> ${shippingDetail.recipientName}
                                    <br>
                                    <b>Address :</b>
                                    ${shippingDetail.line1},<br>
                                    <c:if test="${not empty fn:trim(shippingDetail.line2)}">
                                        ${shippingDetail.line2},<br>
                                    </c:if>
                                    ${shippingDetail.city}, ${shippingDetail.state}, ${shippingDetail.countryCode}, <br>
                                    ${shippingDetail.postalCode}<br>
                                    <b>Call :</b> ${customer.customerPnumber}
                                    <br>
                                    <b>E-mail :</b> ${customer.customerEmail}<br><br>

                                </c:forEach>
                            </c:if>
                        </div>

                        <c:set var="orderItems" value="${sessionScope.orderitems}" />
                        <c:set var="products" value="${sessionScope.products}" />

                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12">
                                <div class="table-responsive">
                                    <c:if test="${not empty orderItems}">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Product name</th>
                                                    <th>Quantity</th>
                                                    <th>Unit Price</th>
                                                    <th>Sub Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="billAmount" value="0" />
                                                <c:forEach var="orderItem" items="${orderItems}">
                                                    <tr>
                                                        <td>${orderItem.productName}</td>
                                                        <td>${orderItem.orderItemQuantity}</td>
                                                        <td>${orderItem.orderItemUnitPrice}</td>
                                                        <c:set var="productTotal" value="${orderItem.orderItemUnitPrice * orderItem.orderItemQuantity}" />
                                                        <td>${productTotal}</td>
                                                    </tr>
                                                    <c:set var="billAmount" value="${billAmount + productTotal}" />
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                                <hr>
                                <div class="has-text-right px-5">
                                    <h4> <strong>Bill Amount: ${billAmount}</strong> </h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
