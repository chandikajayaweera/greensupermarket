<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bulma.min.css"/>
        <title>Payment Failed</title>
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
            }
        %>
        <jsp:include page="template/frontend/navbar.jsp"/>

        <div class="hero is-fullheight has-background-primary-light">
            <div class="hero-body is-justify-content-center is-align-items-center">
                <div class="columns is-flex is-flex-direction-column box">
                    <div class="column has-text-centered">
                        <h1 class="is-size-4">Payment failed</h1>
                    </div>
                    <div class="column has-text-centered">
                        <h1><button onclick="location.href = 'cart?action=viewcart'" class="button is-primary is-size-6">Try again</button></h1>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
