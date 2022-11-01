<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="viewBundle">

    <head>
        <title>
            <fmt:message key="contactUs" />
        </title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">

        <style>
            @import url(https://fonts.googleapis.com/css?family=Montserrat:400,700);
            body {
                background: #e8f5ff;
            }

            form {
                max-width: 420px;
                margin: 50px auto;
            }


            .feedback-input {
                color: black;
                font-family: Helvetica, Arial, sans-serif;
                font-weight: 500;
                font-size: 18px;
                border-radius: 5px;
                line-height: 22px;
                background-color: transparent;
                border: 2px solid #CC6666;
                transition: all 0.3s;
                padding: 13px;
                margin-bottom: 15px;
                width: 100%;
                box-sizing: border-box;
                outline: 0;
            }

            .feedback-input:focus {
                border: 2px solid #CC4949;
            }

            textarea {
                height: 150px;
                line-height: 150%;
                resize: vertical;
            }

            [type="submit"] {
                font-family: 'Montserrat', Arial, Helvetica, sans-serif;
                width: 100%;
                background: #CC6666;
                border-radius: 5px;
                border: 0;
                cursor: pointer;
                color: white;
                font-size: 24px;
                padding-top: 10px;
                padding-bottom: 10px;
                transition: all 0.3s;
                margin-top: -4px;
                font-weight: 700;
            }

            [type="submit"]:hover {
                background: #CC4949;
            }
        </style>
    </head>

    <body>
    <form method="post" action="${pageContext.request.contextPath}/message">

        <input name="name" type="text" class="feedback-input" placeholder="<fmt:message key="name"/>" value="${requestScope.user.name}" required pattern="^[A-ZА-Я]+[a-zа-я]*$" minlength="3" maxlength="20"/>
        <input readonly name="email" type="text" class="feedback-input" placeholder="<fmt:message key="email"/>" value="${requestScope.user.email}"  required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50"/>
        <select name="category" class="feedback-input">
            <option value="websiteProblem">
                <fmt:message key="problemWithTheWebsite" />
            </option>
            <option value="technicalProblem">
                <fmt:message key="technicalProblem" />
            </option>
            <option value="orderProblem">
                <fmt:message key="problemWithOrder" />
            </option>
        </select>
        <textarea name="message" class="feedback-input" placeholder="<fmt:message key="message"/>" required minlength="20" maxlength="500"></textarea>
        <input type="submit" value="<fmt:message key="submit"/>"/>
    </form>
    </body>
</fmt:bundle>

</html>