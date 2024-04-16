package Controller;

import Model.*;
import View.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class ControllerVoiture {
    private ViewFormVoiture view;

    public ControllerVoiture(ViewFormVoiture view) {
        this.view = view;
        this.view.setEnregistrerListener(new EnregistrerListener());
    }

    class EnregistrerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ModelVoiture voiture = view.getVoitureFromInput();
            // Appel à la méthode pour enregistrer la voiture dans la base de données
            enregistrerVoitureDansLaBaseDeDonnees(voiture);
        }
    }

    private void enregistrerVoitureDansLaBaseDeDonnees(ModelVoiture voiture) {
        //String url = "jdbc:mysql://localhost:3306/ma_base_de_donnees";
        //String username = "mon_utilisateur";
        //String password = "mon_mot_de_passe";

        try (Connection conn = DriverManager.getConnection(Model.DB_URL, Model.USER, Model.PASS)) {
            String query = "INSERT INTO voiture (marque, model, date_achat, category, prix, nombre_siege, type_transmission, specification, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, voiture.getMarque());
            statement.setString(2, voiture.getModele());
            statement.setDate(3, new java.sql.Date(voiture.getDateAchat().getTime()));
            statement.setString(4, voiture.getCategory());
            statement.setInt(5, voiture.getPrix());
            statement.setInt(6, voiture.getNombreSiege());
            statement.setString(7, voiture.getTypeTransmission());
            statement.setString(8, voiture.getSpecification());
            statement.setString(9, voiture.getDescription());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Voiture enregistrée avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de la voiture.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur SQL: " + ex.getMessage());
        }
    }
}
