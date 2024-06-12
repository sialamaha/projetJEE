package authentification;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import authentificationrole.models.Matiere;
import authentificationrole.models.MatiereDAO;
import authentificationrole.models.User;
import authentificationrole.models.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EnseignantControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UserDAO userDAO;
	    public EnseignantControlleur() {
	        super();
	        // Initialize UserDAO in the constructor
	        userDAO = new UserDAO();
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Récupérer l'utilisateur authentifié à partir de la session
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("authenticatedUser");

	        // Récupérer la liste des matières
	        List<Matiere> matieres = getMatieresenseignants(request, user.getIdUser());

	        // Récupérer la liste des enseignants
	        List<User> enseignants = userDAO.getEnseignants();
	        request.setAttribute("enseignants", enseignants);
	        request.setAttribute("user", user);
	        request.setAttribute("matieres", matieres); // Ajouter les matières comme attribut de la requête

	        RequestDispatcher dispatcher = request.getRequestDispatcher("Enseignant_Dashboard.jsp");
	        dispatcher.forward(request, response);
	    }

	    public static List<Matiere> getMatieresenseignants(HttpServletRequest request, int idEnseignant) {
	        MatiereDAO matiereDAO = new MatiereDAO();
	        return matiereDAO.getMatieresByEnseignant(idEnseignant);
	    }
	    public static List<Matiere> getMatieres(HttpServletRequest request) {
	        MatiereDAO matiereDAO = new MatiereDAO();
	        return matiereDAO.getAllMatieres();
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
