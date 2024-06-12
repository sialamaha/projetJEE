package authentificationrole.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	 private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/gestiontirage";
	    private static final String USER = "root";
	    private static final String PASS = ""; 

	    public static Connection getConnection() {
	        Connection conn = null;
	        try {
	            Class.forName(JDBC_DRIVER);
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
}