<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 2/14/2020
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    String query = request.getQueryString();
    if(query == null) {
        query = "";
    }
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
    <a href="<%= request.getContextPath() + "?" + query.replace("lang=ru", "lang=en") %>">EN</a>/
    <a href="<%= request.getContextPath() + "?" + query.replace("lang=en", "lang=ru") %>">RU</a>
    <div class="container">
        <div class="header__inner">
            <div class="logo">
                <h1><a href="index?lang=${sessionScope.get("locale")}"><em class="fas fa-crown"></em> Elizabeth!!!</a></h1>
            </div>
            <nav id="main__menu">
                <ul>
                    <li class="hello">${requestScope.bundle.getString("greeting")}, <span class="userEntity-name">${sessionScope.user.get().getUsername()}</span> !</li>
                    <a href="profile?page=1&lang=${sessionScope.get("locale")}" class="btn">${requestScope.bundle.getString("profile")}</a>
                    <a href="purchase?lang=${sessionScope.get("locale")}" class="btn">${requestScope.inCart} <em class="fas fa-shopping-basket"></em></a>
                    <a href="" id="logout" class="btn">${requestScope.bundle.getString("log_out")}</a>
                </ul>
            </nav>
        </div>
    </div>
    <script>
        let tickets = ${requestScope.inCart};
        document.getElementById("logout").addEventListener("click", function (e) {
            e.preventDefault();
            if (tickets===0 || confirm("You have " + tickets + " ticket(s) in cart. Do you want to exit?")) document.location = "logout";
        })
    </script>
</header>
