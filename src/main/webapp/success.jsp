<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bulma.min.css"/>
        <title>Payment Completed</title>
    </head>
    <body>
        <%
            //HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //HTTP 1.0
            response.setHeader("pragma", "no-cache");
            //For proxy servers
            response.setHeader("Expires", "0"); 
	        
            if(session.getAttribute("customer") == null){
                response.sendRedirect("customer/login?action=logout");
                return;
            }
            
            if(session.getAttribute("customerorderid") == null){
                response.sendRedirect("index.jsp");
                return;
            }
        %>
        <jsp:include page="template/frontend/navbar.jsp"/>
        <h1>Payment Completed</h1>
        Order ID: ${customerorderid}
        <br><br>
        <button onclick="location.href = 'customer/controller?action=invoice&orderid=${customerorderid}'" class="button is-primary is-size-6">View invoice</button>
    </body>
</html>
