<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<fmt:bundle basename="viewBundle">
    <head>

        <title>
            <fmt:message key="profile" />
        </title>

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">

        <style>
            @import url(https://fonts.googleapis.com/css?family=Dancing+Script);

            * {
                margin: 0;
            }

            body {
                background-color: #e8f5ff;
                font-family: Arial;
                overflow: hidden;
            }

            .main {
                margin-top: 2%;
                margin-left: 25%;
                font-size: 28px;
                padding: 0 10px;
                width: 50%;
            }

            .main h2 {
                color: #333;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                font-size: 24px;
                margin-bottom: 10px;
            }

            .main .card {
                background-color: #fff;
                border-radius: 18px;
                box-shadow: 1px 1px 8px 0 grey;
                height: auto;
                margin-bottom: 20px;
                padding: 20px 0 20px 50px;
            }

            .main .card table {
                border: none;
                font-size: 16px;
                height: 270px;
                width: 80%;
            }

        </style>
    </head>

    <body>

    <!-- Main -->
    <div class="main">
        <h2><fmt:message key="profile"/></h2>
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
                        <td>${requestScope.user.realSurName}</td>
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
    </body>
</fmt:bundle>

</html>