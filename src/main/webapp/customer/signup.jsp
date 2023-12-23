<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>Sign up page</title>
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
        <h1>Customer sign up page</h1>
        <form action="login" method="POST">
            <input type="hidden" name="action" value="signup">
            <table>
                <tr>
                    <td>Enter your first name:</td>
                    <td><input type="text" name="customerfname"></td>
                </tr>
                <tr>
                    <td>Enter your last name:</td>
                    <td><input type="text" name="customerlname"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="customeremail"></td>
                </tr>
                <tr>
                    <td>Phone number</td>
                    <td><input type="tel" name="customerpnumber"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="customerpassword"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Sign Up"></td>
                </tr>
            </table>
        </form>        
        <h3><a href="login.jsp">Login</a></h3>
    </body>
</html>
