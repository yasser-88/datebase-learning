package dao;

import entities.Etudiant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    public void ajouterEtudiant(Etudiant etudiant) throws SQLException {
        String query = "INSERT INTO Etudiant (Num_Etudiant, Nom, Prenom, Email, Telephone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, etudiant.getNumEtudiant());
            statement.setString(2, etudiant.getNom());
            statement.setString(3, etudiant.getPrenom());
            statement.setString(4, etudiant.getEmail());
            statement.setString(5, etudiant.getTelephone());
            statement.executeUpdate();
        }
    }

    public void modifierEtudiant(Etudiant etudiant) throws SQLException {
        String query = "UPDATE Etudiant SET Nom = ?, Prenom = ?, Email = ?, Telephone = ? WHERE Num_Etudiant = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getEmail());
            statement.setString(4, etudiant.getTelephone());
            statement.setInt(5, etudiant.getNumEtudiant());
            statement.executeUpdate();
        }
    }

    public void supprimerEtudiant(int numEtudiant) throws SQLException {
        String query = "DELETE FROM Etudiant WHERE Num_Etudiant = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numEtudiant);
            statement.executeUpdate();
        }
    }

    public List<Etudiant> listerEtudiants() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM Etudiant";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant(
                    resultSet.getInt("Num_Etudiant"),
                    resultSet.getString("Nom"),
                    resultSet.getString("Prenom"),
                    resultSet.getString("Email"),
                    resultSet.getString("Telephone")
                );
                etudiants.add(etudiant);
            }
        }
        return etudiants;
    }
}