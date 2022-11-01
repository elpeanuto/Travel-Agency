<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />

<!DOCTYPE html>
<html lang="en">
<fmt:bundle basename="viewBundle">
    <head>
        <title><fmt:message key="signIn"/></title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/travel.png" type="image/png">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: "Poppins", sans-serif;
            }
            body{
                background: #dfe9f5;
            }
            .wrapper{
                width: 330px;
                padding: 2rem 0 1rem 0;
                margin: 50px auto;
                background: #fff;
                border-radius: 10px;
                text-align: center;
                box-shadow: 0 20px 35px rgba(0, 0, 0, 0.1);
            }
            h1{
                font-size: 2rem;
                color: #07001f;
            }
            p{
                margin-bottom: 1.7rem;
            }
            form input{
                width: 85%;
                outline: none;
                border: none;
                background: #dfe9f5;
                padding: 12px 14px;
                margin-bottom: 10px;
                border-radius: 10px;
            }
            .recover{
                text-align: right;
                font-size: 0.8rem;
                margin: 0.2rem 1.7rem 0 0;
            }
            .recover a{
                text-decoration: none;
                color: #07001f;
            }
            button{
                font-size: 1.1rem;
                margin-top: 1rem;
                padding: 8px 0;
                border-radius: 5px;
                outline: none;
                border: none;
                width: 85%;
                background: tomato;
                color: #fff;
                cursor: pointer;
            }
            button:hover{
                background: rgba(122, 30, 30, 0.767);
            }
            .or{
                font-size: 0.8rem;
                margin-top: 1.5rem;
            }
            .icons i{
                color: #07001f;
                padding: 00.8rem 1.5rem;
                border-radius: 10px;
                margin-left: .9rem;
                font-size: 1.5rem;
                cursor: pointer;
                border: 2px solid #dfe9f5;
            }
            .icons i:hover{
                color: #fff !important;
                background: #07001f;
                transition: 1s;
            }
            .icons i:first-child{
                color: green;
            }
            .icons i:last-child{
                color: blue;
            }
            .not-member{
                font-size: 0.8rem;
                margin-top: 1.4rem;
            }
            .not-member a{
                color: tomato;
                text-decoration: none;
            }
            a:hover{
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
    <div class="wrapper">
        <h1><fmt:message key="helloAgain"/>!</h1>
        <p>Travel Management Systems</p>
        <form method="post" action="login">
            <input type="email" name="email" placeholder="<fmt:message key="enterEmail"/>" required="required" pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" maxlength="50"/>
            <input type="password" name="password" placeholder="<fmt:message key="password"/>" required="required" minlength="5" maxlength="20" />
            <p class="recover">
                <a href="forgotPassword"><fmt:message key="forgetpassword"/>?</a>
            </p>

            <button><fmt:message key="signIn"/></button>
        </form>
        <div class="not-member">
            <fmt:message key="notAMember"/>? <a href="registration"><fmt:message key="register"/></a>
        </div>
    </div>
    </body>

</fmt:bundle>
</html>