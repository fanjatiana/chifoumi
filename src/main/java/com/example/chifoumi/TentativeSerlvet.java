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
        request.setAttribute("resultPlayerChoice", choice);

        // choix de l'ordinateur
        String computerchoice = new ServerRandomService().play();
        request.setAttribute("resultComputerChoice", computerchoice);

        // resultats
        String resultat;
        if (choice.equals(computerchoice)) {
            resultat = "Egalité";
        } else if (choice.equals("Chi") && computerchoice.equals("Mi")
                || choice.equals("Fu") && computerchoice.equals("Chi")
                || choice.equals("Mi") && computerchoice.equals("Fu")) {
            resultat = "Bravo vous avez gagné !!";
        } else {
            resultat = "Le serveur a gagné !";
        }
        request.setAttribute("result", resultat);

        // recuperation de la session utilisateur
        HttpSession session = request.getSession(true);

        // création d'une liste de sessions vide
        List<String> gameSessions = new ArrayList<>();

        if (session.getAttribute("users") != null) {
            gameSessions = (List<String>) session.getAttribute("users");
        }

        // limiter à 3 sessions gagnantes
        if (gameSessions.size() < 3) {
            gameSessions.add(resultat);
            System.out.println("resultat du jeu " + resultat);

        } else {
            session.invalidate();
        }

        System.out.println("nombre session" + gameSessions.size());

        // historique des résultats sur 3 sessions gagnantes
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

        session.setAttribute("users", gameSessions);
        request.setAttribute("playerSessionReport", nbPlayerWon);
        request.setAttribute("serverSessionReport", nbServerWon);
        request.setAttribute("egalitySessionReport", nbEgality);

        session.setAttribute("users", gameSessions);

        String finalResult;
        if (gameSessions.size() == 3 && nbServerWon > nbPlayerWon && nbServerWon > nbEgality) {
            finalResult = "Le serveur est le vaiqueur de la partie !";
            gameSessions.clear();
            session.invalidate();
        } else if (gameSessions.size() == 3 && nbPlayerWon > nbServerWon && nbPlayerWon > nbEgality) {
            finalResult = "Vous avez gagné la partie !";
            gameSessions.clear();
            session.invalidate();
        } else if (gameSessions.size() == 3 && nbEgality > nbServerWon && nbEgality > nbPlayerWon && nbServerWon > nbPlayerWon) {
            finalResult = "Le serveur est le vaiqueur de la partie !";
            gameSessions.clear();
            session.invalidate();
        } else if (gameSessions.size() == 3 && nbEgality > nbServerWon && nbEgality > nbPlayerWon && nbPlayerWon > nbServerWon) {
            finalResult = "Vous avez gagné la partie !";
            gameSessions.clear();
            session.invalidate();
        } else {
            finalResult = "";
        }
        request.setAttribute("finalSessionResult", finalResult);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/resultats.jsp");
        rd.forward(request, response);
    }
}



