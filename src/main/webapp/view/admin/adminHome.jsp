<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Welcome</title>
    <style>
        <%@include file="/css/home-style.css"%>
    </style>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">


</head>

<body>
<%@ include file="adminHeader.jspf" %>
<jsp:include page="../user/footer.jsp" />
</body>

</html>