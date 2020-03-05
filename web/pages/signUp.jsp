<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/16/2020
  Time: 5:38 PM
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
    <link rel="stylesheet" href="../styles/SignUp.css">
    <title>${requestScope.bundle.getString("sign_up")}</title>
</head>
<body>
<a href="signUp?lang=en">EN</a>/<a href="signUp?lang=ru">RU</a>
<form method="post" action="signUp?lang=${sessionScope.get("locale")}" class="sign-up" accept-charset="ISO-8859-1">
    <h1>${requestScope.bundle.getString("create_account")}</h1>

    <div class="txtb ${requestScope.errorUsername?'error':''}" >
        <label for="name">${requestScope.bundle.getString("username")}</label>
        <input type="text" id="name" name="username" value="Валера${requestScope.username}">

    </div>

    <div class="txtb ${requestScope.errorEmail?'error':''}">
        <label for="email">${requestScope.bundle.getString("email")}</label>
        <input type="text" id="email" name="email" value="re@re${requestScope.email}">
        
    </div>

    <div class="txtb ${requestScope.errorPassword?'error':''}">
        <label for="pass1">${requestScope.bundle.getString("password")}</label>
        <input type="password" id="pass1" name="password1">
       
    </div>

    <div class="txtb ${requestScope.errorPassword?'error':''}">
        <label for="pass2">${requestScope.bundle.getString("re_password")}</label>
        <input type="password" id="pass2" name="password2">
       
    </div>

    <div class="bottom-text">
        <span id="btm-txt"> ${requestScope.alarm} </span>
    </div>

    <button type="submit" class="signbtn" id="btn">${requestScope.bundle.getString("sign_up")}</button>

</form>
</body>
</html>
