<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../resources/css/bulma.min.css"/>
    <title>Customer Sign up</title>
    <script>
        function validatePhoneNumber() {
            var phoneNumber = document.forms["signupForm"]["customerpnumber"].value;
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
    // HTTP 1.1
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    // HTTP 1.0
    response.setHeader("pragma", "no-cache");
    // For proxy servers
    response.setHeader("Expires", "0");
%>
<jsp:include page="../template/frontend/navbar.jsp"/>

<form name="signupForm" action="login" method="POST" onsubmit="return validatePhoneNumber()">
    <input type="hidden" name="action" value="signup">
    <div class="hero is-fullheight has-background-primary-light">
        <div class="hero-body is-justify-content-center is-align-items-center">
            <div class="columns is-flex is-flex-direction-column box">

                <div class="column has-text-centered">
                    <h1 class="title has-text-primary">Customer Signup</h1>
                </div>

                <div class="column">
                    <label for="email">First name</label>
                    <input class="input is-primary" type="text" name="customerfname" placeholder="First name" required>
                </div>

                <div class="column">
                    <label for="email">Last name</label>
                    <input class="input is-primary" type="text" name="customerlname" placeholder="Last name" required>
                </div>

                <div class="column">
                    <label for="Name">Email</label>
                    <input class="input is-primary" type="email" name="customeremail" placeholder="Email" required>
                </div>

                <div class="column">
                    <label for="email">Phone number</label>
                    <input class="input is-primary" type="tel" name="customerpnumber" placeholder="Phone number" required>
                </div>

                <div class="column">
                    <label for="Name">Password</label>
                    <input class="input is-primary" type="password" name="customerpassword" placeholder="Password" required>
                </div>
                <div class="column">
                    <button class="button is-primary is-fullwidth" type="submit">Sign up</button>
                </div>
                <div class="has-text-centered">
                    <p class="is-size-7"> Already have an account? <a href="login.jsp" class="has-text-primary">Login</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
