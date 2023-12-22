<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Category Page</title>
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
                    <li><a href="controller?action=units">Units</a></li>
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
                <h1>Category Page</h1>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Category Name</th>
                            <th>Category Image</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="category" items="${categories}">
                            <tr>
                                <td>${category.categoryName}</td>
                                <td><img src="${category.categoryImageURL}" width="150" height="150"></td>
                                <td><form action="controller?action=categories&query=delete&categoryname=${category.categoryName}" method="POST">
                                        <button type="submit">Delete</button>
                                    </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <h2>Add new Category</h2>
                <form action='controller' method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="categories">
                    <input type="hidden" name="query" value="add">
                    <table>
                        <tr>
                            <td>Enter category name:</td>
                            <td><input type="text" name="categoryname"></td>
                        </tr>
                        <tr>
                            <td>Select an category Image:</td>
                            <td><input type="file" name="categoryimage" accept="image/*"></td>
                        </tr>
                        <tr>
                            <td><button type="submit">Submit</button></td>
                        </tr>
                    </table>
                </form>        

                <h2>Update a category</h2>
                <form action='controller' method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="categories">
                    <input type="hidden" name="query" value="update">
                    <table>
                        <tr>
                            <td>Enter category name:</td>
                            <td>                    
                                <select id="categoryDropdown" name="categoryname">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.categoryName}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Select an category Image:</td>
                            <td><input type="file" name="categoryimage" accept="image/*"></td>
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
