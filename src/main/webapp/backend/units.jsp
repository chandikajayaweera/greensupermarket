<%@taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unit Page</title>
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
            response.sendRedirect("login.jsp");
            }
        %>
        <h1>Unit Page</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Unit Name</th>
                    <th>Unit Abbreviation</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="unit" items="${units}">
                    <tr>
                        <td>${unit.unitName}</td>
                        <td>${unit.unitAbbreviation}</td>
                        <td><form action="controller?action=units&query=delete&unitname=${unit.unitName}" method="POST">
                                <button type="submit">Delete</button>
                            </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br><br>

        <h2>Add new unit</h2>
        <form action='controller' method="POST">
            <input type="hidden" name="action" value="units">
            <input type="hidden" name="query" value="add">
            Enter unit name: <input type="text" name="unitname">
            Enter unit abbreviation: <input type="text" name="unitabbreviation">
            <button type="submit">Submit</button>
        </form>
        

    </body>
</html>
