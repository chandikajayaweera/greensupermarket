<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bulma.min.css"/>
        <title>Green Supermarket</title>
    </head>
    <body class="has-background-primary-light">
        <%
        if(session.getAttribute("session") == null){
            response.sendRedirect("controller?action=index");
            }
        %>

        <!-- Navbar -->
        <jsp:include page="template/frontend/navbar.jsp"/>

        <section class="section">
            <div class="container hero is-fullheight">
                <div>
                    <h1 class="has-text-centered is-size-3 has-text-weight-bold has-text-primary">Shop by category</h1>

                    <div class="mt-5 columns is-centered is-8 is-multiline">       
                        <c:forEach var="category" items="${sessionScope.categories}">
                            <a class="column is-4-tablet is-3-desktop" href="controller?action=categories&categoryname=${category.categoryName}">
                                <div class="card" style="border-radius: 10px; background: black; overflow: hidden;">
                                    <div class="card-image has-text-centered">
                                        <figure class="image is-2by1">
                                            <img src="${category.categoryImageURL}" style="opacity: 0.7;">
                                        </figure>
                                        <h1 class="is-size-4 has-text-white">${category.categoryName}</h1>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                        <a class="column is-12" href="controller?action=categories&categoryname=all">
                            <div class="card has-background-primary" style="border-radius: 10px;">
                                <div class="card-header-title is-centered"><h1 class="is-size-4 has-text-white">View all products</h1></div>
                            </div>
                        </a>
                    </div>
                </div>

            </div>
        </section>

    </body>
</html>