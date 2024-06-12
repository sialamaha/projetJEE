package authentificationrole.models;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.util.logging.Logger;

public class UserDAO {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDAO() {
        connection = DBUtil.getConnection();
    }

    public User authenticate(String email, String password) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdresse(rs.getString("adresse"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
 
    public void addUser(Connection connection, User user) {
        try {
            // Insérer l'utilisateur dans la table user
            PreparedStatement preparedStatement = 
            		connection.prepareStatement("insert into user(nom, prenom,adresse, email,username, password, role) "
                    + "values (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getAdresse());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.executeUpdate();

            // Récupérer l'ID de l'utilisateur nouvellement inséré
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }

            // Insérer les informations spécifiques en fonction du rôle
            if (user instanceof Enseignant) {
                Enseignant enseignant = (Enseignant) user;
                preparedStatement = connection.prepareStatement("insert into enseignant(idUser, specialite) values (?, ?)");
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, enseignant.getSpecialite());
                preparedStatement.executeUpdate();
            } else if (user instanceof AgentTirage) {
                AgentTirage agentTirage = (AgentTirage) user;
                preparedStatement = connection.prepareStatement("insert into agenttirage(idUser, zone) values (?, ?)");
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, agentTirage.getZone());
                preparedStatement.executeUpdate();
            } else if (user instanceof Administrateur) {
                Administrateur administrateur = (Administrateur) user;
                preparedStatement = connection.prepareStatement("insert into administrateur(idUser, permission) values (?, ?)");
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, administrateur.getPermission());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getEnseignants() {
        List<User> enseignants = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idUser, nom, prenom FROM user WHERE role='enseignant'");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User enseignant = new User();
                enseignant.setIdUser(rs.getInt("idUser"));
                enseignant.setNom(rs.getString("nom"));
                enseignant.setPrenom(rs.getString("prenom"));
                enseignants.add(enseignant);
                logger.info("Enseignant ajouté : " + enseignant.getNom() + " " + enseignant.getPrenom());
            }
            logger.info("Nombre total d'enseignants récupérés : " + enseignants.size());
        } catch (SQLException e) {
           // logger.error("Erreur lors de la récupération des enseignants : ", e);
        }
        return enseignants;
    }
    /************/
    public String getUserNameById(int userId) {
        String userName = null;
        String query = "SELECT nom FROM user WHERE idUser = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                userName = resultSet.getString("nom");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }
/*********************/
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idUser, nom, prenom, role FROM user");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setRole(rs.getString("role"));
                users.add(user);
                logger.info("Utilisateur ajouté : " + user.getNom() + " " + user.getPrenom());
            }
            logger.info("Nombre total d'utilisateurs récupérés : " + users.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public void updateUser(User user) {
        try {
            // Préparer la requête SQL pour mettre à jour l'utilisateur
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET nom=?, prenom=?, adresse=?, email=?, username=?, password=?, role=? WHERE idUser=?");

            // Remplacer les paramètres de la requête par les valeurs de l'utilisateur
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getAdresse());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setInt(8, user.getIdUser());

            // Exécuter la mise à jour
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUserById(int userId) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE idUser = ?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdresse(rs.getString("adresse"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
/*********************/
    public void deleteUserById(int IdUser) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE IdUser = ?");
            preparedStatement.setInt(1, IdUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
