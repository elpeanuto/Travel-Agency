﻿<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />

<!DOCTYPE html>
<fmt:bundle basename="viewBundle">
<head>
    <title><fmt:message key="profileEdit"/></title>
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

<div class="main">
    <h2><fmt:message key="profile"/></h2>
    <div class="card">
        <div class="card-body">

            <form method="post" action="${pageContext.request.contextPath}/profileChange">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <fmt:message key="nickname" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="name" placeholder="<fmt:message key="enterNickname"/>" value="${requestScope.user.name}" pattern="^[A-Za-zА-Яа-я0-9]*$" minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="email" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="email" placeholder="<fmt:message key="enterEmail"/>" value="${requestScope.user.email}" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="phoneNumber" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="phoneNumber" placeholder="<fmt:message key="enterPhoneNumber"/>" value="${requestScope.user.phoneNumber}" pattern="\d*"  required minlength="10" maxlength="10">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="name" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="realName" placeholder="<fmt:message key="enterName"/>" value="${requestScope.user.realName}" pattern="^[A-ZА-я]+[a-zа-я]*$" minlength="3" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="surname" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="realSurName" placeholder="<fmt:message key="enterSurname"/>" value="${requestScope.user.realSurName}" pattern="^[A-ZА-Я]+[a-zа-я]*$" minlength="3" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="dateOfBirth" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="date" name="dateOfBirth" placeholder="<fmt:message key="enterDateOfBirth"/>" value="${requestScope.user.dateOfBirth}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="gender" />
                        </td>
                        <td>:</td>
                        <td>
                            <select name="gender">
                                <option value="Male">
                                    <fmt:message key="male" />
                                </option>
                                <option value="Female">
                                    <fmt:message key="female" />
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="nationality" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="citizenship" placeholder="<fmt:message key="enterNationality"/>" value="${requestScope.user.citizenship}" pattern="^[A-ZА-Я]+[a-zа-я]*$" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="passportSerial" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="passportSerial" placeholder="<fmt:message key="enterPassportSerial"/>" value="${requestScope.user.passportSerial}" pattern="\d*" required minlength="10" maxlength="10">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="passportNumber" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="passportNumber" placeholder="<fmt:message key="enterPassportNumber"/>" value="${requestScope.user.passportNumber}" pattern="\d*" required minlength="5" maxlength="5">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="passportValidDate" />
                        </td>
                        <td>:</td>
                        <td>
                            <input type="date" name="passportValidDate" placeholder="<fmt:message key="enterPassportValidDate"/>" value="${requestScope.user.passportValidDate}">
                        </td>
                    </tr>
                    </tbody>

                </table>
                <button class="button">
                    <fmt:message key="save"/></button>
            </form>
        </div>
    </div>

</body>
</fmt:bundle>
</html>