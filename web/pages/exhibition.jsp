<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/20/2020
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../styles/exposition.css">
    <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
    <title>Document</title>
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
                    <a href="myProfile?page=0" class="next-previous-btn">My profile</a>
                    <a href="purchase" class="next-previous-btn">${inCart} <i class="fas fa-shopping-basket"></i></a>
                    <a href="logout" class="next-previous-btn">Log out</a>
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
                    <li><a href="">Exhibition</a></li>
                </ul>
            </div>

            <form action="buying?id=${exhib.get().getId()}&page=${page}" method="post">
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
                        <button class="buy-btn" type="submit">Buy</button>
                        <p style="float: right;">${toCart}</p>
                    </div>
                </div>
            </form>
            <div class="title">
                <h1>List of Expositions:</h1>
            </div>

            <c:forEach var="expositoion" items="${expositions}">
                <div class="expositions">
                    <div class="exposition">
                        <div class="expo__photo">
                            <img src="${expositoion.getImage()}" alt="">
                        </div>
                        <div class="expo__desc">
                            <p class="nameExhib">${expositoion.getName()}</p>
                            <p><span>Description: </span> ${expositoion.getAbout()}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="next-previous">
                <div class="previous">
                    <a class="next-previous-btn np-btn ${page<=0?'hide':''}" href="exhibition?id=${exhib.get().getId()}&page=${page-1}">Previous</a>
                </div>
                <div class="next">
                    <a class="next-previous-btn np-btn ${(numberOfExpo<=(page+1)*3)?'hide':''}" href="exhibition?id=${exhib.get().getId()}&page=${page+1}">Next</a>
                </div>
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
