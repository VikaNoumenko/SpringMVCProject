<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<table>
    <c:forEach items="${requestScope.message}" var="message">
        <tr>
            <td><c:out value="${message.id}"/></td>
            <td><c:out value="${message.text}"/></td>
            <td><c:out value="${message.chatId}"/></td>
            <td><c:out value="${message.authorId}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
