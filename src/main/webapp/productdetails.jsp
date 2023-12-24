<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bulma.min.css"/>
        <title>Product details</title>
    </head>
    <body>
        <%
        if(session.getAttribute("product") == null){
            response.sendRedirect("controller?action=index");
            }
        %>
        <!-- Navbar -->
        <jsp:include page="template/frontend/navbar.jsp"/>


        <!-- product info -->
        <section class="section">
            <div class="container">
                <div class="columns is-vcentered is-multiline">
                    <div class="column is-6-tablet is-3-desktop">
                        <h1 class="is-size-3-mobile is-size-1-desktop title">${product.productName}</h1>
                        <h2 class="is-size-5-mobile is-size-4-desktop subtitle">Brand: ${product.brandName}</h2>
                        <p>${product.productDescription}</p>
                    </div>
                    <div class="column is-6-tablet is-5-desktop has-text-centered">
                        <img src="${product.productImageURL}" alt="docker coffee image" class="px-6">
                    </div>
                    <div class="column is-12-tablet is-4-desktop">
                        <div class="is-size-4 mb-4">Rs. ${product.productUnitPrice}</div>
                        <p><b>Product type:</b> ${product.subCategoryName}</p>
                        <p class="mb-4"><b>Product SKU:</b> ${product.productSKU}</p>
                        <p class="mb-4">(Stock remaining: ${product.productStock})</p>
                        <c:choose>
                            <c:when test="${product.productStock > 0}">
                                <input type="number" name="quantity" value="1" max="${product.productStock}" min="1" ><br><br>
                                <button class="button is-primary">Add to Cart</button>
                            </c:when>
                            <c:otherwise>
                                <button class="button is-primary" disabled>Out of Stock</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
