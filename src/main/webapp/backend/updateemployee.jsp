<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Update Employee</title>
    </head>
    <body>
        <%
            // HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            // HTTP 1.0
            response.setHeader("pragma", "no-cache");
            // For proxy servers
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
                            <div class="cardName">Update Employee</div>
                        </div>
                    </div>
                </div>

                <!-- ================ Product List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <form action='controller' method="POST">
                            <input type="hidden" name="action" value="employees">
                            <input type="hidden" name="query" value="update">
                            <input type="hidden" name="employeeid" value="${employeeinfo.employeeID}">
                            <table>
                                <tr>
                                    <td>Employee ID</td>
                                    <td>${employeeinfo.employeeID}</td>
                                </tr>
                                <tr>
                                    <td>Employee role</td>
                                    <td><input type="text" name="employeerole" value="${employeeinfo.roleName}" required></td>
                                </tr>
                                <tr>
                                    <td>First name:</td>
                                    <td><input type="text" name="employeefname" value="${employeeinfo.employeeFname}" required></td>
                                </tr>
                                <tr>
                                    <td>Last name:</td>
                                    <td><input type="text" name="employeelname" value="${employeeinfo.employeeLname}" required></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td><input type="email" name="employeeemail" value="${employeeinfo.employeeEmail}" required></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type="password" name="employeepassword" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><button type="submit">Submit</button></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
