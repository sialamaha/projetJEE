package authentification;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import authentificationrole.models.User;
import authentificationrole.models.UserDAO;

public class LoginControlleur extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupérer l'utilisateur authentifié à partir de la session ou de la base de données
	    HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("authenticatedUser");

	    // Vérifier si l'utilisateur est authentifié
	    if (user != null) {
	        // Définir les attributs de session pour le nom d'utilisateur et le rôle
	        session.setAttribute("idUser", user.getIdUser());
	        session.setAttribute("username", user.getUsername());
	        session.setAttribute("email", user.getEmail());
	        session.setAttribute("role", user.getRole());
	    }
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Enseignant_Dashboard.jsp");
	    dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    UserDAO userDAO = new UserDAO();
	    User user = userDAO.authenticate(email, password);
	    
	    if (user != null) {
	        String role = user.getRole();
	        HttpSession session = request.getSession();
	        session.setAttribute("idUser", user.getIdUser());
	        session.setAttribute("username", user.getUsername());
	        session.setAttribute("email", user.getEmail());
	        session.setAttribute("role", user.getRole());
	        if (role != null && !role.isEmpty()) {
	            // Si le rôle est défini
	            if (role.equals("enseignant")) {
	                response.sendRedirect("Enseignant_Dashboard.jsp");
	            } else if (role.equals("administrateur")) {
	                response.sendRedirect("Administration.jsp");
	            } else if (role.equals("agenttirage")) {
	                response.sendRedirect("AgentTirage.jsp");
	            }
	            return; 
	        }
	    }	    
	    
	    String errorMessage = "Nom d'utilisateur ou mot de passe incorrect.";
	    request.setAttribute("errorMessage", errorMessage);
	    request.getRequestDispatcher("Login.jsp").forward(request, response);
	}
}