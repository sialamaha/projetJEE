package authentificationrole.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import authentificationrole.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DemandeTirageDAO {
    private Connection connection;

    public DemandeTirageDAO() {
        connection = DBUtil.getConnection();
    }

    public void addDemandeTirage(DemandeTirageclass demandeTirage) {
        /******************************************/
        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("INSERT INTO demandetirage (id_enseignant, id_matiere, id_groupe, date_reception, date_emprunte, nombre_copies, document_PDF) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, 23);
            preparedStatement.setInt(2, demandeTirage.getIdMatiere());
            preparedStatement.setInt(3, demandeTirage.getIdGroupe());
            preparedStatement.setDate(4, demandeTirage.getDate_reception());
            preparedStatement.setDate(5, demandeTirage.getDate_emprunte());
            preparedStatement.setInt(6, demandeTirage.getNombre_copies());
            preparedStatement.setBlob(7, demandeTirage.getDocument_PDF());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /******************/
    public List<DemandeTirageclass> getAllDemandes() {
        List<DemandeTirageclass> demandes = new ArrayList<>();
        String query = "SELECT * FROM demandetirage";

        // Instanciation des DAO nécessaires
        UserDAO userDAO = new UserDAO(); // Make sure this UserDAO has the correct method to fetch user details
        MatiereDAO matiereDAO = new MatiereDAO(); // Make sure this is implemented
        GroupeDAO groupeDAO = new GroupeDAO(); 
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DemandeTirageclass demande = new DemandeTirageclass();
                demande.setIdUser(23);
                
                // Récupérer le nom de l'utilisateur en fonction de l'ID utilisateur
                String nomUtilisateur = userDAO.getUserNameById(23);
                demande.setNom(nomUtilisateur);
                
                // Récupérer le nom de la matière en fonction de l'ID matière
                String nomMatiere = matiereDAO.getNomMatiereById(resultSet.getInt("id_matiere"));
                demande.setNomMatiere(nomMatiere);
                
                // Récupérer le nom de la groupe fonction de l'ID groupe
                String nomGroupe = groupeDAO.getNomGroupeById(resultSet.getInt("id_groupe"));
                demande.setNomGroupe(nomGroupe);
                
                demande.setDate_reception(resultSet.getDate("date_reception"));
                demande.setDate_emprunte(resultSet.getDate("date_emprunte"));
                demande.setNombre_copies(resultSet.getInt("nombre_copies"));
                demande.setDocument_PDF(resultSet.getBlob("document_PDF").getBinaryStream());
                // Ajouter la demande à la liste
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }

}
