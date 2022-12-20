<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />

<html>
<fmt:bundle basename="viewBundle">

    <head>
        <title>Bill</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bill.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
    </head>

    <body>

    <div class="receipt-content">
        <div class="container bootstrap snippets bootdey">
            <div class="row">
                <div class="col-md-12 print">
                    <div class="invoice-wrapper">
                        <div class="intro">
                            <fmt:message key="hello" />, <strong>${requestScope.order.realName} ${requestScope.order.realSurname}</strong>,
                            <br>
                            <fmt:message key="thisIsReceipt" /> <strong>$${requestScope.product.price}</strong> (USD)
                        </div>

                        <div class="payment-info">
                            <div class="row">
                                <div class="col-sm-6 text-right">
                                        <span>
                                            <fmt:message key="paymentDate" />
                                        </span>
                                    <strong>${requestScope.order.date}</strong>
                                </div>
                            </div>
                        </div>

                        <div class="payment-details">
                            <div class="row">
                                <div class="col-sm-6">
                                        <span>
                                            <fmt:message key="client" />
                                        </span>
                                    <strong>
                                        <fmt:message key="clientInfo" />
                                    </strong>
                                    <p>
                                            ${requestScope.order.realName} ${requestScope.order.realSurname}
                                        <br> ${requestScope.order.phoneNumber}
                                        <br> ${requestScope.order.dateOfBirth}
                                        <br> ${requestScope.order.citizenship}
                                        <br> ${requestScope.order.email}
                                    </p>
                                </div>
                                <div class="col-sm-6 text-right">
                                        <span>
                                            <fmt:message key="paymentTo" />
                                        </span>
                                    <strong>
                                        Travel Management System
                                    </strong>
                                    <p>
                                        344 9th Avenue
                                        <br> San Francisco
                                        <br> 99383
                                        <br> USA
                                        <br> travelManagementSystem@gmail.com
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="line-items">
                            <div class="headers clearfix">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <fmt:message key="tourId" />
                                    </div>
                                    <div class="col-xs-3">
                                        <fmt:message key="tourName" />
                                    </div>
                                    <div class="col-xs-5 text-right">
                                        <fmt:message key="numberOfTourists" />
                                    </div>
                                </div>
                            </div>
                            <div class="items">
                                <div class="row item">
                                    <div class="col-xs-4 desc">
                                        <fmt:message key="tourId" />
                                    </div>
                                    <div class="col-xs-3 qty">
                                            ${requestScope.product.id}
                                    </div>
                                </div>
                                <div class="row item">
                                    <div class="col-xs-4 desc">
                                        <fmt:message key="tourName" />
                                    </div>
                                    <div class="col-xs-3 qty">
                                            ${requestScope.product.name}
                                    </div>
                                </div>
                                <div class="row item">
                                    <div class="col-xs-4 desc">
                                        <fmt:message key="numberOfTourists" />
                                    </div>
                                    <div class="col-xs-3 qty">
                                            ${requestScope.product.numberOfTourists}
                                    </div>
                                </div>
                            </div>
                            <div class="total text-right">
                                <p class="extra-notes">
                                    <strong>
                                        <fmt:message key="extraNotes" />
                                    </strong>
                                    <fmt:message key="plesaPayDuring" />
                                </p>
                                <div class="field">
                                    <fmt:message key="subtotal" /> <span>$${requestScope.product.price}</span>
                                </div>
                                <div class="field">
                                    <fmt:message key="discount" /> <span>${requestScope.order.totalDiscount}%</span>
                                </div>
                                <div class="field grand-total">
                                    <fmt:message key="total" /> <span>$${requestScope.order.totalPrice}</span>
                                </div>
                            </div>

                            <div class="print">
                                <a href="${pageContext.request.contextPath}/myOrders?page=1">
                                    <i class="fa fa-print"></i>
                                    <fmt:message key="myOrders" />
                                </a>
                                <a href="${pageContext.request.contextPath}/allProduct?page=1">
                                    <i class="fa fa-print"></i>
                                    <fmt:message key="tours" />
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="footer">
                        Copyright © 2022. Elpeanuto
                    </div>
                </div>
            </div>
        </div>
    </div>

    </body>
</fmt:bundle>

</html>