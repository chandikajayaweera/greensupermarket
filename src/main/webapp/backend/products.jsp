<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Product Page</title>
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
                <h1>Product page</h1>
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product Image</th>
                            <th>Product Name</th>
                            <th>Product SKU</th>
                            <th>Brand Name</th>
                            <th>Unit Name</th>
                            <th>SubCategory</th>
                            <th>Product description</th>
                            <th>Product Unit Price</th>
                            <th>Product Stock</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.productID}</td>
                                <td><img src="${product.productImageURL}" width="150" height="150"></td>
                                <td>${product.productName}</td>
                                <td>${product.productSKU}</td>
                                <td>${product.brandName}</td>
                                <td>${product.unitName}</td>
                                <td>${product.subCategoryName}</td>
                                <td>${product.productDescription}</td>
                                <td>${product.productUnitPrice}</td>
                                <td>${product.productStock}</td>
                                <td><form action="controller?action=products&query=delete&productid=${product.productID}" method="POST">
                                        <button type="submit">Delete</button>
                                    </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <h2>Add new Category</h2>
                <form action='controller' method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="products">
                    <input type="hidden" name="query" value="add">
                    <table>
                        <tr>
                            <td>Enter product name:</td>
                            <td><input type="text" name="productname"></td>
                        </tr>
                        <tr>
                            <td>Enter product SKU:</td>
                            <td><input type="text" name="productsku"></td>
                        </tr>
                        <tr>
                            <td>Enter Brand name:</td>
                            <td><input type="text" name="brandname"></td>
                        </tr>
                        <tr>
                            <td>Select product unit:</td>
                            <td>
                                <select id="unitDropdown" name="unitname">
                                    <c:forEach var="unit" items="${units}">
                                        <option value="${unit.unitName}">${unit.unitName} | ${unit.unitAbbreviation}</option>
                                    </c:forEach>
                                </select>
                            </td>   
                        </tr>
                        <tr>
                            <td>Select product subcategory</td>
                            <td>
                                <select id="subCategoryDropdown" name="subcategoryname">
                                    <c:forEach var="subcategory" items="${subcategories}">
                                        <option value="${subcategory.subCategoryName}">${subcategory.subCategoryName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Enter product description</td>
                            <td><textarea name="productdescription" style="resize:none" rows="4" cols="50"></textarea></td>
                        </tr>
                        <tr>
                            <td>Enter product unit price</td>
                            <td><input type="number" name="productunitprice" min="1" step="0.01"></td>
                        </tr>
                        <tr>
                            <td>Enter product stock quantity</td>
                            <td><input type="number" name="productstock" min="0"></td>
                        </tr>
                        <tr>
                            <td>Enter product image</td>
                            <td><input type="file" name="productimage" accept="image/*"></td>
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
