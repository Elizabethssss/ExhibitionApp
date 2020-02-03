<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 2/1/2020
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/purchase.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Credit Card</title>
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

<section class="alert alert-success ${message == null ? 'hide':''}" role="alert">
    ${message}
</section>

<main>
    <section>
        <div class="container">
            <div class="brd-crn">
                <ul>
                    <li><a href="index">Home</a></li>
                    <li>/</li>
                    <li><a href="purchase">Purchase</a></li>
                    <li>/</li>
                    <li><a href="">Credit Card</a></li>
                </ul>
            </div>

            <h1 class="text-center">Ticket Payment</h1>
            <form method="post" action="pay">
                <div class="card">
                    <div class="left">
                        <div class="card-wrapper">
                            <div class="card-back">
                                <div class="card-back-line"></div>
                                <input type="password" id="cvv" name="cardCVV" class="${errorPassword?'error':''}">
                            </div>
                            <div class="card-front">
                                <i class="fas fa-piggy-bank card-icon"></i>
                                <h2 class="card-name">Elizabeth Bank</h2>
                                <i class="fab fa-cc-visa card-logo-visa"></i>
                                <i class="fab fa-cc-mastercard card-logo-mc"></i>
                                <input type="text" id="card-number" name="cardNumber" class="${errorNumber?'error':''}"
                                       value="${cardNumber}">
                                <input type="text" id="card-to-month" name="cardMonth" class="${errorDate?'error':''}"
                                       value="${cardMonth}">
                                <input type="text" id="card-to-year" name="cardYear" class="${errorDate?'error':''}"
                                       value="${cardYear}">
                            </div>
                        </div>
                        <p class="errorMessage">${errorMessage}</p>
                    </div>
                    <div class="right">
                        <div class="total">
                            <p><span>Total: </span>${totalPrice} UAH</p>
                            <button class="pay-btn" id="btn" type="submit">Pay</button>
                        </div>
                    </div>
                </div>
            </form>
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
