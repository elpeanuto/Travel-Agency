<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="adminHeader.jsp"/>
<jsp:include page="../user/footer.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Promote user</title>
    <style>
        h3 {
            color: yellow;
            text-align: center;
        }
    </style>
</head>

<body>
<div style="color: black; text-align: center; font-size: 20px;">Promote user</div>

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
    <div class="pagination" style="text-align: center;">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="promoteUser?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>
</body>

</html>