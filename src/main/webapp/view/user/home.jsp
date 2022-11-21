<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<%@ taglib uri="elpTags" prefix="elp" %>

<!DOCTYPE html>
<html>
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="tours" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css" type="text/css" />
        <style>
            <%@include file="/css/home-style.css"%>
        </style>

        <style>
            .headName {
                color: black;
                text-align: center;
                font-size: 20px;
            }

            .hot {
                color: red;
            }
        </style>
    </head>

    <body>
    <%@ include file="header.jspf" %>
    <div class="headName">
        <fmt:message key="allTours" />
    </div>

    <form action="allProduct" method="get">
        <td>
            <select name="category">
                <option value="All">
                    <fmt:message key="allCategories" />
                </option>

                <option <c:if test="${requestScope.filter.category == 'Rest'}">
                    selected
                </c:if>
                        value="Rest">
                    <fmt:message key="rest" />
                </option>
                <option <c:if test="${requestScope.filter.category == 'Excursion'}">
                    selected
                </c:if>
                        value="Excursion">
                    <fmt:message key="excursion" />
                </option>
                <option <c:if test="${requestScope.filter.category == 'Shopping'}">
                    selected
                </c:if>
                        value="Shopping">
                    <fmt:message key="shopping" />
                </option>
            </select>
        </td>
        <td>
            <label>
                <select name="hotelType">
                    <option value="All">
                        <fmt:message key="allTypes" />
                    </option>
                    <option <c:if test="${requestScope.filter.hotelType == 'Resort'}">
                        selected
                    </c:if>
                            value="Resort">
                        <fmt:message key="resort" />
                    </option>
                    <option <c:if test="${requestScope.filter.hotelType == 'Hostel'}">
                        selected
                    </c:if>
                            value="Hostel">
                        <fmt:message key="hostel" />
                    </option>
                    <option <c:if test="${requestScope.filter.hotelType == 'Motel'}">
                        selected
                    </c:if>
                            value="Motel">
                        <fmt:message key="motel" />
                    </option>
                    <option <c:if test="${requestScope.filter.hotelType == 'Timeshare'}">
                        selected
                    </c:if>
                            value="Timeshare">
                        <fmt:message key="timeShare" />
                    </option>
                </select>
            </label>
        </td>

        <input type="text" placeholder="<fmt:message key="priceMin" />" name="minPrice" min="1" value="${requestScope.filter.minPrice}" pattern="\d*" maxlength="6">
        <input type="text" placeholder="<fmt:message key="priceMax" />" name="maxPrice" min="1" value="${requestScope.filter.maxPrice}" pattern="\d*" maxlength="6">
        <input type="text" placeholder="<fmt:message key="numberOfTourists" />" min="1" name="numberOfTourists" value="${requestScope.filter.numberOfTourists}" pattern="\d*" maxlength="6">
        <input type="text" placeholder="<fmt:message key="search" />" name="search" value="${requestScope.filter.searchPattern}" maxlength="50">

        <input type="hidden" name="page" value="${requestScope.currentPosition}">


        <button type="submit">
            <fmt:message key="search" />
        </button>

        <a href="${pageContext.request.contextPath}/allProduct?page=1">
            <fmt:message key="reset" />
        </a>
    </form>

    <table>
        <thead>
        <tr>
            <th scope="col">
                <fmt:message key="name" />
            </th>
            <th scope="col">
                <fmt:message key="country" />
            </th>
            <th scope="col">
                <fmt:message key="city" />
            </th>
            <th scope="col">
                <fmt:message key="price" />
            </th>
            <th scope="col">
                <fmt:message key="tourists" />
            </th>
            <th scope="col">
                <fmt:message key="view" />
            </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="product" items="${requestScope.productList}">

            <tr>
                <elp:productTag product="${product}" />
                <td>
                    <a href="viewProduct?id=${product.id}">
                        <fmt:message key="view" />
                    </a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <br>
    <br>
    <br>
    <c:if test="${requestScope.positionList != null}">
        <div class="pagination">
            <c:forEach var="position" items="${requestScope.positionList}">
                <a href="allProduct?page=${position}&category=${requestScope.filter.category}&hotelType=${requestScope.filter.hotelType}&minPrice=${requestScope.filter.minPrice}&maxPrice=${requestScope.filter.maxPrice}&numberOfTourists=${requestScope.filter.numberOfTourists}&search=${requestScope.filter.searchPattern}">${position}</a>
            </c:forEach>
        </div>
    </c:if>

    <jsp:include page="footer.jsp" />

    <script>
        if ("${sessionScope.alertFlag}" === "true") {
            swal("${sessionScope.alertHeader}", "${sessionScope.alertBody}", "${sessionScope.alertType}")
        }
    </script>
    </body>
</fmt:bundle>

</html>