<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Categories</title>
        <style>
            .add {
                display: none;
            }
        </style>
    </head>
    <body>
        <%
            //HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //HTTP 1.0
            response.setHeader("pragma", "no-cache");
            //For proxy servers
            response.setHeader("Expires", "0"); 
        
            if(session.getAttribute("employee") == null){
                response.sendRedirect("login?action=logout");
            }
        %>

        <div id="container">
            <!-- =============== Navigation ================ -->
            <jsp:include page="../template/backend/navbar.jsp"/>

            <!-- ========================= Main ==================== -->
            <div class="main">
                <div class="topbar">
                    <div class="toggle">
                        <ion-icon name="menu-outline"></ion-icon>
                    </div>
                    <div>
                        <h2>${employee.roleName} | ${employee.employeeFname} ${employee.employeeLname} </h2>
                    </div>

                    <div>
                        <button onclick="location.href = 'login?action=logout'">Logout</button>
                    </div> 
                </div>

                <!-- ======================= Cards ================== -->
                <div class="cardBox">
                    <div class="card">
                        <div>
                            <div class="cardName">Categories</div>
                        </div>
                        <div class="b1">
                            <!-- Added onclick event to call the toggleElements function -->
                            <a href="#" class="btn" style="color: white;" onclick="toggleElements('add', 'list')">Add Category </a>
                        </div>
                    </div>
                </div>

                <!-- ================ Category List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="list">
                            <table>
                                <thead>
                                    <tr>
                                        <td>Category Name</td>
                                        <td>Category Image</td>
                                        <td>Update Image</td>
                                        <td>Delete Item</td> 
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="category" items="${categories}">
                                        <tr>
                                            <td>${category.categoryName}</td>
                                            <td><img src="${category.categoryImageURL}" width="200" height="150"></td>
                                            <td>
                                                <form action='controller' method="POST" enctype="multipart/form-data">
                                                    <input type="hidden" name="action" value="categories">
                                                    <input type="hidden" name="query" value="update">
                                                    <input type="hidden" name="categoryname" value="${category.categoryName}">
                                                    <input type="file" name="categoryimage" accept="image/*" required>
                                                    <button type="submit" class="btn3">&nbsp; ✓ &nbsp;</button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="controller?action=categories&query=delete&categoryname=${category.categoryName}" method="POST">
                                                    <button type="submit" class="btn2">Delete</button>
                                                </form>
                                            </td>    
                                        </tr>
                                    </c:forEach>
                                </tbody>      
                            </table>
                        </div>

                        <div class="add">
                            <h2>Add new Category</h2>
                            <form action='controller' method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="categories">
                                <input type="hidden" name="query" value="add">
                                <table>
                                    <tr>
                                        <td>Enter category name:</td>
                                        <td><input type="text" name="categoryname" required></td>
                                    </tr>
                                    <tr>
                                        <td>Select a category Image:</td>
                                        <td><input type="file" name="categoryimage" accept="image/*" required></td>
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
        </div>
        <!-- =========== Scripts =========  -->
        <script src="../resources/js/main.js"></script>

        <!-- Added JavaScript to toggle elements -->
        <script>
                                function toggleElements(showClassName, hideClassName) {
                                    var showElements = document.getElementsByClassName(showClassName);
                                    var hideElements = document.getElementsByClassName(hideClassName);

                                    for (var i = 0; i < showElements.length; i++) {
                                        showElements[i].style.display = 'block';
                                    }

                                    for (var j = 0; j < hideElements.length; j++) {
                                        hideElements[j].style.display = 'none';
                                    }
                                }
        </script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>
