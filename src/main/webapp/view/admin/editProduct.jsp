<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <title>Product Edit</title>
    <style>
        <%@include file="/css/view-style.css"%>
    </style>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
</head>

<body>

<div class="main">

    <h2>Update Product</h2>
    <div class="card">
        <div class="card-body">

            <form method="post" action="${pageContext.request.contextPath}/updateProduct">
                <input type="hidden" name="id" value="${requestScope.product.id}">

                <table>
                    <tbody>
                    <tr>
                        <td>Name</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="name" placeholder="Enter name" pattern="^[A-ZА-Я][\D]*" value="${requestScope.product.name}" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td>:</td>
                        <td>
                            <input type="number" name="price" min="1" placeholder="Enter price" required value="${requestScope.product.price}" maxlength="6">
                        </td>
                    </tr>
                    <tr>
                        <td>Hotel Name</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="hotelName" placeholder="Enter hotel name" value="${requestScope.product.hotelName}" required pattern="^[A-ZА-Я][\D]*" minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="description" placeholder="Enter description" value="${requestScope.product.description}" required minlength="10" maxlength="200">
                        </td>
                    </tr>
                    <tr>
                        <td>Number of Tourists</td>
                        <td>:</td>
                        <td>
                            <input type="number" name="numberOfTourists" min="1" placeholder="Enter num of tourists" value="${requestScope.product.numberOfTourists}" required maxlength="6">
                        </td>
                    </tr>
                    <tr>
                        <td>Arrival Date</td>
                        <td>:</td>
                        <td>
                            <input type="date" name="arrivalDate" placeholder="Enter arrival date" value="${requestScope.product.arrivalDate}" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Arrival place</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="arrivalPlace" placeholder="Enter arrival place" value="${requestScope.product.arrivalPlace}" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Departure date</td>
                        <td>:</td>
                        <td>
                            <input type="date" name="departureDate" value="${requestScope.product.departureDate}" placeholder="Enter departure date" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Departure place</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="departurePlace" placeholder="Enter departure place" value="${requestScope.product.departurePlace}" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="country" placeholder="Enter country" value="${requestScope.product.country}" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="city" placeholder="Enter city" value="${requestScope.product.city}" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Amount Of days</td>
                        <td>:</td>
                        <td>
                            <input type="number" name="amountOfDays" min="1" value="${requestScope.product.amountOfDays}" placeholder="Enter amountOfDays" maxlength="6" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Hotel type</td>
                        <td>:</td>
                        <td>
                            <select name="hotelType">
                                <option value="Resort">Resort</option>
                                <option value="Motel">Motel</option>
                                <option value="Hostel">Hostel</option>
                                <option value="Timeshare">Timeshare</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Type</td>
                        <td>:</td>
                        <td>
                            <select class="input-style" name="type">
                                <option value="Ordinary">Ordinary</option>
                                <option value="Hot">Hot</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td>:</td>
                        <td>
                            <select name="category">
                                <option value="Rest">Rest</option>
                                <option value="Excursion">Excursion</option>
                                <option value="Shopping">Shopping</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Is food in price</td>
                        <td>:</td>
                        <td>
                            <select name="foodInPrice">
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Is flight in price</td>
                        <td>:</td>
                        <td>
                            <select name="flightInPrice">
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Active</td>
                        <td>:</td>
                        <td>
                            <select name="active">
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                            </select>
                        </td>
                    </tr>
                    </tbody>

                </table>
                <button class="button">Save</button>
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

</html>