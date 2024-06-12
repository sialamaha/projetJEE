package authentification;

import java.io.IOException;
import java.util.List;
import authentificationrole.models.Groupe;
import authentificationrole.models.GroupeDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GroupeServlet extends HttpServlet {
    private GroupeDAO groupeDAO;

    public void init() {
        groupeDAO = new GroupeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Groupe> groupes = groupeDAO.getAllGroupes();
        request.setAttribute("groupes", groupes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AddGroupe.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomGroupe = request.getParameter("nomGroupe");

        Groupe groupe = new Groupe();
        groupe.setNomGroupe(nomGroupe);

        groupeDAO.addGroupe(groupe);

        response.sendRedirect("Administration.jsp");
    }
}