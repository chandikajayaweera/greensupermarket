<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Login Portal</title>
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

        <h1>Customer Login Portal</h1>
        <form action="logincontroller" method="POST">
            <input type="hidden" name="action" value="login">
            <table>
                <tr>
                    <td>Customer Email:</td>
                    <td><input type="email" name="customeremail"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="customerpassword"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Login"></td>
                </tr>
            </table>
        </form>
        
        <h3><a href="signup.jsp">Signup</a></h3>
    </body>
</html>
