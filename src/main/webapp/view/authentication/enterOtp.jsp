<%--suppress JSUnresolvedLibraryURL --%>
<%--suppress JSUnresolvedLibraryURL --%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<html>

<fmt:bundle basename="viewBundle">

    <head>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <title>
            <fmt:message key="enterOTP" />
        </title>

        <style>
            .form-gap {
                padding-top: 70px;
            }
        </style>
    </head>

    <body>
    <input type="hidden" id="status" value="${requestScope.status}">
    <div class="form-gap"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">
                            <h3>
                                <i class="fa fa-lock fa-4x"></i>
                            </h3>
                            <h2 class="text-center">
                                <fmt:message key="enterOTP" />
                            </h2>

                            <div class="panel-body">

                                <form id="register-form" action="validateOtp" role="form" autocomplete="off" class="form" method="post">

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                            <input id="opt" name="otp" placeholder="<fmt:message key="enterOTP" />"
                                                   class="form-control" type="number" required="required"
                                                   pattern="^\d{6}$">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="<fmt:message key="getNewPassword" />" type="submit">
                                    </div>

                                    <input type="hidden" class="hide" name="token" id="token" value="">
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
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