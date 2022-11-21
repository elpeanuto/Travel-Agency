<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<fmt:bundle basename="viewBundle">

  <head>
    <title>
      <fmt:message key="messages" />
    </title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
      <%@include file="/css/home-style.css"%>
    </style>


  </head>

  <body>
  <%@ include file="header.jspf" %>
  <div style="color: black; text-align: center; font-size: 20px;">
    <fmt:message key="yourMessages" /><i class='fab fa-elementor'></i>
  </div>

  <table>
    <thead>
    <tr>
      <th scope="col">
        <fmt:message key="category" />
      </th>
      <th scope="col">
        <fmt:message key="receivedDate" />
      </th>
      <th scope="col">
        <fmt:message key="proceededDate" />
      </th>
      <th scope="col">
        <fmt:message key="answer" />
      </th>
      <th scope="col">
        <fmt:message key="view" />
      </th>

    </tr>
    </thead>
    <tbody>

    <c:forEach var="message" items="${requestScope.messageList}">
      <tr>
        <td>
          <fmt:message key="${message.category}" />
        </td>
        <td>${message.receivedDate}</td>
        <c:choose>
          <c:when test="${message.processingDate != null}">
            <td>${message.processingDate}</td>
          </c:when>
          <c:when test="${message.processingDate == null}">
            <td>
              <fmt:message key="notProceeded" />
            </td>
          </c:when>
        </c:choose>
        <c:choose>
          <c:when test="${message.processingDate != null}">
            <td>
              <fmt:message key="proceeded" />
            </td>
          </c:when>
          <c:when test="${message.processingDate == null}">
            <td>
              <fmt:message key="notProceeded" />
            </td>
          </c:when>
        </c:choose>
        <td>
          <a href="${pageContext.request.contextPath}/viewMessage?id=${message.id}">
            <fmt:message key="view" />
          </a>
        </td>
      </tr>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/message">
      <fmt:message key="contactUs" />
    </a>
    </tbody>
  </table>
  <br>
  <br>
  <br>

  <c:if test="${requestScope.positionList != null}">
    <div class="pagination" style="text-align: center;">
      <c:forEach var="position" items="${requestScope.positionList}">
        <a href="myMessages?page=${position}">${position}</a>
      </c:forEach>
    </div>
  </c:if>
  <jsp:include page="footer.jsp" />

  <script>
    if ("${sessionScope.alertFlag}" === "true") {
      swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
  </script>
  </body>
</fmt:bundle>

</html>