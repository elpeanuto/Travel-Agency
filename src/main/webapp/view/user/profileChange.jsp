<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />

<!DOCTYPE html>
<html>
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="profileEdit" />
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

                <form method="post" action="${pageContext.request.contextPath}/profileChange">
                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <fmt:message key="nickname" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="name" placeholder="<fmt:message key=" enterNickname" />" value="${requestScope.user.name}" pattern="^[A-Za-zА-Яа-я0-9]*$" minlength="5" maxlength="20" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="phoneNumber" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="phoneNumber" placeholder="<fmt:message key=" enterPhoneNumber" />" value="${requestScope.user.phoneNumber}" pattern="^\d{10}$">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="name" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="realName" placeholder="<fmt:message key=" enterName" />" value="${requestScope.user.realName}" pattern="^[A-ZА-я]+[a-zа-я]*$" minlength="3" maxlength="20" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="surname" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="realSurname" placeholder="<fmt:message key=" enterSurname" />" value="${requestScope.user.realSurname}" pattern="^[A-ZА-Я]+[a-zа-я]*$" minlength="3" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="dateOfBirth" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="date" name="dateOfBirth" placeholder="<fmt:message key=" enterDateOfBirth" />" value="${requestScope.user.dateOfBirth}" required>
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
                                <input type="text" name="citizenship" placeholder="<fmt:message key=" enterNationality" />" value="${requestScope.user.citizenship}" pattern="^[A-ZА-Я]+[a-zа-я]*$" maxlength="20" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportSerial" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="passportSerial" placeholder="<fmt:message key=" enterPassportSerial" />" value="${requestScope.user.passportSerial}" required pattern="^\d{10}$">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportNumber" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="passportNumber" placeholder="<fmt:message key=" enterPassportNumber" />" value="${requestScope.user.passportNumber}" required pattern="^\d{5}$">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportValidDate" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="date" name="passportValidDate" placeholder="<fmt:message key=" enterPassportValidDate" />" value="${requestScope.user.passportValidDate}" required>
                            </td>
                        </tr>
                        </tbody>

                    </table>
                    <button class="button">
                        <fmt:message key="save" />
                    </button>
                </form>
            </div>
        </div>
        <script>
            if ("${sessionScope.alertFlag}" === "true") {
                swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
            }
        </script>
    </div>
    </body>
</fmt:bundle>

</html>