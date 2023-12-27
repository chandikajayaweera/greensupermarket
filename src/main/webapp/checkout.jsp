<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bulma.min.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="template/frontend/navbar.jsp"/>

        <% 
            if (session.getAttribute("customer") == null) {
                response.sendRedirect("customer/login?action=logout");
            } else {
                Object cart = session.getAttribute("cart");
                if (cart == null || (cart instanceof ArrayList && ((ArrayList<?>) cart).isEmpty())) { 
                    response.sendRedirect("cart.jsp");
                }
            }
        %>

        <h1 class="title">Checkout page</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Product name</th>
                    <th>Product Image</th>
                    <th>Unit price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderitem" items="${orderitems}">
                    <tr>
                        <td>${orderitem.productName}</td>
                        <td><img src="${orderitem.productImageURL}" width="100"></td>
                        <td>${orderitem.orderItemUnitPrice}</td>
                        <td>${orderitem.orderItemQuantity}</td>
                        <c:set var="productTotal" value="${orderitem.orderItemUnitPrice * orderitem.orderItemQuantity}" />
                        <td>${productTotal}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h2>Total: ${total}</h2>

        <br>
        <form id="checkoutForm" action="checkout" method="POST">
            <input type="hidden" name="total" value="${total}">
            <input type="submit" value="Checkout" class="button is-primary">
        </form>


    </body>
</html>
