<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">

  <head>
    <title>Cancel order</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

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

  <div class="main">
    <h2><fmt:message key="yourOrder"/></h2>
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
        <form method="post" action="${pageContext.request.contextPath}/cancelOrder">
          <input type="hidden" name="id" value="${requestScope.orderId}">
          <button class="button">
            <fmt:message key="cancel" />
          </button>
        </form>

      </div>

    </div>
  </div>

  <script>
    if("${sessionScope.alertFlag}" === "true"){
      swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
  </script>
  </body>
</fmt:bundle>

</html>