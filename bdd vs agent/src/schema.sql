-- Création de la table Etudiant
CREATE TABLE Etudiant (
    Num_Etudiant NUMBER PRIMARY KEY,
    Nom VARCHAR2(50) NOT NULL,
    Prenom VARCHAR2(50) NOT NULL,
    Email VARCHAR2(100) UNIQUE NOT NULL,
    Telephone VARCHAR2(15)
);

-- Création de la table Livre
CREATE TABLE Livre (
    Code_Livre NUMBER PRIMARY KEY,
    Titre VARCHAR2(100) NOT NULL,
    Auteur VARCHAR2(100) NOT NULL,
    Categorie VARCHAR2(50),
    Annee_Publication NUMBER,
    Quantite_Disponible NUMBER NOT NULL
);

-- Création de la table Emprunt
CREATE TABLE Emprunt (
    Num_Emprunt NUMBER PRIMARY KEY,
    Num_Etudiant NUMBER REFERENCES Etudiant(Num_Etudiant),
    Code_Livre NUMBER REFERENCES Livre(Code_Livre),
    Date_Emprunt DATE NOT NULL,
    Date_Retour_Prevue DATE NOT NULL,
    Date_Retour_Effective DATE,
    Statut VARCHAR2(20) CHECK (Statut IN ('En cours', 'Rendu', 'En retard'))
);