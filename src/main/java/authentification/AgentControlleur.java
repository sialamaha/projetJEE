package authentification;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import authentificationrole.models.DemandeTirageDAO;
import authentificationrole.models.DemandeTirageclass;

@WebServlet("/agent")
public class AgentControlleur extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private DemandeTirageDAO demandeTirageDAO;
    
    public AgentControlleur() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenir la liste des demandes depuis la base de données
        List<DemandeTirageclass> demandes = getDemandes();
        
        request.setAttribute("nombreDemandes", demandes.size());
        
        request.setAttribute("demandes", demandes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("agenttirage.jsp");
        dispatcher.forward(request, response);
    }    
    public List<DemandeTirageclass> getDemandes() {   
        // Créer une instance de DemandeTirageDAO
        DemandeTirageDAO demandeTirageDAO = new DemandeTirageDAO();
          return demandeTirageDAO.getAllDemandes();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Appeler la méthode doGet pour traiter les requêtes POST de la même manière que les requêtes GET
        doGet(request, response);
    }
}
