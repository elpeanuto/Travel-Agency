<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home-style.css">
    <style>

        body {
            font-family: "Open Sans", sans-serif;
            line-height: 1.25;
            background-color: #e8f5ff;
        }
        a
        {
            text-decoration: none;
        }
        .sticky {
            position: -webkit-sticky;
            position: sticky;
            top: 0;
        }

        table {
            border: 1px solid #ccc;
            border-collapse: collapse;
            margin: 0;
            padding: 0;
            width: 100%;
            /*margin-left: 5%;*/
            table-layout: fixed;
        }

        table caption {
            font-size: 1.5em;
            margin: .5em 0 .75em;
        }

        table tr {
            background-color: #FFFFFF;
            border: 1px solid #ddd;
            padding: .35em;
        }

        table th,
        table td {
            padding: .625em;
            text-align: center;
        }

        table th {
            font-size: .85em;
            letter-spacing: .1em;
            text-transform: uppercase;
        }

        @media screen and (max-width: 600px) {
            table {
                border: 0;
            }

            table caption {
                font-size: 1.3em;
            }

            table thead {
                border: none;
                clip: rect(0 0 0 0);
                height: 1px;
                margin: -1px;
                overflow: hidden;
                padding: 0;
                position: absolute;
                width: 1px;
            }

            table tr {
                border-bottom: 3px solid #ddd;
                display: block;
                margin-bottom: .625em;
            }

            table td {
                border-bottom: 1px solid #ddd;
                display: block;
                font-size: .8em;
                text-align: right;
            }

            table td::before {
                /*
                * aria-label has no advantage, it won't be read inside a table
                content: attr(aria-label);
                */
                content: attr(data-label);
                float: left;
                font-weight: bold;
                text-transform: uppercase;
            }

            table td:last-child {
                border-bottom: 0;
            }
        }

        /*Header Css*/
        * {box-sizing: border-box;}

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #d2e8fa;
        }

        .topnav a {
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #2196F3;
            color: white;
        }

        .topnav .search-container {
            float: right;
        }

        .topnav input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
        }

        .topnav .search-container button {
            float: right;
            padding: 6px 10px;
            margin-top: 8px;
            margin-right: 16px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .topnav .search-container button:hover {
            background: #ccc;
        }

        @media screen and (max-width: 600px) {
            .topnav .search-container {
                float: none;
            }
            .topnav a, .topnav input[type=text], .topnav .search-container button {
                float: none;
                display: block;
                text-align: left;
                width: 100%;
                margin: 0;
                padding: 14px;
            }
            .topnav input[type=text] {
                border: 1px solid #ccc;
            }

        }
        .footer {
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            background-color: #d2e8fa;
            color: black;
            text-align: center;
        }
    </style>
</head>

<br>
<div class="topnav sticky">
    <center>
        <h2>TIME TO REDACT</h2></center>
    <c:if test="${sessionScope.user.status == 'Admin' || sessionScope.user.status == 'Leader'}">
        <a href="${pageContext.request.contextPath}/addNewProduct">Add New Product</a>
    </c:if>
    <c:if test="${sessionScope.user.status == 'Admin' || sessionScope.user.status == 'Leader'}">
        <a href="${pageContext.request.contextPath}/allProductEdit?page=1">All Products & Edit Products</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/promoteUser?page=1">Promote User</a>
    <a href="${pageContext.request.contextPath}/promoteProduct?page=1">Promote Product</a>
    <a href="${pageContext.request.contextPath}/discount">Discount</a>
    <a href="${pageContext.request.contextPath}/allMessages?page=1">Messages Received</a>
    <a href="${pageContext.request.contextPath}/allOrders?page=1">Orders Received</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>
<br>