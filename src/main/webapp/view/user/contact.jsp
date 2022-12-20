<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="contactUs" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <style>
            <%@include file="/css/contact.css"%>
        </style>

    </head>

    <body>
    <form method="post" action="${pageContext.request.contextPath}/message">

        <input name="name" type="text" class="feedback-input" placeholder="<fmt:message key="name" />" value="${requestScope.user.name}" required pattern="^[A-Za-zА-Яа-я0-9]*$" minlength="3" maxlength="20"/>
        <input readonly name="email" type="text" class="feedback-input" placeholder="<fmt:message key="email" />" value="${requestScope.user.email}" required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50"/>
        <select name="category" class="feedback-input">
            <option value="websiteProblem">
                <fmt:message key="problemWithTheWebsite" />
            </option>
            <option value="technicalProblem">
                <fmt:message key="technicalProblem" />
            </option>
            <option value="orderProblem">
                <fmt:message key="problemWithOrder" />
            </option>
        </select>
        <textarea wrap="hard" name="message" class="feedback-input" placeholder="<fmt:message key="message" />" required minlength="20" maxlength="480"></textarea>
        <input type="submit" value="<fmt:message key="submit" />"/>
    </form>

    <script>
        if ("${sessionScope.alertFlag}" === "true") {
            swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
        }
    </script>
    </body>
</fmt:bundle>

</html>