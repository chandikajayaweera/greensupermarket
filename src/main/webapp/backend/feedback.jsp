<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- ====== Chart.js ======= -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <link rel="stylesheet" href="../resources/css/backend.css"/>
        <title>Customer Feedback Page</title>
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
                            <div class="cardName">Customer Feedback</div>
                        </div>
                    </div>
                </div>

                <!-- ================ Employee Details List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <canvas id="feedbackChart" width="1500" height="500"></canvas>
                        <table>
                            <thead>
                                <tr>
                                    <th>Feedback ID</th>
                                    <th>Customer ID</th>
                                    <th>Date</th>
                                    <th>Rate</th>
                                    <th>Message</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="eachfeedback" items="${feedback}">
                                    <tr>
                                        <td>${eachfeedback.feedbackID}</td>
                                        <td>${eachfeedback.customerID}</td>
                                        <td>${eachfeedback.feedbackDate}</td>
                                        <td>${eachfeedback.feedbackRating}</td>
                                        <td>${eachfeedback.feedbackMessage}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>

        <!-- =========== Scripts =========  -->
        <script src="../resources/js/main.js"></script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

        <script>
                                    var feedbackRatings = [
            <c:forEach var="eachfeedback" items="${feedback}" varStatus="loop">
                ${eachfeedback.feedbackRating}<c:if test="${!loop.last}">, </c:if>
            </c:forEach>
                                    ].map(Number);

                                    var labels = Array.from({length: 11}, (_, i) => i);

                                    var ratingCount = Array.from({length: 11}, () => 0);

                                    feedbackRatings.forEach(rating => {
                                        ratingCount[rating]++;
                                    });

                                    var ctx = document.getElementById('feedbackChart').getContext('2d');

                                    var customColors = [
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 159, 64, 0.2)',
                                        'rgba(255, 205, 86, 0.2)',
                                        'rgba(75, 192, 192, 0.2)',
                                        'rgba(54, 162, 235, 0.2)',
                                        'rgba(153, 102, 255, 0.2)',
                                        'rgba(201, 203, 207, 0.2)',
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(255, 159, 64, 0.2)',
                                        'rgba(255, 205, 86, 0.2)',
                                        'rgba(75, 192, 192, 0.2)'
                                    ];

                                    var myChart = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: labels,
                                            datasets: [{
                                                    label: 'Feedback Ratings',
                                                    data: ratingCount,
                                                    backgroundColor: customColors,
                                                    borderColor: 'rgba(75, 192, 192, 1)',
                                                    borderWidth: 1
                                                }]
                                        },
                                        options: {
                                            scales: {
                                                x: {
                                                    type: 'linear',
                                                    position: 'bottom',
                                                    title: {
                                                        display: true,
                                                        text: 'Rating'
                                                    },
                                                    ticks: {
                                                        stepSize: 1
                                                    }
                                                },
                                                y: {
                                                    beginAtZero: true,
                                                    title: {
                                                        display: true,
                                                        text: 'Frequency'
                                                    }
                                                }
                                            }
                                        }
                                    });
        </script>




    </body>
</html>
