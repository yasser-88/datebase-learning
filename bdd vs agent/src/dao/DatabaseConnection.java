package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Ensure the URL is set for local Oracle installation
    private static final String USER = "university"; // Nom d'utilisateur Oracle mis à jour
    private static final String PASSWORD = "university"; // Mot de passe Oracle mis à jour

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur : " + e.getMessage());
            }
        }
    }
}