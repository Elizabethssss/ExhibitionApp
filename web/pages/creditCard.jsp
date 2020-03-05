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
          content="width=device-width, userEntity-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/purchase.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Credit Card</title>
</head>
<body>

<jsp:include page="commons/header.jsp"/>

<section class="alert alert-success ${message == null ? 'hide':''}" roleEntity="alert">
    ${message}
</section>

<main>
    <section>
        <div class="container">
            <div class="brd-crn">
                <ul>
                    <li><a href="index?lang=${sessionScope.get("locale")}">${requestScope.bundle.getString("home")}</a></li>
                    <li>/</li>
                    <li><a href="purchase?lang=${sessionScope.get("locale")}">${requestScope.bundle.getString("purchase")}</a></li>
                    <li>/</li>
                    <li><a href="">${requestScope.bundle.getString("credit_card")}</a></li>
                </ul>
            </div>

            <h1 class="text-center">${requestScope.bundle.getString("ticket_payment")}</h1>
            <form method="post" action="pay?lang=${sessionScope.get("locale")}">
                <div class="card">
                    <div class="left">
                        <div class="card-wrapper">
                            <div class="card-back">
                                <div class="card-back-line"></div>
                                <input type="password" id="cvv" name="cardCVV" class="${errorPassword?'error':''}">
                            </div>
                            <div class="card-front">
                                <em class="fas fa-piggy-bank card-icon"></em>
                                <h2 class="card-name">Elizabeth Bank</h2>
                                <em class="fab fa-cc-visa card-logo-visa"></em>
                                <em class="fab fa-cc-mastercard card-logo-mc"></em>
                                <input type="text" id="card-number" name="cardNumber" class="${errorNumber?'error':''}"
                                       value="378282246310005">
<%--                                       value="${cardNumber}">--%>
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
                            <p><span>${requestScope.bundle.getString("total")}: </span>${totalPrice} ${requestScope.bundle.getString("currency")}</p>
                            <button class="pay-btn" id="btn" type="submit">${requestScope.bundle.getString("pay")}</button>
                        </div>
                    </div>
                </div>
            </form>
            <jsp:include page="commons/footer.jsp"/>
        </div>
    </section>
</main>
</body>
</html>
