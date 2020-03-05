<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 2/2/2020
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/index.css">
    <link rel="stylesheet" href="../styles/purchase.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Document</title>
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
                    <li><a href="">${requestScope.bundle.getString("profile")}</a></li>
                </ul>
            </div>

            <div class="title">
                <h1>${requestScope.bundle.getString("your_tickets")}:</h1>
            </div>
            <section ${requestScope.ticketsMap.isEmpty()?'':'hidden'} class="alert alert-warning" roleEntity="alert">
                You haven't bought tickets yet!
            </section>
            <c:forEach var="key" items="${ticketsMap.keySet()}">
                <c:set var="exhib" value="${ticketsMap.get(key)}"/>
                <div class="show">
                    <div class="show__photo">
                        <img src="${exhib.get().getImage()}" alt="exposition photo" class="img150">
                    </div>
                    <div class="description">
                        <p class="nameExhib">${exhib.get().getName()}</p>
                        <p><span>${requestScope.bundle.getString("date")}: </span> ${exhib.get().getDateFrom()}</p>
                        <p><span>${requestScope.bundle.getString("theme")}: </span> ${exhib.get().getTheme()}</p>
                        <p><span>${requestScope.bundle.getString("about")}: </span>${exhib.get().getAbout()}</p>
                        <p><span>${requestScope.bundle.getString("price")}: </span> ${exhib.get().getPrice()} UAH</p>

                    </div>
                </div>
            </c:forEach>
            <div class="next-previous">
                <div class="previous">
                    <a class="next-previous-btn ${pageNumber<=1?'hide':''}" href="profile?page=${pageNumber-1}&lang=${sessionScope.get("locale")}">
                        ${requestScope.bundle.getString("previous")}</a>
                </div>
                <div class="next">
                    <a class="next-previous-btn ${(numberOfTickets<=(pageNumber*5))?'hide':''}" href="profile?page=${pageNumber+1}&lang=${sessionScope.get("locale")}">
                        ${requestScope.bundle.getString("next")}</a>
                </div>
            </div>

            <jsp:include page="commons/footer.jsp"/>
        </div>
    </section>
</main>

</body>
</html>
