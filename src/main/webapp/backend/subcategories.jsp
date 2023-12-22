<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>        
        <title>SubCategory Page</title>
    </head>
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
    <body>
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
                <h1>SubCategory Page</h1>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Category type</th>
                            <th>SubCategory name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="subcategory" items="${subcategories}">
                            <tr>
                                <td>${subcategory.categoryName}</td>
                                <td>${subcategory.subCategoryName}</td>
                                <td><form action="controller?action=subcategories&query=delete&subcategoryname=${subcategory.subCategoryName}" method="POST">
                                        <button type="submit">Delete</button>
                                    </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


                <table>
                    <form action='controller' method="POST">
                        <input type="hidden" name="action" value="subcategories">
                        <input type="hidden" name="query" value="add">
                        <tr>
                            <td colspan="2"><h2>Add new SubCategory</h2></td>
                        </tr>
                        <tr>
                            <td>Enter category type:</td>
                            <td>                                
                                <select id="categoryDropdown" name="categoryname">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.categoryName}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Enter Subcategory name:</td>
                            <td><input type="text" name="subcategoryname"></td>
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
