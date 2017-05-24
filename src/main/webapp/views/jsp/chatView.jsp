<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
</head>
<body>
<table>
    <c:forEach items="${requestScope.chat}" var="chat">
        <tr>
            <td><c:out value="${chat.id}"/></td>
            <td><c:out value="${chat.creatorId}"/></td>
            <td><c:out value="${chat.name}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
