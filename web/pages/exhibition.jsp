<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/20/2020
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, userEntity-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/exposition.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Document</title>
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
                    <li><a href="index">Home</a></li>
                    <li>/</li>
                    <li><a href="">Exhibition</a></li>
                </ul>
            </div>

            <form action="buying?id=${exhib.get().getId()}&page=${pageNumber}" method="post">
                <div class="show">
                    <div class="show__photo">
                        <img src="${exhib.get().getImage()}" alt="exposition photo">
                    </div>
                    <div class="description">
                        <p class="nameExhib">${exhib.get().getName()}</p>
                        <p><span>Date: </span> ${exhib.get().getDateFrom()}</p>
                        <p><span>Theme: </span> ${exhib.get().getTheme()}</p>
                        <p><span>About: </span>${exhib.get().getAbout()} ${exhib.get().getLongAbout()}</p>
                        <p><span>Price: </span> ${exhib.get().getPrice()} UAH</p>
                        <button class="buy-btn" type="submit">Buy</button>
                        <p style="float: right;">${toCart}</p>
                    </div>
                </div>
            </form>
            <div class="title">
                <h1>List of Expositions:</h1>
            </div>

            <c:forEach var="exposition" items="${expositions}">
                <div class="expositionEntities">
                    <div class="exposition">
                        <div class="expo__photo">
                            <img src="${exposition.getImage()}" alt="">
                        </div>
                        <div class="expo__desc">
                            <p class="nameExhib">${exposition.getName()}</p>
                            <p><span>Description: </span> ${exposition.getAbout()}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="next-previous">
                <div class="previous">
                    <a class="next-previous-btn np-btn ${pageNumber<=1?'hide':''}" href="exhibition?id=${exhib.get().getId()}&page=${pageNumber-1}">Previous</a>
                </div>
                <div class="next">
                    <a class="next-previous-btn np-btn ${(numberOfExpositions<=(pageNumber*3))?'hide':''}" href="exhibition?id=${exhib.get().getId()}&page=${pageNumber+1}">Next</a>
                </div>
            </div>
            <jsp:include page="commons/footer.jsp"/>
        </div>
    </section>
</main>

</body>
</html>
