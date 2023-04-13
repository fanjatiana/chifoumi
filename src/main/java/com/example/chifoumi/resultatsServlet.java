package com.example.chifoumi;

import Service.ComputerRandomService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/resultats")
public class resultatsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/tentative.jsp");
        rd.forward(request, response);
        String btnRestart = request.getParameter("btnRestart");
        if (btnRestart != null && btnRestart.equals("recommencer")) {
            // redirige vers la page/tentative
            response.sendRedirect("tentative");
        }
    }
}


