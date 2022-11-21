<!DOCTYPE html>
<html>

<head>
    <title>Title</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        <%@include file="/css/contact.css"%>
    </style>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">

</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/messageResponse">
    <input name="id" hidden value=${requestScope.message.id}>
    <input name="category" type="text" class="feedback-input" readonly value="${requestScope.message.category}" required />
    <input name="adminId" type="number" class="feedback-input" readonly placeholder="Id" value="${requestScope.admin.id}" required />
    <input name="request" class="feedback-input" value="${requestScope.message.message}" readonly>
    <textarea wrap="hard" name="response" class="feedback-input" placeholder="Comment" required minlength="20" maxlength="500"></textarea>
    <input type="submit" value="SUBMIT" />
</form>

<script>
    if ("${sessionScope.alertFlag}" === "true") {
        swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
    }
</script>
</body>

</html>