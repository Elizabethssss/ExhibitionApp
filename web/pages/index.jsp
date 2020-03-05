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

<jsp:include page="commons/header.jsp"/>
<%--  <jsp:param name="inCart" value="${inCart}"/>--%>
<%--</jsp:include>--%>

<main>
  <section>
    <div class="container">
<%--      <div class="theme">--%>
<%--        <select>--%>
<%--          <option value="1">Sec 1</option>--%>
<%--          <option value="2">Sec 2</option>--%>
<%--          <option value="3">Sec 3</option>--%>
<%--          <option value="4">Sec 4</option>--%>
<%--          <option value="5">Sec 5</option>--%>
<%--        </select>--%>
<%--      </div>--%>

      <c:forEach var="month" items="${requestScope.exhibMap.keySet()}">
      <div class="exposition">
        <div class="month">
            <c:if test="${!requestScope.exhibMap.get(month).isEmpty()}">
              <h2>${month}</h2>
            </c:if>
        </div>
        <c:forEach var="exhib" items="${requestScope.exhibMap.get(month)}">
          <a class="exhib-card" href="exhibition?id=${exhib.getId()}&page=1&lang=${sessionScope.get("locale")}">
          <div class="descriptions">
            <div class="expo__photo">
              <img src="${exhib.getImage()}" alt="exposition photo" class="img150">
            </div>
            <div class="description">
              <p class="nameExhib">${exhib.getName()}</p>
              <p><span>${requestScope.bundle.getString("date")}: </span> ${exhib.getDateFrom()} — ${exhib.getDateTo()}</p>
              <p><span>${requestScope.bundle.getString("theme")}: </span> ${exhib.getTheme()}</p>
              <p><span>${requestScope.bundle.getString("about")}: </span>${exhib.getAbout()}</p>
              <p><span>${requestScope.bundle.getString("price")}: </span> ${exhib.getPrice()} ${requestScope.bundle.getString("currency")}</p>
            </div>
          </div>
          </a>
        </c:forEach>

      </div>
      </c:forEach>

      <jsp:include page="commons/footer.jsp"/>
    </div>
  </section>
</main>

</body>
</html>
