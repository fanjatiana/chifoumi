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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Jeu Chi fu mi </title>
</head>
<body>
<header>
    <h1 class="text-center mt-4 mb-5">Chi Fu Mi</h1>
</header>
<div class="container-fluid d-flex justify-content-center align-items-center col-8">
    <div>
        <h4 class="mb-3">Règle du jeu</h4>
        <p>Cliquez sur les boutons pour choisir et battez le serveur en 3 manches</p>
        <p>La pierre (CHI) écrase les ciseaux (MI) et gagne.
            <br>La feuille (FU) enveloppe la pierre (CHI) et gagne.
            <br>Les ciseaux (MI) découpent la feuille (FU) et gagnent.</p>
        <p>A vous de jouer !</p>
        <div>
            <form method="post" action="tentative">
                <button type="submit" class="btn btn-outline-dark btn-lg" name="playerChoice" value="Chi">Chi</button>
                <button type="submit" class="btn btn-outline-dark btn-lg" name="playerChoice" value="Fu">Fu</button>
                <button type="submit" class="btn btn-outline-dark btn-lg" name="playerChoice" value="Mi">Mi</button>
            </form>
        </div>
    </div>
</div>
<footer></footer>
</body>
</html>
