<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 1/15/2020
  Time: 9:11 PM
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
  <script src="https://kit.fontawesome.com/9549242389.js" crossorigin="anonymous"></script>
  <title>Document</title>
</head>
<body>

<header>
  <div class="container">
    <div class="header__inner">
      <div class="logo">
        <h1><a href="#"><i class="fas fa-crown"></i> Elizabeth!!!</a></h1>
      </div>
      <nav id="main__menu">
        <ul>
          <li class="hello">Hello, <span class="user-name">${username}</span> !</li>
          <a href="myProfile?page=0" class="btn">My profile</a>
          <a href="purchase" class="btn">${inCart} <i class="fas fa-shopping-basket"></i></a>
          <a href="logout" class="btn">Log out</a>
        </ul>
      </nav>
    </div>
  </div>
</header>

<main>
  <section>
    <div class="container">
      <div class="theme">
        <select>
          <option value="1">Sec 1</option>
          <option value="2">Sec 2</option>
          <option value="3">Sec 3</option>
          <option value="4">Sec 4</option>
          <option value="5">Sec 5</option>
        </select>
      </div>

      <c:forEach var="month" items="${exhibMap.keySet()}">
      <div class="exposition">
        <div class="month">
            <c:if test="${!exhibMap.get(month).isEmpty()}">
              <h2>${month}</h2>

            </c:if>
        </div>
        <c:forEach var="exhib" items="${exhibMap.get(month)}">
          <a class="exhib-card" href="exhibition?id=${exhib.getId()}&page=0">
          <div class="descriptions">
            <div class="expo__photo">
              <img src="${exhib.getImage()}" alt="exposition photo" class="img150">
            </div>
            <div class="description">
              <p class="nameExhib">${exhib.getName()}</p>
              <p><span>Date: </span> ${exhib.getDate()}</p>
              <p><span>Theme: </span> ${exhib.getTheme()}</p>
              <p><span>About: </span>${exhib.getAbout()}</p>
              <p><span>Price: </span> ${exhib.getPrice()} UAH</p>
            </div>
          </div>
          </a>
        </c:forEach>

      </div>
      </c:forEach>



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
