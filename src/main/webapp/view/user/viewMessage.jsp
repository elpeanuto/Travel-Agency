<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<fmt:bundle basename="viewBundle">

<head>
  <title>Title</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

  <style>
    @import url(https://fonts.googleapis.com/css?family=Montserrat:400,700);
    body {
      background: #e8f5ff;
    }

    form {
      max-width: 420px;
      margin: 50px auto;
    }


    .feedback-input {
      color: black;
      font-family: Helvetica, Arial, sans-serif;
      font-weight: 500;
      font-size: 18px;
      border-radius: 5px;
      line-height: 22px;
      background-color: transparent;
      border: 2px solid #CC6666;
      transition: all 0.3s;
      padding: 13px;
      margin-bottom: 15px;
      width: 100%;
      box-sizing: border-box;
      outline: 0;
    }

    .feedback-input:focus {
      border: 2px solid #CC4949;
    }

    textarea {
      height: 150px;
      line-height: 150%;
      resize: vertical;
    }

    [type="submit"] {
      font-family: 'Montserrat', Arial, Helvetica, sans-serif;
      width: 100%;
      background: #CC6666;
      border-radius: 5px;
      border: 0;
      cursor: pointer;
      color: white;
      font-size: 24px;
      padding-top: 10px;
      padding-bottom: 10px;
      transition: all 0.3s;
      margin-top: -4px;
      font-weight: 700;
    }

    [type="submit"]:hover {
      background: #CC4949;
    }
  </style>
</head>

<body>
  <input name="category" type="text" class="feedback-input" readonly value="<fmt:message key="${requestScope.message.category}"/>" />
  <c:choose>
    <c:when test="${requestScope.message.adminId == 0}">
      <input readonly name="adminId" type="text" class="feedback-input" placeholder="Id" value="<fmt:message key="notProceeded"/>" />
    </c:when>
    <c:when test="${requestScope.message.adminId != 0}">
      <input readonly name="adminId" type="text" class="feedback-input" placeholder="Id" value="<fmt:message key="adminId"/>: ${requestScope.message.adminId}" />
    </c:when>
  </c:choose>
  <input readonly name="request" class="feedback-input" value="${requestScope.message.message}">
  <c:choose>
    <c:when test="${requestScope.message.answer == null}">
      <input readonly name="response" class="feedback-input" value="<fmt:message key="notProceeded"/>">
    </c:when>
    <c:when test="${requestScope.message.adminId != null}">
      <input readonly name="response" class="feedback-input" value="${requestScope.message.answer}">
    </c:when>
  </c:choose>

  <script>
    if("${sessionScope.alertFlag}" === "true"){
      swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
  </script>
</body>
</fmt:bundle>
</html>