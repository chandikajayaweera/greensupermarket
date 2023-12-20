<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Unit Page</title>
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
            response.sendRedirect("login.jsp");
            }
        %>
        <div id="container">
            <div id="sidebar">
                <ul>
                    <li><font size="30"><a href="dashboard.jsp"><b>Dash</b></a></font></li>
                    <li><a href="controller?action=units">Units</a></li>
                    <li><a href="controller?action=categories">Categories</a></li>
                    <li><a href="controller?action=subcategories">Sub Categories</a></li>
                    <li><a href="controller?action=products">Products</a></li>
                    <li><a href="controller?action=customers">Customers</a></li>
                    <li><a href="controller?action=customerorders">Customer Orders</a></li>
                    <li><a href="controller?action=customerfeedback">Customer Feedback</li>
                    <li><a href="controller?action=employees">Employees</a></li>
                </ul>
            </div>
            <div id="content">
                <h1>Unit Page</h1>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Unit Name</th>
                            <th>Unit Abbreviation</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="unit" items="${units}">
                            <tr>
                                <td>${unit.unitName}</td>
                                <td>${unit.unitAbbreviation}</td>
                                <td><form action="controller?action=units&query=delete&unitname=${unit.unitName}" method="POST">
                                        <button type="submit">Delete</button>
                                    </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <br><br>

                <h2>Add new unit</h2>
                <form action='controller' method="POST">
                    <input type="hidden" name="action" value="units">
                    <input type="hidden" name="query" value="add">
                    <table>
                        <tr>
                            <td>Enter unit name:</td>
                            <td><input type="text" name="unitname"></td>
                        </tr>
                        <tr>
                            <td>Enter unit abbreviation:</td>
                            <td><input type="text" name="unitabbreviation"></td>
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
