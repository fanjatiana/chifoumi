package com.example.chifoumi;

import Service.ServerRandomService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/resultats.jsp");
        rd.forward(request, response);
    }
}
