<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="view" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <style>
            <%@include file="/css/contact.css"%>
        </style>
    </head>

    <body>
    <div class="container">
        <div class="text-box">
            <output name="category" type="text">
                <fmt:message key="problem" />:
                <fmt:message key="${requestScope.message.category}" />
            </output>
        </div>
        <c:choose>
            <c:when test="${requestScope.message.adminId == 0}">
                <div class="text-box">
                    <output name="adminId" type="text">
                        <fmt:message key="adminId" />:
                        <fmt:message key="notProceeded" />
                    </output>
                </div>
            </c:when>
            <c:when test="${requestScope.message.adminId != 0}">
                <div class="text-box">
                    <output name="adminId" type="text">
                        <fmt:message key="adminId" />: ${requestScope.message.adminId}
                    </output>
                </div>

            </c:when>
        </c:choose>
        <div class="text-box">
            <pre>${requestScope.message.message}</pre>
        </div>
        <c:choose>
            <c:when test="${requestScope.message.answer == null}">
                <div class="text-box">
                    <output name="response">
                        <fmt:message key="notProceeded" />
                    </output>
                </div>
            </c:when>
            <c:when test="${requestScope.message.adminId != null}">
                <div class="text-box">
                    <pre>${requestScope.message.answer}</pre>
                </div>
            </c:when>
        </c:choose>
    </div>
    <script>
        if ("${sessionScope.alertFlag}" === "true") {
            swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
        }
    </script>
    </body>
</fmt:bundle>

</html>