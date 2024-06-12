package authentification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LogoutControlleur
 */
public class LogoutControlleur extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Supprimer toutes les informations d'identification de la session
        request.getSession().invalidate();

        // Rediriger vers la page de connexion
        response.sendRedirect("Login.jsp");
    }
}