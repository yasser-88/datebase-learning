import dao.DatabaseConnection;
import dao.EmpruntDAO;
import dao.EtudiantDAO;
import dao.LivreDAO;
import entities.Emprunt;
import entities.Etudiant;
import entities.Livre;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        // Test de la connexion à la base de données
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connexion réussie à la base de données Oracle !");
            // Use the connection variable to avoid warnings
            if (connection != null) {
                System.out.println("La connexion est active.");
            }
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données : " + e.getMessage());
            return;
        }

        // Test des opérations CRUD sur les étudiants
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        try {
            // Ajouter un étudiant
            Etudiant etudiant = new Etudiant(1, "Dupont", "Jean", "jean.dupont@example.com", "0123456789");
            etudiantDAO.ajouterEtudiant(etudiant);
            System.out.println("Étudiant ajouté avec succès.");

            // Lister les étudiants
            List<Etudiant> etudiants = etudiantDAO.listerEtudiants();
            System.out.println("Liste des étudiants :");
            for (Etudiant e : etudiants) {
                System.out.println(e.getNumEtudiant() + " - " + e.getNom() + " " + e.getPrenom());
            }

            // Modifier un étudiant
            etudiant.setNom("Durand");
            etudiantDAO.modifierEtudiant(etudiant);
            System.out.println("Étudiant modifié avec succès.");

            // Supprimer un étudiant
            etudiantDAO.supprimerEtudiant(1);
            System.out.println("Étudiant supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors des opérations CRUD sur les étudiants : " + e.getMessage());
        }

        // Test des opérations CRUD sur les livres
        LivreDAO livreDAO = new LivreDAO();
        try {
            // Ajouter un livre
            Livre livre = new Livre(1, "Java Programming", "John Doe", "Programming", 2020, 10);
            livreDAO.ajouterLivre(livre);
            System.out.println("Livre ajouté avec succès.");

            // Lister les livres
            List<Livre> livres = livreDAO.listerLivres();
            System.out.println("Liste des livres :");
            for (Livre l : livres) {
                System.out.println(l.getCodeLivre() + " - " + l.getTitre() + " par " + l.getAuteur());
            }

            // Modifier un livre
            livre.setTitre("Advanced Java Programming");
            livreDAO.modifierLivre(livre);
            System.out.println("Livre modifié avec succès.");

            // Supprimer un livre
            livreDAO.supprimerLivre(1);
            System.out.println("Livre supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors des opérations CRUD sur les livres : " + e.getMessage());
        }

        // Test des opérations CRUD sur les emprunts
        EmpruntDAO empruntDAO = new EmpruntDAO();
        try {
            // Ajouter un emprunt
            Emprunt emprunt = new Emprunt(1, 1, 1, new Date(), new Date(), null, "En cours");
            empruntDAO.ajouterEmprunt(emprunt);
            System.out.println("Emprunt ajouté avec succès.");

            // Lister les emprunts
            List<Emprunt> emprunts = empruntDAO.listerEmprunts();
            System.out.println("Liste des emprunts :");
            for (Emprunt emp : emprunts) {
                System.out.println(emp.getNumEmprunt() + " - Livre: " + emp.getCodeLivre() + ", Étudiant: " + emp.getNumEtudiant());
            }

            // Modifier un emprunt
            emprunt.setStatut("Rendu");
            empruntDAO.modifierEmprunt(emprunt);
            System.out.println("Emprunt modifié avec succès.");

            // Supprimer un emprunt
            empruntDAO.supprimerEmprunt(1);
            System.out.println("Emprunt supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors des opérations CRUD sur les emprunts : " + e.getMessage());
        }
    }
}