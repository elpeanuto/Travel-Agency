<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Edit</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view.css" />

    <!-- FontAwesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
</head>

<body>

<!-- Main -->
<div class="main">

    <h2>New Product</h2>
    <div class="card">
        <div class="card-body">

            <form method="post" action="${pageContext.request.contextPath}/updateProduct">
                <input type="hidden" name="id" value="${product.id}">

                <table>
                    <tbody>
                    <tr>
                        <td>Name</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="name" placeholder="Enter name" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="price" placeholder="Enter price" required pattern="\d*" maxlength="6">
                        </td>
                    </tr>
                    <tr>
                        <td>Hotel Name</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="hotelName" placeholder="Enter hotel name" required pattern="^[A-ZА-Я][\D]*" minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="description" placeholder="Enter description" required minlength="10" maxlength="200">
                        </td>
                    </tr>
                    <tr>
                        <td>Number of Tourists</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="numberOfTourists" placeholder="Enter num of tourists" pattern="\d*" required maxlength="6">
                        </td>
                    </tr>
                    <tr>
                        <td>Arrival Date</td>
                        <td>:</td>
                        <td>
                            <input type="date" name="arrivalDate" placeholder="Enter arrival date" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Arrival place</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="arrivalPlace" placeholder="Enter arrival place" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Departure date</td>
                        <td>:</td>
                        <td>
                            <input type="date" name="departureDate" placeholder="Enter departure date" required>
                        </td>
                    </tr>
                    <tr>
                        <td>Departure place</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="departurePlace" placeholder="Enter departure place" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="country" placeholder="Enter country" pattern="^[A-ZА-Я][\D]* required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="city" placeholder="Enter city" pattern="^[A-ZА-Я][\D]*" required minlength="5" maxlength="20">
                        </td>
                    </tr>
                    <tr>
                        <td>Amount Of days</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="amountOfDays" placeholder="Enter amountOfDays" pattern="\d*" maxlength="6" required>
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
</body>

</html>