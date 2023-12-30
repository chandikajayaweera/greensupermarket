<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Update Product</title>
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
                            <div class="cardName">Update Product</div>
                        </div>
                    </div>
                </div>

                <!-- ================ Product List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <h2>Product Information</h2>
                        <form action='controller' method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="products">
                            <input type="hidden" name="query" value="update">
                            <input type="hidden" name="productid" value="${product.productID}">
                            <table>
                                <tr>
                                    <td>Product ID:</td>
                                    <td>${product.productID}</td>
                                </tr>
                                <tr>
                                    <td>Enter product name:</td>
                                    <td><input type="text" name="productname" value="${product.productName}" required></td>
                                </tr>
                                <tr>
                                    <td>Enter product SKU:</td>
                                    <td><input type="text" name="productsku" value="${product.productSKU}" required></td>
                                </tr>
                                <tr>
                                    <td>Enter Brand name:</td>
                                    <td><input type="text" name="brandname" value="${product.brandName}" required></td>
                                </tr>
                                <tr>
                                    <td>Select product subcategory</td>
                                    <td>
                                        <select id="subCategoryDropdown" name="subcategoryname">
                                            <option value="${product.subCategoryName}" selected="selected">${product.subCategoryName}</option>
                                            <c:forEach var="subcategory" items="${subcategories}">
                                                <c:choose>
                                                    <c:when test="${subcategory.subCategoryName eq product.subCategoryName}">
                                                        <!-- Skip this option -->
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${subcategory.subCategoryName}">${subcategory.subCategoryName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Enter product description</td>
                                    <td><textarea name="productdescription" style="resize:none" rows="4" cols="50">${product.productDescription}</textarea></td>
                                </tr>
                                <tr>
                                    <td>Enter product unit price</td>
                                    <td><input type="number" name="productunitprice" min="1" step="0.01" value="${product.productUnitPrice}" required></td>
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

                        <h2>Product stock</h2>
                        <form action='controller' method="POST">
                            <input type="hidden" name="action" value="products">
                            <input type="hidden" name="query" value="updatestock">
                            <input type="hidden" name="productid" value="${product.productID}">
                            <table>
                                <tr>
                                    <td>Enter quantity</td>
                                    <td><input type="number" name="productstock" min="0" value="${product.productStock}" required></td>
                                    <td><input type="submit" value="Update"></td>
                                </tr>
                            </table>
                        </form>

                        <h2>Product unit price</h2>
                        <form action='controller' method="POST">
                            <input type="hidden" name="action" value="products">
                            <input type="hidden" name="query" value="updateunitprice">
                            <input type="hidden" name="productid" value="${product.productID}">
                            <table>
                                <tr>
                                    <td>Enter new price</td>
                                    <td><input type="number" name="unitprice" min="1" step="0.01" value="${product.productUnitPrice}" required></td>
                                    <td><input type="submit" value="Update"></td>
                                </tr>
                            </table>
                        </form>
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
