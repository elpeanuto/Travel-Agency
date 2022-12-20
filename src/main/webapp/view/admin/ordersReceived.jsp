<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Order received</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <style>
        <%@include file="/css/home-style.css"%>
    </style>

</head>

<body>
<%@ include file="adminHeader.jspf" %>
<div style="color: black; text-align: center; font-size: 20px;">All orders</div>

<table>
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Product_Id</th>
        <th scope="col">User_Id</th>
        <th scope="col">Date</th>
        <th scope="col">Status</th>
        <th scope="col">Update</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="order" items="${requestScope.orderList}">
        <tr>
            <td>${order.id}</td>
            <td>${order.productId}</td>
            <td>${order.userId}</td>
            <td>${order.date}</td>
            <td>${order.status}</td>
            <form action="allOrders?id=${order.id}" method="post">
                <td>
                    <select class="input-style" name="status">
                        <option <c:if test="${requestScope.order.status == 'Paid'}">selected</c:if> value="Paid">Paid</option>
                        <option <c:if test="${requestScope.order.status == 'Canceled'}">selected</c:if> value="Canceled">Canceled</option>
                        <option <c:if test="${requestScope.order.status == 'Succeed'}">selected</c:if> value="Succeed">Succeed</option>
                    </select>
                    <button class="button">Save</button>

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
            <a href="allOrders?page=${position}">${position}</a>
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