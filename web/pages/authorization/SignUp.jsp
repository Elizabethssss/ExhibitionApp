<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/16/2020
  Time: 5:38 PM
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
    <link rel="stylesheet" href="../../styles/SignUp.css">
    <title>Sign Up</title>
</head>
<body>

<form method="post" action="signup" class="sign-up" accept-charset="ISO-8859-1">
    <h1>CREATE ACCOUNT</h1>

    <div class="txtb ${errorUsername?'error':''}" >
        <label for="name">Username</label>
        <input type="text" id="name" name="username" class="${errorSameUsername?'uname':''}"
               value="${username}">

    </div>

    <div class="txtb ${errorEmail?'error':''}">
        <label for="email">Email</label>
        <input type="text" id="email" name="email" class="${errorSameEmail?'uname':''}"
               value="${email}">
        
    </div>

    <div class="txtb ${errorPassword?'error':''}">
        <label for="pass1">Password</label>
        <input type="password" id="pass1" name="password">
       
    </div>

    <div class="txtb ${errorPassword?'error':''}">
        <label for="pass2">Repeat Password</label>
        <input type="password" id="pass2" name="password2">
       
    </div>

    <div class="bottom-text">
        <span id="btm-txt"> ${alarm} </span>
    </div>

    <button type="submit" class="signbtn" id="btn">Sign Up</button>

</form>
</body>
</html>
