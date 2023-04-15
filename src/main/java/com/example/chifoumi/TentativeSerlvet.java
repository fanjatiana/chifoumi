package com.example.chifoumi;

import Service.ServerRandomService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/tentative"})
public class TentativeSerlvet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/tentative.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // choix du joueur
        String choice = request.getParameter("playerChoice");
        request.setAttribute("playerChoice", choice);

        // choix de l'ordinateur
        String serverChoice = new ServerRandomService().play();
        request.setAttribute("serverChoice", serverChoice);

        // on affiche le gagnant
        String gameResult;
        if (choice.equals(serverChoice)) {
            gameResult = "Egalité";
        } else if (choice.equals("Chi") && serverChoice.equals("Mi")
                || choice.equals("Fu") && serverChoice.equals("Chi")
                || choice.equals("Mi") && serverChoice.equals("Fu")) {
            gameResult = "Bravo vous avez gagné !!";
        } else {
            gameResult = "Le serveur a gagné !";
        }
        request.setAttribute("gameResult", gameResult);

        // recuperation de la session utilisateur
        HttpSession session = request.getSession(true);

        // création d'une liste de sessions vide
        List<String> gameSessions = new ArrayList<>();
        if (session.getAttribute("games") != null) {
            gameSessions = (List<String>) session.getAttribute("games");
        }

        // on ajoute les résultats des 3 sessions
        if (gameSessions.size() < 3) {
            gameSessions.add(gameResult);
        } else {
            session.invalidate();
        }

        // on récupère le nombre de victoire et des égalités avec des compteurs
        int nbPlayerWon = 0;
        int nbServerWon = 0;
        int nbEgality = 0;
        for (String s : gameSessions) {
            if (s.equals("Bravo vous avez gagné !!")) {
                nbPlayerWon++;
            }
            if (s.equals("Le serveur a gagné !")) {
                nbServerWon++;
            }

            if (s.equals("Egalité")) {
                nbEgality++;
            }
        }

        // on sauvegarde les données de la session
        session.setAttribute("games", gameSessions);

        request.setAttribute("playerSessionReport", nbPlayerWon);
        request.setAttribute("serverSessionReport", nbServerWon);
        request.setAttribute("egalitySessionReport", nbEgality);

        // affichage du vainqueur final + on vide le tableau + on détruit la session
        String finalResult;
        if (gameSessions.size() == 3 && nbServerWon > nbPlayerWon && nbServerWon > nbEgality) {
            finalResult = "Le serveur remporte la partie !";
            gameSessions.clear();
            session.invalidate();
        } else if (gameSessions.size() == 3 && nbPlayerWon > nbServerWon && nbPlayerWon > nbEgality) {
            finalResult = "Vous avez gagné la partie !";
            gameSessions.clear();
            session.invalidate();
        } else if (gameSessions.size() == 3 && nbEgality > nbServerWon && nbEgality > nbPlayerWon && nbServerWon > nbPlayerWon) {
            finalResult = "Le serveur remporte la partie !";
            gameSessions.clear();
            session.invalidate();
        } else if (gameSessions.size() == 3 && nbEgality > nbServerWon && nbEgality > nbPlayerWon && nbPlayerWon > nbServerWon) {
            finalResult = "Vous avez gagné la partie !";
            gameSessions.clear();
            session.invalidate();
        }else if (gameSessions.size() == 3 && nbEgality == 3) {
            finalResult = "Egalité ! beau match, bravo !";
            gameSessions.clear();
            session.invalidate();
        }else if (gameSessions.size() == 3 && nbEgality == nbPlayerWon && nbEgality == nbServerWon) {
            finalResult = "Egalité ! beau match, bravo !";
            gameSessions.clear();
            session.invalidate();
        } else {
            finalResult = "";
        }
        request.setAttribute("finalResult", finalResult);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/resultats.jsp");
        rd.forward(request, response);
    }
}



