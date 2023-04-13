<%--
  Created by IntelliJ IDEA.
  User: fandja
  Date: 12/04/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Résultats du jeu</title>
</head>
<body>
<div class="container-fluid">
    <div class="card">
        <div class="card-header">
            <h2>Résultats du chi fu mi</h2>
        </div>
        <div class="card-body">
            <p class="card-text">Joueur : ${resultPlayerChoice} </p>
            <p class="card-text">Serveur : ${resultComputerChoice} </p>
            <p>${result}</p>
            <form action="resultats" method="GET">
            <button type="submit" class="btn btn-primary" name="btnRestart" value="recommencer">Recommencer</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
