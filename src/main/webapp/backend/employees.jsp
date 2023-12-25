<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Employee Page</title>
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
                <h1>Employee page</h1>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Role</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Email</th>
                            <th>Action</th>
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
                                    <form action="controller?action=employees&query=delete&employeeid=${employee.employeeID}" method="POST">
                                        <button type="submit">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <br><br>
                
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
                            <td><button type="submit">Submit</button></td>
                        </tr>
                    </table>
                </form>

                <br><br>
                
                <h1>Update employee</h1>
                <form action='controller' method="POST">
                    <input type="hidden" name="action" value="employees">
                    <input type="hidden" name="query" value="update">
                    <table>
                        <tr>
                            <td>Select employee</td>
                            <td>
                                <select id="employeeDropdown" name="employeeid">
                                    <c:forEach var="employee" items="${employees}">
                                        <option value="${employee.employeeID}">${employee.employeeFname} ${employee.employeeLname} | ${employee.employeeEmail}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
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
                            <td><button type="submit">Submit</button></td>
                        </tr>
                    </table>
                </form>
                
                
                
            </div>
        </div>
    </body>
</html>
