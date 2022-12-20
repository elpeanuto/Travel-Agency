<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Promote user</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <style><%@include file="/css/home-style.css"%></style>


</head>

<body>
<%@ include file="adminHeader.jspf" %>
<div class="header-name">Promote user</div>

<table>
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">PhoneNumber</th>
        <th>Status</th>
        <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${requestScope.userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.status}</td>
            <form action="promoteUser?id=${user.id}" method="post">
                <td>

                    <c:choose>
                        <c:when test="${sessionScope.user.status == 'Admin' || sessionScope.user.status == 'Leader'}">
                            <select class="input-style" name="status">
                                <option <c:if test="${user.status == 'Admin'}">
                                    selected
                                </c:if>
                                        value="Admin">admin
                                </option>
                                <option <c:if test="${user.status == 'Manager'}">
                                    selected
                                </c:if>
                                        value="Manager">manager
                                </option>
                                <option <c:if test="${user.status == 'Client'}">
                                    selected
                                </c:if>
                                        value="Client">client
                                </option>
                                <option <c:if test="${user.status == 'Banned'}">
                                    selected
                                </c:if>
                                        value="Banned">banned
                                </option>
                            </select>
                            <button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>
                        </c:when>
                        <c:when test="${sessionScope.user.status == 'Manager'}">

                            <select class="input-style" name="status">
                                <option <c:if test="${user.status == 'Manager'}">
                                    selected
                                </c:if>
                                        value="Manager">manager
                                </option>
                                <option <c:if test="${user.status == 'Client'}">
                                    selected
                                </c:if>
                                        value="Client">client
                                </option>
                                <option <c:if test="${user.status == 'Banned'}">
                                    selected
                                </c:if>
                                        value="Banned">Banned
                                </option>
                            </select>
                            <button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>
                        </c:when>
                    </c:choose>

                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<br>
<c:if test="${requestScope.positionList != null}">
    <div class="pagination">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="promoteUser?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>

<jsp:include page="../user/footer.jsp"/>
<script>
    if("${sessionScope.alertFlag}" === "true"){
        swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
</script>
</body>

</html>