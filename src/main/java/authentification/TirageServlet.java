package authentification;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import authentificationrole.models.DemandeTirageDAO;
import authentificationrole.models.DemandeTirageclass;
import authentificationrole.models.User;
import authentificationrole.models.UserDAO;

import jakarta.servlet.annotation.MultipartConfig;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50,     // 50MB
maxRequestSize = 1024 * 1024 * 100) // 100MB
public class TirageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TirageServlet() {
        super();
        // TODO Auto-generated constructor stub 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
              
        User user = (User) session.getAttribute("authenticatedUser");
        // Ajout de l'utilisateur à la requête
        request.setAttribute("user", user);      
        RequestDispatcher dispatcher = request.getRequestDispatcher("DemandeTirage.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    int idUser = 1;
	    
	    // Récupérer les autres champs du formulaire
	    int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
	    int idGroupe = Integer.parseInt(request.getParameter("idGroupe"));
	    Date dateReception = Date.valueOf(request.getParameter("date_reception"));
	    Date dateEmprunte = Date.valueOf(request.getParameter("date_emprunte"));
	    int nombreCopies = Integer.parseInt(request.getParameter("nombre_copies"));
	    Part pdfPart = request.getPart("document_PDF");
	    InputStream pdfInputStream = pdfPart.getInputStream();

	    // Créer un objet DemandeTirage avec les données fournies
	    DemandeTirageclass demandeTirage = new DemandeTirageclass();
	    demandeTirage.setIdUser(idUser);
	    demandeTirage.setIdMatiere(idMatiere);
	    demandeTirage.setIdGroupe(idGroupe);
	    demandeTirage.setDate_reception(dateReception);
	    demandeTirage.setDate_emprunte(dateEmprunte);
	    demandeTirage.setNombre_copies(nombreCopies);
	    demandeTirage.setDocument_PDF(pdfInputStream);

	    // Instance de DemandeTirageDAO pour effectuer des opérations sur la base de données
	    DemandeTirageDAO demandeTirageDAO = new DemandeTirageDAO();

	    // Ajouter la demande d'impression à la base de données
	    demandeTirageDAO.addDemandeTirage(demandeTirage);
	    String message = "Votre demande d'impression a été envoyée avec succès au agent.";
	    request.setAttribute("message", message);
	    // Rediriger vers la page du tableau de bord de l'enseignant
	    response.sendRedirect("Enseignant_Dashboard.jsp");
	}


}
