<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!doctype html>
<html>
<fmt:bundle basename="viewBundle">

<head>
    <title>
        <fmt:message key="passwordRecovery" />
    </title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' rel='stylesheet'>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        <%@include file="/css/forgot-password.css"%>
    </style>

</head>

<body>
<input type="hidden" id="status" value="${requestScope.status}">

<div class="container padding-bottom-3x mb-2 mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10">
            <div class="forgot">
                <h2>
                    <fmt:message key="forgotYourPass" />?
                </h2>
                <p>
                    <fmt:message key="changeYourPasswordInSteps" />!
                </p>
                <ol class="list-unstyled">
                    <li><span class="text-primary text-medium">1. </span>
                        <fmt:message key="enterYourAddressBelow" />.
                    </li>
                    <li><span class="text-primary text-medium">2. </span>
                        <fmt:message key="systemSendOTP" />.
                    </li>
                    <li><span class="text-primary text-medium">3. </span>
                        <fmt:message key="enterOTPnextPage" />.
                    </li>
                </ol>
            </div>

            <form class="card mt-4" action="forgotPassword" method="POST">
                <div class="card-body">
                    <div class="form-group">
                        <label for="email-for-pass">
                            <fmt:message key="enterYourEmail" />
                        </label>
                        <input class="form-control" type="text" name="email" id="email-for-pass" required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50"><small>
                        <small class="form-text text-muted">
                            <fmt:message key="enterRegisteredEmail" />.
                            <fmt:message key="thenweEmail" />.
                        </small>
                    </small>
                    </div>
                </div>
                <div class="card-footer">
                    <button class="btn btn-success" type="submit">
                        <fmt:message key="getNewPassword" />
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</fmt:bundle>

<script>
    if ("${sessionScope.alertFlag}" === "true") {
        swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
</script>

</body>

</html>