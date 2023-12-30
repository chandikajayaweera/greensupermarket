<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
        <title>Customer Profile</title>
        <script>
            function validatePhoneNumber() {
                var phoneNumber = document.forms["updateForm"]["pnumber"].value;
                var phoneNumberPattern = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;

                if (!phoneNumberPattern.test(phoneNumber)) {
                    alert("Please enter a valid phone number.");
                    return false;
                }
                return true;
            }
        </script>
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
                    <div class="title p-3">
                        <h1>Account information</h1>
                    </div>
                    <div class="content m-5">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>Email</th>
                                    <th>Phone number</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${customer.customerFname}</td>
                                    <td>${customer.customerLname}</td>
                                    <td>${customer.customerEmail}</td>
                                    <td>${customer.customerPnumber}</td>
                                </tr>
                            </tbody>
                        </table>


                        <br><br>
                        <h1>Update information</h1>   
                        <form name="updateForm" action="controller?action=profile&query=update&customerid=${customer.customerID}" method="POST" onsubmit="return validatePhoneNumber()">
                            <table>
                                <tr>
                                    <td>First name</td>
                                    <td><input type="text" value="${customer.customerFname}" name="fname" required></td>
                                </tr>
                                <tr>
                                    <td>Last name</td>
                                    <td><input type="text" value="${customer.customerLname}" name="lname" required></td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td><input type="email" value="${customer.customerEmail}" name="email" required></td>
                                </tr>
                                <tr>
                                    <td>Phone number</td>
                                    <td><input type="tel" value="${customer.customerPnumber}" name="pnumber" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><input type="submit" value="Update"></td>
                                </tr>
                            </table>  
                        </form>

                        <br><br>
                        <h1>Update password</h1>
                        <form action="controller?action=profile&query=updatepassword&customerid=${customer.customerID}" method="POST">
                            <table>
                                <tr>
                                    <td>Enter new password</td>
                                    <td><input type="password" name="password" placeholder="Password" required></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><input type="submit" value="Update"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>       
    </body>
</html>
