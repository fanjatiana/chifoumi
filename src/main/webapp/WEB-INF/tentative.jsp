<%--
  Created by IntelliJ IDEA.
  User: fandja
  Date: 13/04/2023
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Jeu Chi fu mi </title>
</head>
<body>
<header>
    <h1>Chi Fu Mi</h1>
</header>
<div class="container-fluid">
    <div>
        <h2>Joueur</h2>
        <div>
            <form method="post" action="tentative">
                <button type="submit" class="btn btn-primary" name="playerChoice" value="Chi">Chi</button>
                <button type="submit" class="btn btn-primary" name="playerChoice" value="Fu">Fu</button>
                <button type="submit" class="btn btn-primary" name="playerChoice" value="Mi">Mi</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
