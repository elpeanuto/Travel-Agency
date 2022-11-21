<!DOCTYPE html>

<head>
    <title>
        Tour delete
    </title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
</head>

<style>
    <%@include file="/css/view-style.css"%>
</style>


<body>

<div class="main">
    <h2>
        Tour
    </h2>
    <div class="card">
        <div class="card-body">

            <table>
                <tbody>
                <tr>
                    <td>
                        Name
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.name}</td>
                </tr>
                <tr>
                    <td>
                        Category
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.category}</td>
                </tr>
                <tr>
                    <td>
                        Type
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.type}</td>
                </tr>
                <tr>
                    <td>
                        Price
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.price}</td>
                </tr>
                <tr>
                    <td>
                        Hotel name
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.hotelName}</td>
                </tr>
                <tr>
                    <td>
                        Hotel type
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.hotelType}</td>
                </tr>
                <tr>
                    <td>
                        Arrival date
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.arrivalDate}</td>
                </tr>
                <tr>
                    <td>
                        Departure date
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.departureDate}</td>
                </tr>
                <tr>
                    <td>
                        Arrival place
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.arrivalPlace}</td>
                </tr>
                <tr>
                    <td>
                        Departure place
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.departurePlace}</td>
                </tr>
                <tr>
                    <td>
                        Country
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.country}</td>
                </tr>
                <tr>
                    <td>
                        City
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.city}</td>
                </tr>
                <tr>
                    <td>
                        Food in price
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.foodInPrice}</td>
                </tr>
                <tr>
                    <td>
                        Flight in price
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.flightInPrice}</td>
                </tr>
                <tr>
                    <td>
                        Amount of days
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.amountOfDays}</td>
                </tr>
                <tr>
                    <td>
                        Number of tourists
                    </td>
                    <td>:</td>
                    <td>${requestScope.product.numberOfTourists}</td>
                </tr>
                </tbody>

            </table>

            <a href="${pageContext.request.contextPath}/editProduct?id=${requestScope.product.id}">
                Edit
            </a>

            <form action="${pageContext.request.contextPath}/deleteProduct" method="POST">
                <input type="hidden" name="id" value="${requestScope.product.id}" />
                <input type="text" name="name" placeholder="Enter tour name" required="required" />
                <button>Delete</button>
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