<jsp:include page="adminHeader.jsp" />
<jsp:include page="../user/footer.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Messages</title>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

  <style>
    h3 {
      color: yellow;
      text-align: center;
    }
  </style>

</head>

<body>
<div style="color: black; text-align: center; font-size: 20px;">Your messages</div>

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

<script>
  if("${sessionScope.alertFlag}" === "true"){
    swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
  }
</script>
</body>

</html>