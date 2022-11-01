<jsp:include page="adminHeader.jsp" />
<jsp:include page="../user/footer.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Promote product</title>
    <style>
        h3 {
            color: yellow;
            text-align: center;
        }
    </style>
</head>

<body>
<div style="color: black; text-align: center; font-size: 20px;">Promote product</div>

<table>
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Category</th>
        <th scope="col">Active</th>
        <th scope="col">Price</th>
        <th>Type</th>
        <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.active}</td>
            <td>${product.price}</td>
            <td>${product.type}</td>
            <form action="promoteProduct?id=${product.id}" method="post">
                <td>
                    <select class="input-style" name="status">

                        <option <c:if test="${product.type == 'ordinary'}">
                            selected
                        </c:if>
                                value="Ordinary">Ordinary
                        </option>
                        <option <c:if test="${product.type == 'hot'}">
                            selected
                        </c:if>
                                value="Hot">Hot
                        </option>
                    </select>
                    <button class="button">Save<i class='far fa-arrow-alt-circle-right'></i></button>

                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<br>
<c:if test="${requestScope.positionList != null}">
    <div class="pagination" style="text-align: center;">
        <c:forEach var="position" items="${requestScope.positionList}">
            <a href="promoteProduct?page=${position}">${position}</a>
        </c:forEach>
    </div>
</c:if>
</body>

</html>