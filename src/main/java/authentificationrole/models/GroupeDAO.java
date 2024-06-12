package authentificationrole.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO {
    private Connection connection;

    public GroupeDAO() {
        connection = DBUtil.getConnection();
    }

    public List<Groupe> getAllGroupes() {
        List<Groupe> groupes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM groupe");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Groupe groupe = new Groupe();
                groupe.setIdGroupe(rs.getInt("idGroupe"));
                groupe.setNomGroupe(rs.getString("nomGroupe"));
                groupes.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupes;
    }

    public void addGroupe(Groupe groupe) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO groupe (nomGroupe) VALUES (?)");
            preparedStatement.setString(1, groupe.getNomGroupe());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getNomGroupeById(int IdGroupe) {
        String nomGroupe = null;
        String query = "SELECT nomGroupe FROM groupe WHERE idGroupe = ?"; // Correction de la requête SQL
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, IdGroupe);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nomGroupe = resultSet.getString("nomGroupe"); // Utilisation de "nom" au lieu de "nomGroupe"
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomGroupe;
    }

}