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
                            <div class="cardName">Customers</div>
                        </div>
                    </div>
                </div>

                <!-- ================ Customer Details List ================= -->
                <div class="details">
                    <div class="recentOrders"> <!-- Ignore the div class name -->
                        <table>
                            <thead>
                                <tr>
                                    <td>Customer ID</td>
                                    <td>Customer Fname</td>
                                    <td>Customer Lname</td>
                                    <td>Customer email</td>
                                    <td>Customer Phone number</td>
                                    <td>Delete</td>
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
                                        <td>
                                            <form action="controller?action=customers&query=delete&customerid=${customer.customerID}" method="POST">
                                                <button type="submit">Delete</button>
                                            </form>
                                        </td>
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
