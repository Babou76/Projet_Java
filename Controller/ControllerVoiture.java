package controller;


import model.*;
import view.CarView;

import javax.swing.*;
import java.sql.*;


public class ControllerVoiture {
    private CarModel model;
    private CarView view;

    public ControllerVoiture(){}
    public ControllerVoiture(CarModel model, CarView view) {
        this.model = model;
        this.view = view;
    }

    public void enregister(ModelVoiture voiture) {

        enregistrerVoitureDansLaBaseDeDonnees(voiture); // Appel à la méthode pour enregistrer la voiture dans la base de données

    }



    private void enregistrerVoitureDansLaBaseDeDonnees(ModelVoiture voiture) {

        try (Connection conn = DriverManager.getConnection(Model.DB_URL, Model.USER, Model.PASS)) {
            String query = "INSERT INTO car (Car_brand, Car_name, Car_Date, Car_category, Car_PricePerday, Car_NbSeat, Car_transmission, Car_specification, Car_Description) " +
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
