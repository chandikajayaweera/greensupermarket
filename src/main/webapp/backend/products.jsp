<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Product Page</title>
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
                            <div class="cardName">Products</div>
                        </div>
                        <div class="b1">
                            <!-- Added onclick event to call the toggleElements function -->
                            <a href="#" class="btn" style="color: white;" onclick="toggleElements('add', 'list')">Add Product </a>
                        </div>
                    </div>
                </div>

                <!-- ================ Product List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="list">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Product Image</th>
                                        <th>Product Name</th>
                                        <th>Product SKU</th>
                                        <th>Brand Name</th>
                                        <th>SubCategory</th>
                                        <th>Product description</th>
                                        <th>Product Unit Price</th>
                                        <th>Product Stock</th>
                                        <th>Update</th>
                                        <th>Delete</th>
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
                                            <td>${product.subCategoryName}</td>
                                            <td>${product.productDescription}</td>
                                            <td>${product.productUnitPrice}</td>
                                            <td>${product.productStock}</td>
                                            <td>
                                                <form action="controller">
                                                    <input type="hidden" name="action" value="updateproduct">
                                                    <input type="hidden" name="productid" value="${product.productID}">
                                                    <button type="submit">Update</button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="controller?action=products&query=delete&productid=${product.productID}" method="POST">
                                                    <button type="submit">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="add">
                            <h2>Add new product</h2>
                            <form action='controller' method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="products">
                                <input type="hidden" name="query" value="add">
                                <table>
                                    <tr>
                                        <td>Enter product name:</td>
                                        <td><input type="text" name="productname" required></td>
                                    </tr>
                                    <tr>
                                        <td>Enter product SKU:</td>
                                        <td><input type="text" name="productsku" required></td>
                                    </tr>
                                    <tr>
                                        <td>Enter Brand name:</td>
                                        <td><input type="text" name="brandname" required></td>
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
                                        <td><input type="number" name="productunitprice" min="1" step="0.01" required></td>
                                    </tr>
                                    <tr>
                                        <td>Enter product stock quantity</td>
                                        <td><input type="number" name="productstock" min="0" required></td>
                                    </tr>
                                    <tr>
                                        <td>Enter product image</td>
                                        <td><input type="file" name="productimage" accept="image/*" required></td>
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
