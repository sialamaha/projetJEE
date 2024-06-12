package authentification;
import java.io.IOException;
import authentificationrole.models.User;
import authentificationrole.models.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifierUtilisateurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifierUtilisateurServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'utilisateur à partir des paramètres de la requête
        int userId = Integer.parseInt(request.getParameter("idUser"));

        // Rediriger vers la page de modification de l'utilisateur avec l'ID de l'utilisateur
        response.sendRedirect("ModificationUser.jsp?idUser=" + userId);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int userId = Integer.parseInt(request.getParameter("idUser"));
         String nom = request.getParameter("nom");
         String prenom = request.getParameter("prenom");
         String adresse = request.getParameter("adresse");
         String email = request.getParameter("email");
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         String role = request.getParameter("role");

         // Créer un objet User avec les nouvelles informations
         User user = new User();
         user.setIdUser(userId);
         user.setNom(nom);
         user.setPrenom(prenom);
         user.setAdresse(adresse);
         user.setEmail(email);
         user.setUsername(username);
         user.setPassword(password);
         user.setRole(role);

         // Mettre à jour l'utilisateur dans la base de données
         UserDAO userDAO = new UserDAO();
         userDAO.updateUser(user);

         // Rediriger vers la page de liste des utilisateurs
         response.sendRedirect("adminsetting.jsp");
     }
}
