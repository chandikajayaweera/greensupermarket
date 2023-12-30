<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>Customer Login</title>
    </head>
    <body>
        <%
            //HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //HTTP 1.0
            response.setHeader("pragma", "no-cache");
            //For proxy servers
            response.setHeader("Expires", "0"); 
        %>
        <jsp:include page="../template/frontend/navbar.jsp"/>

        <form action="login" method="POST">
            <input type="hidden" name="action" value="login">
            <div class="hero is-fullheight has-background-primary-light">
                <div class="hero-body is-justify-content-center is-align-items-center">
                    <div class="columns is-flex is-flex-direction-column box">
                        <div class="column has-text-centered">
                            <h1 class="title has-text-primary">Customer Login</h1>
                        </div>
                        <div class="column">
                            <label for="email">Email</label>
                            <input class="input is-primary" type="email" name="customeremail" placeholder="Email address" required>
                        </div>
                        <div class="column">
                            <label for="Name">Password</label>
                            <input class="input is-primary" type="password" name="customerpassword" placeholder="Password" required>
                        </div>
                        <div class="column">
                            <button class="button is-primary is-fullwidth" type="submit">Login</button>
                        </div>
                        <div class="has-text-centered">
                            <p class="is-size-7"> Don't have an account? <a href="signup.jsp" class="has-text-primary">Sign up</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
