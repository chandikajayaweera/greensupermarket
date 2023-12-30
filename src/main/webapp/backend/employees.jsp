<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Employees</title>
        <style>
            .add {
                display: none;
            }
        </style>
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
                            <div class="cardName">Employees</div>
                        </div>
                        <div class="b1">
                            <!-- Added onclick event to call the toggleElements function -->
                            <a href="#" class="btn" style="color: white;" onclick="toggleElements('add', 'list')">Add Employee</a>
                        </div>
                    </div>
                </div>

                <!-- ================ Employee Details List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="list">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Role</th>
                                        <th>First name</th>
                                        <th>Last name</th>
                                        <th>Email</th>
                                        <th>Update</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead> 
                                <tbody>
                                    <c:forEach var="employee" items="${employees}">
                                        <tr>
                                            <td>${employee.roleName}</td>
                                            <td>${employee.employeeFname}</td>
                                            <td>${employee.employeeLname}</td>
                                            <td>${employee.employeeEmail}</td>
                                            <td>
                                                <form action="controller">
                                                    <input type="hidden" name="action" value="updateemployee">
                                                    <input type="hidden" name="employeeid" value="${employee.employeeID}">
                                                    <center><button type="submit">Update</button></center>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="controller?action=employees&query=delete&employeeid=${employee.employeeID}" method="POST">
                                                    <button type="submit">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>  
                            </table>
                        </div>

                        <div class="add">
                            <h1>Add employee</h1>
                            <form action='controller' method="POST">
                                <input type="hidden" name="action" value="employees">
                                <input type="hidden" name="query" value="add">
                                <table>
                                    <tr>
                                        <td>Employee role</td>
                                        <td><input type="text" name="employeerole"></td>
                                    </tr>
                                    <tr>
                                        <td>First name:</td>
                                        <td><input type="text" name="employeefname"></td>
                                    </tr>
                                    <tr>
                                        <td>Last name:</td>
                                        <td><input type="text" name="employeelname"></td>
                                    </tr>
                                    <tr>
                                        <td>Email:</td>
                                        <td><input type="email" name="employeeemail"></td>
                                    </tr>
                                    <tr>
                                        <td>Password:</td>
                                        <td><input type="password" name="employeepassword"></td>
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
        </div>  

        <!-- =========== Scripts =========  -->
        <script src="../resources/js/main.js"></script>

        <!-- Added JavaScript to toggle elements -->
        <script>
                                function toggleElements(showClassName, hideClassName) {
                                    var showElements = document.getElementsByClassName(showClassName);
                                    var hideElements = document.getElementsByClassName(hideClassName);

                                    for (var i = 0; i < showElements.length; i++) {
                                        showElements[i].style.display = 'block';
                                    }

                                    for (var j = 0; j < hideElements.length; j++) {
                                        hideElements[j].style.display = 'none';
                                    }
                                }
        </script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>
