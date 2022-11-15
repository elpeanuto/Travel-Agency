<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Discount</title>
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

  <h2>Discount</h2>
  <div class="card">
    <div class="card-body">

      <form method="post" action="${pageContext.request.contextPath}/discount">

        <table>
          <tbody>
          <tr>
            <td>Step</td>
            <td>:</td>
            <td>
              <input type="text" name="step" placeholder="Enter step" value="${requestScope.discount.step}" required>
            </td>
          </tr>
          <tr>
            <td>Max</td>
            <td>:</td>
            <td>
              <input type="text" name="max" placeholder="Enter max" value="${requestScope.discount.max}" required>
            </td>
          </tr>
          </tbody>

        </table>
        <button class="button">Save</button>
      </form>
    </div>

  </div>
</div>
</body>

</html>