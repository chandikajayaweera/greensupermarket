<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/bulma.min.css"/>
    <title>Cart</title>
</head>
<body>
    <jsp:include page="template/frontend/navbar.jsp"/>
    
    <% if (session.getAttribute("customer") == null) {
        response.sendRedirect("customer/login?action=logout");
    } else {
        Object cart = session.getAttribute("cart");
        if (cart != null && cart instanceof ArrayList && !((ArrayList<?>) cart).isEmpty()) { 
    %>

    <h1 class="is-size-3 has-text-weight-bold">Your cart</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Product name</th>
                <th>Product Image</th>
                <th>Unit price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="orderitem" items="${cart}">
                <tr>
                    <td>${orderitem.productName}</td>
                    <td><img src="${orderitem.productImageURL}" width="100"></td>
                    <td>${orderitem.orderItemUnitPrice}</td>
                    <td>${orderitem.orderItemQuantity}</td>
                    <c:set var="productTotal" value="${orderitem.orderItemUnitPrice * orderitem.orderItemQuantity}" />
                    <td>${productTotal}</td>
                    <td>
                        <form action="cart" method="GET">
                            <input type="hidden" name="action" value="removefromcart">
                            <input type="hidden" name="productid" value="${orderitem.productID}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>Total: </td>
                <td>${total}</td>
            </tr>
        </tbody>
    </table>
    <br>
    <button onclick="location.href='cart?action=checkout'" class="button is-primary">Checkout</button>
    <% } else { %>
    <h1 class="is-size-3 has-text-weight-bold">Your cart is empty</h1>
    <% } %>
    <% } %>
</body>
</html>
