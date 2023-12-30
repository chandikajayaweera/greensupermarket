<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>  
        <title>Invoice</title>
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

        <style>
            /* =============================================================
               GENERAL STYLES
             ============================================================ */
            body {
                font-family: 'Open Sans', sans-serif;
                font-size:16px;
                line-height:30px;
            }
            .pad-top-botm {
                padding-bottom:40px;
                padding-top:60px;
            }
            h4 {
                text-transform:uppercase;
            }
            /* =============================================================
               PAGE STYLES
             ============================================================ */

            .contact-info span {
                font-size:14px;
                padding:0px 50px 0px 50px;
            }

            .contact-info hr {
                margin-top: 0px;
                margin-bottom: 0px;
            }

            .client-info {
                font-size:15px;
            }

            .ttl-amts {
                text-align:right;
                padding-right:50px;
            }
        </style>

        <div id="container">
            <!-- =============== Navigation ================ -->
            <jsp:include page="../template/backend/navbar.jsp"/>

            <!-- ========================= Main ==================== -->
            <div class="main">
                <div class="topbar">
                    <div class="toggle">
                        <ion-icon name="menu-outline"></ion-icon>
                    </div>
                    <div>
                        <h2>${employee.roleName} | ${employee.employeeFname} ${employee.employeeLname} </h2>
                    </div>

                    <div>
                        <button onclick="location.href = 'login?action=logout'">Logout</button>
                    </div> 
                </div>

                <!-- ======================= Cards ================== -->
                <div class="cardBox">
                    <div class="card">
                        <div>
                            <div class="cardName">Invoice</div>
                        </div>
                    </div>
                </div>

                <!-- ================ Invoice ================= -->
                <div class="details">

                    <div class="recentOrders">
                        <!--<div class="row pad-top-botm">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <strong>GREENSUPERMARKET</strong>
                            </div>
                            <br>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <strong>Invoice no:</strong> #${customerorder.customerOrderID}<br>
                                <strong>Date:</strong> ${customerorder.customerOrderDate}
                            </div>
                        </div>
                        <center>
                            <div class="row text-center contact-info">
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <hr>
                                    <span>
                                        <strong>Email : </strong>  contact@greensupermarket.com 
                                    </span>
                                    <span>
                                        <strong>Call : </strong>  +1-623-777-9044 
                                    </span>
                                    <hr>
                                </div>
                            </div>
                        </center>-->
                        <div class="row pad-top-botm client-info">
                            <div class="col-lg-6 col-md-6 col-sm-6">
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
                                        <b>Call :</b> ${customerinfo.customerPnumber}
                                        <br>
                                        <b>E-mail :</b> ${customerinfo.customerEmail}
                                    </c:forEach>
                                </c:if>
                            </div>
                            <br>
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
                                <div class="ttl-amts">
                                    <h4> <strong>Bill Amount: ${billAmount}</strong> </h4>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>  
        </div>
        <!-- =========== Scripts =========  -->
        <script src="../resources/js/main.js"></script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>
