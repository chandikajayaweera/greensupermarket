<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="columns has-text-centered has-background-primary-light">
                <aside class="column is-2 aside menu hero is-fullheight has-background-primary-dark">
                    <ul class="menu-list">
                        <li class="pb-5 mx-5"><a class="is-size-4 has-text-weight-bold has-text-white" href="controller?action=orders">Orders</a></li>
                        <li class="pb-5 mx-5"><a class="is-size-4 has-text-weight-bold has-text-white" href="controller?action=profile">Profile</a></li>
                        <li class="pb-5 mx-5"><a class="is-size-4 has-text-weight-bold has-text-white" href="controller?action=feedback">Feedback</a></li>
                        <li class="m-5"><button class="button is-size-5 has-text-weight-bold is-primary" onclick="location.href = 'login?action=logout'">Logout</button></li>
                    </ul>
                </aside>
                <div class="column is-10">
                    <div class="title p-5">
                        <h1>Write your feedback</h1>
                    </div>
                    <div class="content m-5">
                        
                        <form action='controller' method="POST">
                            <input type="hidden" name="action" value="feedback">
                            <table>
                                <tr>
                                    <td>How satisfied are you?<br> Rate from 0 to 10</td>
                                    <td><input type="number" name="rate" min="0" max="10" placeholder="0 - 10" required></td>
                                </tr>
                                <tr>
                                    <td>Write your feedback message</td>
                                    <td><textarea name="message" style="resize:none" rows="4" cols="50" placeholder="Enter your message here"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><button type="submit">Submit</button></td>
                                </tr>
                            </table>
                        </form>    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
