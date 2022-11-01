<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Title</title>
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
<form method="post" action="${pageContext.request.contextPath}/messageResponse">
  <input name="id" hidden value=${requestScope.message.id}>
  <input name="category" type="text" class="feedback-input" readonly value="${requestScope.message.category}" required/>
  <input name="adminId" type="number" class="feedback-input" readonly placeholder="Id" value="${requestScope.admin.id}" required/>
  <input name="request" class="feedback-input" value="${requestScope.message.message}" readonly >
  <textarea name="response" class="feedback-input" placeholder="Comment" required minlength="20" maxlength="500"></textarea>
  <input type="submit" value="SUBMIT" />
</form>
</body>

</html>