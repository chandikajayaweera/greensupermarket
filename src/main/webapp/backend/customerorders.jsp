<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>    
        <title>Customer Orders</title>
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
                            <div class="cardName">Customer Orders</div>
                        </div>
                    </div>
                </div>


                <!-- ================ Customer Order List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <table>
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Payment ID</th>
                                    <th>Update Status</th>
                                    <th>View Invoice</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="customerOrder" items="${customerorders}">
                                    <tr>
                                        <td>${customerOrder.customerOrderID}</td>
                                        <td>${customerOrder.customerOrderDate}</td>
                                        <td><span class="status">${customerOrder.customerOrderStatus}</span></td>
                                        <td>${customerOrder.paymentID}</td>
                                        <td>
                                <center>
                                    <form action='controller' method="POST">
                                        <input type="hidden" name="action" value="customerorders">
                                        <input type="hidden" name="query" value="orderstatus">
                                        <input type="hidden" name="orderid" value="${customerOrder.customerOrderID}">
                                        <select id="orderStatusDropdown" name="orderstatus">
                                            <option value="Processing" ${'Processing' eq customerOrder.customerOrderStatus ? 'selected' : ''}>Processing</option>
                                            <option value="Shipped" ${'Shipped' eq customerOrder.customerOrderStatus ? 'selected' : ''}>Shipped</option>
                                            <option value="Delivered" ${'Delivered' eq customerOrder.customerOrderStatus ? 'selected' : ''}>Delivered</option>
                                            <option value="Cancelled" ${'Cancelled' eq customerOrder.customerOrderStatus ? 'selected' : ''}>Cancelled</option>
                                            <option value="Refunded" ${'Refunded' eq customerOrder.customerOrderStatus ? 'selected' : ''}>Refunded</option>
                                        </select>
                                        <button type="submit" class="btn3">&nbsp; ✓ &nbsp;</button>
                                    </form>
                                </center>
                                </td>
                                <td><button onclick="location.href = 'controller?action=invoice&orderid=${customerOrder.customerOrderID}'">View</button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

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
