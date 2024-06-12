package authentification;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import authentificationrole.models.MatiereGroupeDAO;

/**
 * Servlet implementation class ModifierMatiereGroupeServlet
 */
public class ModifierMatiereGroupeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MatiereGroupeDAO matiereGroupeDAO;   
    public ModifierMatiereGroupeServlet() {
        super();
        matiereGroupeDAO = new MatiereGroupeDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de la requête
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));      
        
     // Définir les attributs pour la page JSP
        request.setAttribute("idMatiere", idMatiere);
        request.setAttribute("idGroupe", idGroupe);

        // Redirection vers la page JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("ModificationMatiereGroupe.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
        
        MatiereGroupeDAO matiereGroupeDAO = new MatiereGroupeDAO();
     
        matiereGroupeDAO.updateMatiereGroupe(idMatiere, idGroupe);

        // Rediriger vers une page de confirmation ou une autre page appropriée
        response.sendRedirect("ListeMatiereGroupe.jsp");
    }
}