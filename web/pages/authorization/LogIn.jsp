<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/16/2020
  Time: 5:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../styles/LogIn.css">
    <title>Login</title>
</head>
<body>

<form method="post" action="login" class="login-form" accept-charset="ISO-8859-1">
    <h1>Login</h1>

    <div class="txtb ${errorUsername?'error':''}">
        <label for="name">Username</label>
        <input type="text" id="name" name="username" autocomplete="off">
    </div>

    <div class="txtb ${errorPassword?'error':''}">
        <label for="pass">Password</label>
        <input type="password" id="pass" name="password" autocomplete="off">
    </div>

    <div class="bottom-text">
        <span style="color: red">${alarm}</span>
    </div>

    <input type="submit" class="logbtn" value="Login">

    <div class="bottom-text">
           <span id="btm-txt">You need to have an account to use the app<br>
               Don't have an account? </span><a href="signup">Sign Up</a>
    </div>

</form>
</body>
</html>
