package com.example.chifoumi;

import Service.ComputerRandomService;
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
        String computerchoice = new ComputerRandomService().play();
        request.setAttribute("resultComputerChoice", computerchoice);

        // resultats
        String resultat;
        if (choice.equals(computerchoice)) {
            resultat = "Egalité";
        } else if (choice.equals("Chi") && computerchoice.equals("Mi")
                || choice.equals("Fu") && computerchoice.equals("Chi")
                || choice.equals("Mi") && computerchoice.equals("Fu")) {
            resultat = "L’utilisateur a gagner";
        } else {
            resultat = "Le serveur a gagner";
        }
        request.setAttribute("result", resultat);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/resultats.jsp");
        rd.forward(request, response);
    }
}
