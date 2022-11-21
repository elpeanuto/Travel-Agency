<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="signUp" />
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
        <form method="post" action="registration">
            <input type="text" name="name" id="name" placeholder="<fmt:message key="enterNickname" />" pattern="^[A-Za-zА-Яа-я0-9]*$" required="required" minlength="5" maxlength="20" />
            <input type="email" name="email" id="email" placeholder="<fmt:message key="enterEmail" />" required="required" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50"/>
            <input type="password" name="password" id="password" placeholder="<fmt:message key="password" />" required="required" minlength="5" maxlength="20" />
            <input type="password" name="re-password" id="re-password" placeholder="<fmt:message key="repeatYourPassword" />" required="required" minlength="5" maxlength="20" />
            <input class="phone_mask" type="text" name="contact" id="contact" placeholder="<fmt:message key="enterPhoneNumber" />" pattern="^\d{10}$" required="required"/>
            <button>
                <fmt:message key="signUp" />
            </button>
        </form>
        <div class="not-member">
            <fmt:message key="haveAccount" />? <a href="login">
            <fmt:message key="signIn" />
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