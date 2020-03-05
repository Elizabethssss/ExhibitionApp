<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/16/2020
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles/LogIn.css">
    <title>${requestScope.bundle.getString("login")}</title>
</head>
<body>
<a href="login?lang=en">EN</a>/<a href="login?lang=ru">RU</a>
<form method="post" action="login?lang=${sessionScope.get("locale")}" class="login-form" accept-charset="ISO-8859-1">
    <h1>${requestScope.bundle.getString("login")}</h1>

    <div class="txtb ${requestScope.error?'error':''}">
        <label for="email">${requestScope.bundle.getString("email")}</label>
        <input type="text" id="email" name="email" autocomplete="off" value="teddy020301@gmail.com${requestScope.email}">
    </div>

    <div class="txtb ${requestScope.error?'error':''}">
        <label for="pass">${requestScope.bundle.getString("password")}</label>
        <input type="password" id="pass" name="password" autocomplete="off" value="123">
    </div>

    <div class="bottom-text">
        <span style="color: red">${requestScope.alarm?requestScope.bundle.getString("wrong_login"):''}</span>
    </div>

    <input type="submit" class="logbtn" value="${requestScope.bundle.getString("login")}">

    <div class="bottom-text">
           <span id="btm-txt">${requestScope.bundle.getString("warning_sign_up")}<br>
              ${requestScope.bundle.getString("not_sign_up")} </span><a href="signUp?lang=${sessionScope.get("locale")}">${requestScope.bundle.getString("sign_up")}</a>
    </div>

</form>
</body>
</html>
