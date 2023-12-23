<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../resources/css/backend.css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>Feedback Page</title>
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
            response.sendRedirect("login?action=logout");
            }
        %>
        <jsp:include page="../template/frontend/navbar.jsp"/>
        <div id="container">
            <div id="sidebar">
                <ul>
                    <li><font size="30"><a href="dashboard.jsp"><b>Dash</b></a></font></li>
                    <li><a href="controller?action=feedback">Feedback</a></li>
                </ul>
            </div>
            <div id="content">
                <!-- Your main content goes here -->
                <h1>Write your feedback</h1>
                <form action='controller' method="POST">
                    <input type="hidden" name="action" value="feedback">
                    <table>
                        <tr>
                            <td>How satisfied are you?<br> Rate from 0 to 10</td>
                            <td><input type="number" name="rate" min="0" max="10"></td>
                        </tr>
                        <tr>
                            <td>Write your feedback message</td>
                            <td><textarea name="message" style="resize:none" rows="4" cols="50"></textarea></td>
                        </tr>
                        <tr>
                            <td><button type="submit">Submit</button></td>
                        </tr>
                    </table>
                </form>                
                
                
            </div>
        </div>
        
    </body>
</html>
