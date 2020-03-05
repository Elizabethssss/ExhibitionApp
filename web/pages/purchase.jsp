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
          content="width=device-width, userEntity-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/purchase.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Purchase</title>
</head>
<body>

<jsp:include page="commons/header.jsp"/>

<main>
    <section>
        <div class="container">
            <div class="brd-crn">
                <ul>
                    <li><a href="index?lang=${sessionScope.get("locale")}">${requestScope.bundle.getString("home")}</a></li>
                    <li>/</li>
                    <li><a href="">${requestScope.bundle.getString("purchase")}</a></li>
                </ul>
            </div>
                <section ${requestScope.ticketsMap.isEmpty()?'':'hidden'} class="alert alert-warning" roleEntity="alert">
                        ${requestScope.bundle.getString("no_tickets")}
                </section>
            <c:forEach var="key" items="${ticketsMap.keySet()}">
                <c:set var="exhib" value="${ticketsMap.get(key)}"/>
                <div class="show">
                    <div class="show__photo">
                        <img src="${exhib.get().getImage()}" alt="exposition photo">
                    </div>
                    <div class="description">
                        <p class="nameExhib">${exhib.get().getName()}</p>
                        <p><span>${requestScope.bundle.getString("date")}: </span> ${exhib.get().getDateFrom()}</p>
                        <p><span>${requestScope.bundle.getString("theme")}: </span> ${exhib.get().getTheme()}</p>
                        <p><span>${requestScope.bundle.getString("about")}: </span>${exhib.get().getAbout()} ${exhib.get().getLongAbout()}</p>
                        <p><span>${requestScope.bundle.getString("price")}: </span> ${exhib.get().getPrice()} UAH</p>

                    </div>
                    <div class="close">
                        <a href="removeTicket?id=${key}&lang=${sessionScope.get("locale")}">&#10008;</a>
                    </div>
                </div>
            </c:forEach>

            <div ${requestScope.ticketsMap.isEmpty()?'hidden':''} class="total">
                <a class="pay-btn p-btn" href="pay?lang=${sessionScope.get("locale")}">${requestScope.bundle.getString("pay")}</a>
                <p><span>${requestScope.bundle.getString("total")}: </span>${totalPrice} ${requestScope.bundle.getString("currency")}</p>
            </div>

            <jsp:include page="commons/footer.jsp"/>
        </div>
    </section>
</main>
</body>
</html>