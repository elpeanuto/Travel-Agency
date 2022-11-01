<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="order" />
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
        /* Main */
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
        .edit {
            position: absolute;
            color: #e7e7e8;
            right: 14%;
        }
        </style>
    </head>

    <body>

    <!-- Main -->
    <div class="main">

        <h2><fmt:message key="yourTour"/></h2>
        <div class="card">
            <div class="card-body">
                <i class="fa fa-pen fa-xs edit"></i>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <fmt:message key="name" />
                        </td>
                        <td>:</td>
                        <td>${product.name}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="category" />
                        </td>
                        <td>:</td>
                        <td>${product.category}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="price" />
                        </td>
                        <td>:</td>
                        <td>${product.price}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="hotelName" />
                        </td>
                        <td>:</td>
                        <td>${product.hotelName}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="arrivalDate" />
                        </td>
                        <td>:</td>
                        <td>${product.arrivalDate}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="departureDate" />
                        </td>
                        <td>:</td>
                        <td>${product.departureDate}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="arrivalPlace" />
                        </td>
                        <td>:</td>
                        <td>${product.arrivalPlace}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="departurePlace" />
                        </td>
                        <td>:</td>
                        <td>${product.departurePlace}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="country" />
                        </td>
                        <td>:</td>
                        <td>${product.country}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="city" />
                        </td>
                        <td>:</td>
                        <td>${product.city}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="foodInPrice" />
                        </td>
                        <td>:</td>
                        <td>${product.foodInPrice}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="flightInPrice" />
                        </td>
                        <td>:</td>
                        <td>${product.flightInPrice}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="amountOfDays" />
                        </td>
                        <td>:</td>
                        <td>${product.amountOfDays}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="numberOfTourists" />
                        </td>
                        <td>:</td>
                        <td>${product.numberOfTourists}</td>
                    </tr>
                    </tbody>

                </table>
            </div>
        </div>
        <h2><fmt:message key="yourInformation"/></h2>
        <div class="card">
            <div class="card-body">
                <i class="fa fa-pen fa-xs edit"></i>
                <form method="post" action="${pageContext.request.contextPath}/makeOrder">
                    <input type="hidden" name="id" value="${product.id}">

                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <fmt:message key="nickname" />
                            </td>
                            <td>:</td>
                            <td>
                                <input readonly type="text" name="name" placeholder="<fmt:message key="enterNickname"/>" value="${user.name}" pattern="^[A-Za-zА-Яа-я0-9]*$" minlength="5" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="email" />
                            </td>
                            <td>:</td>
                            <td>
                                <input readonly type="text" name="email" placeholder="<fmt:message key="enterEmail"/>" value="${user.email}" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="phoneNumber" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="phoneNumber" placeholder="<fmt:message key="enterPhoneNumber"/>" value="${user.phoneNumber}" pattern="\d*"  required minlength="10" maxlength="10">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="name" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="realName" placeholder="<fmt:message key="enterName"/>" value="${user.realName}" pattern="^[A-ZА-я]+[a-zа-я]*$" minlength="3" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="surname" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="realSurName" placeholder="<fmt:message key="enterSurname"/>" value="${user.realSurName}" pattern="^[A-ZА-Я]+[a-zа-я]*$" minlength="3" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="dateOfBirth" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="date" name="dateOfBirth" placeholder="<fmt:message key="enterDateOfBirth"/>" value="${user.dateOfBirth}">
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
                                <input type="text" name="citizenship" placeholder="<fmt:message key="enterNationality"/>p" value="${user.citizenship}" pattern="^[A-ZА-Я]+[a-zа-я]*$" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportSerial" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="passportSerial" placeholder="<fmt:message key="enterPassportSerial"/>" value="${user.passportSerial}" pattern="\d*" required minlength="10" maxlength="10">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportNumber" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="passportNumber" placeholder="<fmt:message key="enterPassportNumber"/>" value="${user.passportNumber}" pattern="\d*" required minlength="5" maxlength="5">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportValidDate" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="date" name="passportValidDate" placeholder="<fmt:message key="enterPassportValidDate"/>" value="${user.passportValidDate}">
                            </td>
                        </tr>
                        </tbody>

                    </table>
                    <button class="button">
                        <fmt:message key="save" /></button>
                </form>
            </div>

        </div>

    </div>
    </body>
</fmt:bundle>

</html>