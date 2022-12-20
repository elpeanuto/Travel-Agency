<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <title>Messages</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <style>
        <%@include file="/css/home-style.css"%>
    </style>
</head>

<body>
<%@ include file="adminHeader.jspf" %>
<div class="header-name">Your messages</div>

<table>
    <thead>
    <tr>
        <th scope="col">Message id</th>
        <th scope="col">User id</th>
        <th scope="col">Category</th>
        <th scope="col">Received date</th>
        <th scope="col">Process</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="message" items="${requestScope.messageList}">
        <tr>
            <td>${message.id}</td>
            <td>${message.userId}</td>
            <td>${message.category}</td>
            <td>${message.receivedDate}</td>
            <td><a href="${pageContext.request.contextPath}/messageResponse?id=${message.id}">Process</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<br>

<c:if test="${requestScope.positionList != null}">
    <div class="pagination" style="text-align: center;">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="allMessages?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>
<jsp:include page="../user/footer.jsp" />

<script>
    if ("${sessionScope.alertFlag}" === "true") {
        swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
</script>
</body>

</html>