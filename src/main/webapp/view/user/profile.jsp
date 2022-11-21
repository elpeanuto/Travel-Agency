<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<fmt:bundle basename="viewBundle">
    <html>

    <head>

        <title>
            <fmt:message key="profile" />
        </title>

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <style>
            <%@include file="/css/view-style.css"%>
        </style>
    </head>

    <body>

    <div class="main">
        <h2>
            <fmt:message key="profile" />
        </h2>
        <div class="card">
            <div class="card-body">

                <table>
                    <tbody>
                    <tr>
                        <td>
                            <fmt:message key="nickname" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.name}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="email" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.email}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="phoneNumber" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.phoneNumber}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="name" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.realName}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="surname" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.realSurname}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="dateOfBirth" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.dateOfBirth}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="gender" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.gender}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="nationality" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.citizenship}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="passportSerial" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.passportSerial}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="passportNumber" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.passportNumber}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="passportValidDate" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.user.passportValidDate}</td>
                    </tr>
                    </tbody>

                </table>
                <a href="${pageContext.request.contextPath}/profileChange">
                    <fmt:message key="change" />
                </a>

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