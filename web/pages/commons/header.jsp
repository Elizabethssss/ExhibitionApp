<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 2/14/2020
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
    <div class="container">
        <div class="header__inner">
            <div class="logo">
                <h1><a href="index"><em class="fas fa-crown"></em> Elizabeth!!!</a></h1>
            </div>
            <nav id="main__menu">
                <ul>
                    <li class="hello">Hello, <span class="userEntity-name">${user.get().getUsername()}</span> !</li>
                    <a href="profile?page=1" class="btn">My profile</a>
                    <a href="purchase" class="btn">${inCart} <em class="fas fa-shopping-basket"></em></a>
                    <a href="" id="logout" class="btn">Log out</a>
                </ul>
            </nav>
        </div>
    </div>
    <script>
        let tickets = ${inCart};
        document.getElementById("logout").addEventListener("click", function (e) {
            e.preventDefault();
            if (tickets===0 || confirm("You have " + tickets + " ticket(s) in cart. Do you want to exit?")) document.location = "logout";
        })
    </script>
</header>
