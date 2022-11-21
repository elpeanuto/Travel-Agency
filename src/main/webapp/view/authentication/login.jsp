<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />

<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="signIn" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <style>
            <%@include file="/css/login.css"%>
        </style>

    </head>

    <body>
    <div class="wrapper">
        <h1>
            <fmt:message key="helloAgain" />!
        </h1>
        <p>Travel Management Systems</p>
        <form method="post" action="login">
            <input type="email" name="email" placeholder="<fmt:message key="enterEmail" />" required="required" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50"/>
            <input type="password" name="password" placeholder="<fmt:message key="password" />" required="required" minlength="5" maxlength="20" />
            <p class="recover">
                <a href="forgotPassword">
                    <fmt:message key="forgetpassword" />?
                </a>
            </p>

            <button>
                <fmt:message key="signIn" />
            </button>
        </form>
        <div class="not-member">
            <fmt:message key="notAMember" />? <a href="registration">
            <fmt:message key="register" />
        </a>
        </div>

    </div>

    <script>
        if ("${sessionScope.alertFlag}" === "true") {
            swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
        }
    </script>
    </body>

</fmt:bundle>

</html>