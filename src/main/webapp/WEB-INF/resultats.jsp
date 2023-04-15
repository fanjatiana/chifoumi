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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Résultats du jeu</title>
</head>
<body>
<header>
    <h1 class="text-center mt-4 mb-5"></h1>
</header>
<div class="container-fluid d-flex justify-content-around col-9">
    <div class=" row col-3">
        <div>
            <h4 class="mb-3">Déroulement de la partie:</h4>
        </div>
        <div>
            <p>Vous avez joué : ${playerChoice} </p>
            <p> Le serveur a joué : ${serverChoice} </p>
            <p class="fw-bold"> Résultat: ${gameResult}</p>
            <form action="resultats" method="GET">
                <button type="submit" class="btn btn-primary" name="btnRestart" value="recommencer">Recommencer</button>
            </form>
        </div>
    </div>
    <div class=" row card col-4">
        <div class="card-header">
            <h4>Décompte des points</h4>
        </div>
        <div class="card-body">
            <p class="card-text">nombre de victoire du joueur :${playerSessionReport} </p>
            <p class="card-text">nombre de victoire du serveur : ${serverSessionReport} </p>
            <p class="card-text">nombre d'égalité :${egalitySessionReport}</p>
        </div>
        <div class="card-body">
            <p class="text-success fw-bold">${finalResult}</p>
        </div>
    </div>
</div>
<footer></footer>
</body>
</html>
