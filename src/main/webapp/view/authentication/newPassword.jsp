<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="getNewPassword" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <style>
            <%@include file="/css/forgot-password.css"%>
        </style>

    </head>

    <body>
    <div class="wrapper">
        <h1>
            <fmt:message key="helloAgain" />!
        </h1>
        <p>Travel Management Systems</p>
        <form method="post" action="newPassword">
            <input type="password" name="password" id="password" placeholder="<fmt:message key="password" />"
                   required="required" minlength="5" maxlength="20"/>
            <input type="password" name="re-password" id="re-password" placeholder="<fmt:message key="repeatYourPassword" />" required="required" minlength="5"
                   maxlength="20"/>

            <button>
                <fmt:message key="reset" />
            </button>
        </form>
    </div>

    <script>
        if ("${sessionScope.alertFlag}" === "true") {
            swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
        }
    </script>
    </body>

</fmt:bundle>

</html>