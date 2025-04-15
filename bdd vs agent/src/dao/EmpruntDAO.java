package dao;

import entities.Emprunt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    public void ajouterEmprunt(Emprunt emprunt) throws SQLException {
        String query = "INSERT INTO Emprunt (Num_Emprunt, Num_Etudiant, Code_Livre, Date_Emprunt, Date_Retour_Prevue, Date_Retour_Effective, Statut) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, emprunt.getNumEmprunt());
            statement.setInt(2, emprunt.getNumEtudiant());
            statement.setInt(3, emprunt.getCodeLivre());
            statement.setDate(4, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            statement.setDate(5, new java.sql.Date(emprunt.getDateRetourPrevue().getTime()));
            if (emprunt.getDateRetourEffective() != null) {
                statement.setDate(6, new java.sql.Date(emprunt.getDateRetourEffective().getTime()));
            } else {
                statement.setNull(6, Types.DATE);
            }
            statement.setString(7, emprunt.getStatut());
            statement.executeUpdate();
        }
    }

    public void modifierEmprunt(Emprunt emprunt) throws SQLException {
        String query = "UPDATE Emprunt SET Num_Etudiant = ?, Code_Livre = ?, Date_Emprunt = ?, Date_Retour_Prevue = ?, Date_Retour_Effective = ?, Statut = ? WHERE Num_Emprunt = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, emprunt.getNumEtudiant());
            statement.setInt(2, emprunt.getCodeLivre());
            statement.setDate(3, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            statement.setDate(4, new java.sql.Date(emprunt.getDateRetourPrevue().getTime()));
            if (emprunt.getDateRetourEffective() != null) {
                statement.setDate(5, new java.sql.Date(emprunt.getDateRetourEffective().getTime()));
            } else {
                statement.setNull(5, Types.DATE);
            }
            statement.setString(6, emprunt.getStatut());
            statement.setInt(7, emprunt.getNumEmprunt());
            statement.executeUpdate();
        }
    }

    public void supprimerEmprunt(int numEmprunt) throws SQLException {
        String query = "DELETE FROM Emprunt WHERE Num_Emprunt = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, numEmprunt);
            statement.executeUpdate();
        }
    }

    public List<Emprunt> listerEmprunts() throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM Emprunt";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Emprunt emprunt = new Emprunt(
                    resultSet.getInt("Num_Emprunt"),
                    resultSet.getInt("Num_Etudiant"),
                    resultSet.getInt("Code_Livre"),
                    resultSet.getDate("Date_Emprunt"),
                    resultSet.getDate("Date_Retour_Prevue"),
                    resultSet.getDate("Date_Retour_Effective"),
                    resultSet.getString("Statut")
                );
                emprunts.add(emprunt);
            }
        }
        return emprunts;
    }
}