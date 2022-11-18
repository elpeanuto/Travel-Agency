<jsp:include page="adminHeader.jsp" />
<jsp:include page="../user/footer.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Order received</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <style>
        h3 {
            color: yellow;
            text-align: center;
        }
    </style>

</head>

<body>
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
                        <option value="Paid">Paid</option>
                        <option value="Canceled">Canceled</option>
                        <option value="Succeed">Succeed</option>
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
    <div class="pagination" style="text-align: center;">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="allOrders?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>
<script>
    if("${sessionScope.alertFlag}" === "true"){
        swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
</script>
</body>

</html>