<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/27/2020
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/purchase.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Purchase</title>
</head>
<body>

<header>
    <div class="container">
        <div class="header__inner">
            <div class="logo">
                <h1><a href="index"><i class="fas fa-crown"></i> Elizabeth!!!</a></h1>
            </div>
            <nav id="main__menu">
                <ul>
                    <li class="hello">Hello, <span class="user-name">${username}</span> !</li>
                    <a href="myProfile?page=0" class="pay-btn">My profile</a>
                    <a href="purchase" class="pay-btn">${inCart} <i class="fas fa-shopping-basket"></i></a>
                    <a href="logout" class="pay-btn">Log out</a>
                </ul>
            </nav>
        </div>
    </div>
</header>

<main>
    <section>
        <div class="container">
            <div class="brd-crn">
                <ul>
                    <li><a href="index">Home</a></li>
                    <li>/</li>
                    <li><a href="">Purchase</a></li>
                </ul>
            </div>
                <section ${requestScope.ticketsMap.isEmpty()?'':'hidden'} class="alert alert-warning" role="alert">
                        You haven't chosen exhibition!
                </section>
            <c:forEach var="key" items="${ticketsMap.keySet()}">
                <c:set var="exhib" value="${ticketsMap.get(key)}"/>
                <div class="show">
                    <div class="show__photo">
                        <img src="${exhib.get().getImage()}" alt="exposition photo">
                    </div>
                    <div class="description">
                        <p class="nameExhib">${exhib.get().getName()}</p>
                        <p><span>Date: </span> ${exhib.get().getDate()}</p>
                        <p><span>Theme: </span> ${exhib.get().getTheme()}</p>
                        <p><span>About: </span>${exhib.get().getAbout()} ${exhib.get().getLongAbout()}</p>
                        <p><span>Price: </span> ${exhib.get().getPrice()} UAH</p>

                    </div>
                    <div class="close">
                        <a href="removeTicket?id=${key}">&#10008;</a>
                    </div>
                </div>
            </c:forEach>

            <div class="total">
                <a class="pay-btn p-btn" href="pay">Pay</a>
                <p><span>Total: </span>${totalPrice} UAH</p>
            </div>

            <footer>
                <div class="footer__inner">
                    &copy; Valteris Elizabeth 2020
                </div>
            </footer>
        </div>
    </section>
</main>
</body>
</html>