<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bulma.min.css"/>
        <title>Products Page</title>
    </head>
    <body>
        <%
        if(session.getAttribute("session") == null){
            response.sendRedirect("controller?action=categories");
            }
        %>
        <jsp:include page="template/frontend/navbar.jsp"/>
        <section class="section">
            <div class="container">
                
                <h3 class="has-text-centered is-size-3 has-text-weight-bold has-text-primary title">${sessionScope.categoryname} Products <% if (session.getAttribute("subcategoryname") != null) { %> > ${sessionScope.subcategoryname}<% } %></h3>
                
                <!-- Sub categories -->
                <div class="columns is-centered is-multiline">
                    <% if (session.getAttribute("subcategoryname") != null) { %>
                    <div class="column is-2 has-text-centered">
                        <button onclick="location.href='controller?action=categories&categoryname=${sessionScope.categoryname}'" class="button is-danger is-rounded is-outlined">Reset</button>
                    </div> <% } %>
                    
                    <c:forEach var="subcategory" items="${sessionScope.subcategories}">
                        <div class="column is-2 has-text-centered">
                            <button class="button is-success is-hovered" onclick="location.href='controller?action=categories&subcategoryname=${subcategory.subCategoryName}'">${subcategory.subCategoryName}</button>
                        </div>
                    </c:forEach>
                </div>
                
                <!-- Product cards -->
                <div class="mt-5 columns is-centered is-3 is-multiline">
                    <c:forEach var="product" items="${sessionScope.products}">
                        <div class="column is-4-tablet is-3-desktop">
                            <div class="card">
                                <div class="card-image has-text-centered px-6">
                                    <img src="${product.productImageURL}" alt="Product Image">
                                </div>
                                <div class="card-content">
                                    <p>Rs. ${product.productUnitPrice}</p>
                                    <p class="title is-size-5">${product.productName}</p>
                                </div>
                                <footer class="card-footer">
                                    <p class="card-footer-item">
                                        <button class="button is-medium is-fullwidth is-primary is-focused" onclick="location.href='controller?action=productdetails&productid=${product.productID}'">View Item</button>
                                    </p>
                                </footer>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

    </body>
</html>
