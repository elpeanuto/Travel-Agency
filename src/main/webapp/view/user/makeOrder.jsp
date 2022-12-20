<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="order" />
        </title>

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <style>
            <%@include file="/css/view-style.css"%>
        </style>

    </head>

    <body>

    <!-- Main -->
    <div class="main">

        <h2>
            <fmt:message key="yourTour" />
        </h2>
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
                        <td>${requestScope.product.name}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="category" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.category}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="price" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.price}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="hotelName" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.hotelName}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="arrivalDate" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.arrivalDate}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="departureDate" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.departureDate}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="arrivalPlace" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.arrivalPlace}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="departurePlace" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.departurePlace}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="country" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.country}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="city" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.city}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="foodInPrice" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.foodInPrice}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="flightInPrice" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.flightInPrice}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="amountOfDays" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.amountOfDays}</td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="numberOfTourists" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.numberOfTourists}</td>
                    </tr>
                    </tbody>

                </table>
            </div>
        </div>
        <h2>
            <fmt:message key="yourInformation" />
        </h2>
        <div class="card">
            <div class="card-body">
                <i class="fa fa-pen fa-xs edit"></i>
                <form method="post" action="${pageContext.request.contextPath}/makeOrder">
                    <input type="hidden" name="id" value="${requestScope.product.id}">

                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <fmt:message key="nickname" />
                            </td>
                            <td>:</td>
                            <td>
                                <input readonly type="text" name="name" placeholder="<fmt:message key="enterNickname" />" value="${requestScope.user.name}" pattern="^[A-Za-zА-Яа-я0-9]*$" minlength="5" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="email" />
                            </td>
                            <td>:</td>
                            <td>
                                <input readonly type="text" name="email" placeholder="<fmt:message key="enterEmail" />" value="${requestScope.user.email}" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="phoneNumber" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" pattern="^\d{10}$" name="phoneNumber" placeholder="<fmt:message key="enterPhoneNumber" />" value="${requestScope.user.phoneNumber}" required minlength="10" maxlength="10">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="name" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="realName" placeholder="<fmt:message key="enterName" />" value="${requestScope.user.realName}" pattern="^[A-ZА-я]+[a-zа-я]*$" minlength="3" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="surname" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="realSurname" placeholder="<fmt:message key="enterSurname" />" value="${requestScope.user.realSurname}" pattern="^[A-ZА-Я]+[a-zа-я]*$" minlength="3" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="dateOfBirth" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="date" name="dateOfBirth" placeholder="<fmt:message key="enterDateOfBirth" />" value="${requestScope.user.dateOfBirth}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="gender" />
                            </td>
                            <td>:</td>
                            <td>
                                <select name="gender">
                                    <option <c:if test="${requestScope.user.gender == 'Male'}">selected</c:if> value="Male">
                                        <fmt:message key="male" />
                                    </option>
                                    <option <c:if test="${requestScope.user.gender == 'Female'}">selected</c:if> value="Female">
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
                                <input type="text" name="citizenship" placeholder="<fmt:message key="enterNationality" />p" value="${requestScope.user.citizenship}" pattern="^[A-ZА-Я]+[a-zа-я]*$" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportSerial" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" pattern="^\d{10}$" name="passportSerial" placeholder="<fmt:message key="enterPassportSerial" />" value="${requestScope.user.passportSerial}" required >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportNumber" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="text" name="passportNumber" placeholder="<fmt:message key="enterPassportNumber" />" value="${requestScope.user.passportNumber}" required pattern="^\d{5}$">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message key="passportValidDate" />
                            </td>
                            <td>:</td>
                            <td>
                                <input type="date" name="passportValidDate" placeholder="<fmt:message key="enterPassportValidDate" />" value="${requestScope.user.passportValidDate}">
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

    </div>

    <script>
        if ("${sessionScope.alertFlag}" === "true") {
            swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
        }
    </script>
    </body>
</fmt:bundle>

</html>