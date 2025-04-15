package dao;

import entities.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    public void ajouterLivre(Livre livre) throws SQLException {
        String query = "INSERT INTO Livre (Code_Livre, Titre, Auteur, Categorie, Annee_Publication, Quantite_Disponible) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, livre.getCodeLivre());
            statement.setString(2, livre.getTitre());
            statement.setString(3, livre.getAuteur());
            statement.setString(4, livre.getCategorie());
            statement.setInt(5, livre.getAnneePublication());
            statement.setInt(6, livre.getQuantiteDisponible());
            statement.executeUpdate();
        }
    }

    public void modifierLivre(Livre livre) throws SQLException {
        String query = "UPDATE Livre SET Titre = ?, Auteur = ?, Categorie = ?, Annee_Publication = ?, Quantite_Disponible = ? WHERE Code_Livre = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setString(3, livre.getCategorie());
            statement.setInt(4, livre.getAnneePublication());
            statement.setInt(5, livre.getQuantiteDisponible());
            statement.setInt(6, livre.getCodeLivre());
            statement.executeUpdate();
        }
    }

    public void supprimerLivre(int codeLivre) throws SQLException {
        String query = "DELETE FROM Livre WHERE Code_Livre = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, codeLivre);
            statement.executeUpdate();
        }
    }

    public List<Livre> listerLivres() throws SQLException {
        List<Livre> livres = new ArrayList<>();
        String query = "SELECT * FROM Livre";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Livre livre = new Livre(
                    resultSet.getInt("Code_Livre"),
                    resultSet.getString("Titre"),
                    resultSet.getString("Auteur"),
                    resultSet.getString("Categorie"),
                    resultSet.getInt("Annee_Publication"),
                    resultSet.getInt("Quantite_Disponible")
                );
                livres.add(livre);
            }
        }
        return livres;
    }
}