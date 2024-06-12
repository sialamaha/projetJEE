package authentification;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import authentificationrole.models.Administrateur;
import authentificationrole.models.AgentTirage;
import authentificationrole.models.DBUtil;
import authentificationrole.models.Enseignant;
import authentificationrole.models.Matiere;
import authentificationrole.models.MatiereDAO;
import authentificationrole.models.User;
import authentificationrole.models.UserDAO;

public class CreateCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UserDAO userDAO;

	    public CreateCompte() {
	        super();
	        // Initialize UserDAO in the constructor
	        userDAO = new UserDAO();
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer l'utilisateur authentifié à partir de la session
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("authenticatedUser");
	        // Récupérer la liste des enseignants
	        UserDAO userDAO = new UserDAO();
	        List<User> users = userDAO.getAllUsers();
	        request.setAttribute("users", users);
	        // Transmettre l'utilisateur authentifié à la JSP
	        request.setAttribute("user", user);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("adminsetting.jsp");
	        dispatcher.forward(request, response);
	    }
	    public static List<User> getUsers(HttpServletRequest request) {
	    	UserDAO userDAO = new UserDAO();
	        return userDAO.getAllUsers();
	    }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String adresse = request.getParameter("adresse");
	        String email = request.getParameter("email");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String role = request.getParameter("role");
	        String specialite = request.getParameter("specialite");
	        String zone = request.getParameter("zone");
	        String permission = request.getParameter("permission");

	        try (Connection connection = DBUtil.getConnection()) {
	            User user = null;
	            if (role.equals("enseignant")) {
	                Enseignant enseignant = new Enseignant();
	                enseignant.setNom(nom);
	                enseignant.setPrenom(prenom);
	                enseignant.setAdresse(adresse);
	                enseignant.setEmail(email);
	                enseignant.setUsername(username);
	                enseignant.setPassword(password);
	                enseignant.setRole(role);
	                enseignant.setSpecialite(specialite);
	                user = enseignant;	                
	            } else if (role.equals("agenttirage")) {
	                AgentTirage agentTirage = new AgentTirage();
	                agentTirage.setNom(nom);
	                agentTirage.setPrenom(prenom);
	                agentTirage.setAdresse(adresse);
	                agentTirage.setEmail(email);
	                agentTirage.setUsername(username);
	                agentTirage.setPassword(password);
	                agentTirage.setRole(role);
	                agentTirage.setZone(zone);
	                user = agentTirage;	               
	            } else if (role.equals("administrateur")) {
	                Administrateur administrateur = new Administrateur();
	                administrateur.setNom(nom);
	                administrateur.setPrenom(prenom);
	                administrateur.setAdresse(adresse);
	                administrateur.setEmail(email);
	                administrateur.setUsername(username);
	                administrateur.setPassword(password);
	                administrateur.setRole(role);
	                administrateur.setPermission(permission);
	                user = administrateur;	                
	            }
	            UserDAO userDAO = new UserDAO();
	            userDAO.addUser(connection, user);	         
	         if (role.equals("enseignant")) {
	        	 response.sendRedirect("Enseignant_Dashboard.jsp");
	         } else if (role.equals("agenttirage")) {
	        	 response.sendRedirect("AgentTirage.jsp");
	         } else if (role.equals("administrateur")) {
	             response.sendRedirect("Administration.jsp");
	         } else {
	             // Redirection par défaut vers une page générale
	             response.sendRedirect("Login.jsp");
	         }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.sendRedirect("error.jsp");
	        }
	    }
	}