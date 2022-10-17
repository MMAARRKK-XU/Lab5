<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <c:out value="${name}"/>!</h1>
        <a href="shoppingList?action=logout">Logout</a>
        
        <h1>List</h1>
        
        <form action="shoppingList" method="post">
            <input type="hidden" name="action" value="add">
            <label>Item: <input type="text" name="item" id="item"></label>
            <button type="submit">Submit</button>
        </form>
        
        <br>
        <br>
        
        <form action="shoppingList" method="post">
            <input type="hidden" name="action" value="delete">
            <c:forEach var="item" items="${items}">
                <label><input type="radio" name="item" value="${item}">${item}</label><br>
            </c:forEach>    
            
            <br>
            <button type="submit">Delete</button>
        </form>
    </body>
</html>
