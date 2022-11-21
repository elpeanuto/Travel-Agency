<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />

<!DOCTYPE html>
<fmt:bundle basename="viewBundle">
    <html>

    <head>
        <title>
            <fmt:message key="tour" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>

    <style>
        <%@include file="/css/view-style.css"%>
    </style>

    <body>

    <div class="main">
        <h2>
            <fmt:message key="yourTour" />
        </h2>
        <div class="card">
            <div class="card-body">

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
                            <fmt:message key="type" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.type}</td>
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
                            <fmt:message key="hotelType" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.hotelType}</td>
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
                    <tr>
                        <td>
                            <fmt:message key="numberOfTourists" />
                        </td>
                        <td>:</td>
                        <td>${requestScope.product.description}</td>
                    </tr>
                    </tbody>

                </table>

                <a href="${pageContext.request.contextPath}/makeOrder?id=${requestScope.product.id}">
                    <fmt:message key="order" />
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