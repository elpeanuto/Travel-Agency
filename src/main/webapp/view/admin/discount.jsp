<!DOCTYPE html>
<html lang="en">

<head>
    <title>Discount</title>
    <style>
        <%@include file="/css/view-style.css"%>
    </style>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
</head>

<body>

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
                            <input type="number" name="step" placeholder="Enter step" value="${requestScope.discount.step}" required maxlength="6">
                        </td>
                    </tr>
                    <tr>
                        <td>Max</td>
                        <td>:</td>
                        <td>
                            <input type="number" name="max" placeholder="Enter max" value="${requestScope.discount.max}" required maxlength="6">
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