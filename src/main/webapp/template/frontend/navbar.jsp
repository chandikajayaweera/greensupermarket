<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<nav class="navbar is-primary has shadow">
    
    <!-- Logo -->
    <div class="navbar-brand">
        <a href="<c:url value='/index.jsp'/>" class="navbar-item has-text-center">
            <h1 class="is-size-3 has-text-weight-bold px-2 ">GREENSUPER</h1>
        </a>
        <a class="navbar-burger" id="burger">
            <span></span>
            <span></span>
            <span></span>
        </a>
    </div>
            
    <div class="navbar-menu" id="nav-links">
        <div class="navbar-end">
            
            <% 
                // Check if the session attribute 'customer' is null
                if (session.getAttribute("customer") == null) {
            %>
                <!-- Show login and signup buttons -->
                <a href="<c:url value='/customer/login.jsp'/>" class="navbar-item">Login</a>
                <a href="<c:url value='/customer/signup.jsp'/>" class="navbar-item">Signup</a>
            <%
                } else {
            %>
                <!-- Show My Account and Shopping Cart links -->
                <a href="<c:url value='/cart?action=viewcart'/>" class="navbar-item">Shopping Cart</a>
                <a href="<c:url value='/customer/dashboard.jsp'/>" class="navbar-item">My Account</a>
            <%
                }
            %>
            
        </div>
    </div>
</nav>
<script>
    const burgerIcon = document.querySelector('#burger');
    const navbarMenu = document.querySelector('#nav-links');

    burgerIcon.addEventListener('click', () => {
        navbarMenu.classList.toggle('is-active');
    });
</script>
