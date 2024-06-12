package authentificationrole.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import authentificationrole.models.Groupe;
import authentificationrole.models.MatiereGroupe;

public class MatiereGroupeDAO {

 	   private Connection connection;

 	    public MatiereGroupeDAO() {
 	        connection = DBUtil.getConnection();
 	    }   
    public List<Groupe> getGroupesByMatiere(int idMatiere) {
        List<Groupe> groupes = new ArrayList<>();

        String query = "SELECT g.* FROM groupe g " +
                       "JOIN matiere_groupe mg ON g.idGroupe = mg.idGroupe " +
                       "WHERE mg.idMatiere = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {            
            preparedStatement.setInt(1, idMatiere);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {               
                while (resultSet.next()) {
                    Groupe groupe = new Groupe();
                    groupe.setIdGroupe(resultSet.getInt("idGroupe"));
                    groupe.setNomGroupe(resultSet.getString("nomGroupe"));                  
                    groupes.add(groupe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupes;
    }
    public void updateMatiereGroupe(int idMatiere, int idGroupe) {
        try {
          
            String query = "UPDATE matiere_groupe SET idGroupe = ? WHERE idMatiere = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);                  
            preparedStatement.setInt(1, idGroupe);
            preparedStatement.setInt(2, idMatiere);           
            // Exécutez la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteMatiereFromGroupe(int idMatiere, int idGroupe) {
        try {
            String query = "DELETE FROM matiere_groupe WHERE idMatiere = ? AND idGroupe = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMatiere);
            preparedStatement.setInt(2, idGroupe);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
