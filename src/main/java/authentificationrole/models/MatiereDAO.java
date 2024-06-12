package authentificationrole.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatiereDAO {
    private Connection connection;

    public MatiereDAO() {
        connection = DBUtil.getConnection();
    }

    public void addMatiere(Matiere matiere, int idEnseignant, List<Integer> groupeIds) {
        try {
            // Insérer la nouvelle matière dans la table matiere
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO matiere (nomMatiere, idEnseignant) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, matiere.getNomMatiere());
            preparedStatement.setInt(2, idEnseignant); // Set the idEnseignant in the SQL statement
            preparedStatement.executeUpdate();

            // Récupérer l'ID de la matière nouvellement créée
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int matiereId = 0;
            if (generatedKeys.next()) {
                matiereId = generatedKeys.getInt(1);
            }

            // Insérer les associations entre la matière et les groupes dans la table matiere_groupe
            preparedStatement = connection.prepareStatement("INSERT INTO matiere_groupe (idMatiere,idGroupe) VALUES (?, ?)");
            for (int groupeId : groupeIds) {
                preparedStatement.setInt(1, matiereId);
                preparedStatement.setInt(2, groupeId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*******************liste matiere****************/
    public List<Matiere> getAllMatieres() {
        List<Matiere> matieres = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM matiere");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Matiere matiere = new Matiere();
                matiere.setIdMatiere(rs.getInt("idMatiere"));
                matiere.setNomMatiere(rs.getString("nomMatiere"));
                matieres.add(matiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matieres;
    }
    public void associerMatiereGroupe(int idMatiere, int idGroupe) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO matiere_groupe (idMatiere,idGroupe) VALUES (?, ?)");
            preparedStatement.setInt(1, idMatiere);
            preparedStatement.setInt(2, idGroupe);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void associerMatiereEnseignant(int idMatiere, int idEnseignant) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO enseignant_matiere (idEnseignant,idMatiere) VALUES (?, ?)");
            
            preparedStatement.setInt(2, idEnseignant);
            preparedStatement.setInt(1, idMatiere);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getNomMatiereById(int matiereId) {
        String nomMatiere = null;
        String query = "SELECT nomMatiere FROM matiere WHERE idMatiere = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, matiereId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nomMatiere = resultSet.getString("nomMatiere");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomMatiere;
    }
    public List<Matiere> getMatieresByEnseignant(int idEnseignant) {
        List<Matiere> matieres = new ArrayList<>();
        try {
            String query = "SELECT * FROM matiere WHERE idEnseignant = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEnseignant);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Matiere matiere = new Matiere();
                matiere.setIdMatiere(rs.getInt("idMatiere"));
                matiere.setNomMatiere(rs.getString("nomMatiere"));
                matieres.add(matiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matieres;
    }

}