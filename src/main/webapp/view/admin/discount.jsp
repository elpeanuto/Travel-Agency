<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Discount</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view.css" />

  <!-- FontAwesome 5 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
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
              <input type="text" name="step" placeholder="Enter step" value="${discount.step}" required>
            </td>
          </tr>
          <tr>
            <td>Max</td>
            <td>:</td>
            <td>
              <input type="text" name="max" placeholder="Enter max" value="${discount.max}" required>
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