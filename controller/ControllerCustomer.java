package controller;

import model.*;
import view.CustomerView;

import javax.swing.*;
import java.sql.*;


public class ControllerCustomer {
    private CustomerModel model;
    private CustomerView view;

    public ControllerCustomer(){}
    public ControllerCustomer(CustomerModel model, CustomerView view) {
        this.model = model;
        this.view = view;
    }

    public void enregister(ModelCustomer customer) {

        enregistrerCustomerDansLaBaseDeDonnees(customer); // Appel à la méthode pour enregistrer la voiture dans la base de données

    }



    private void enregistrerCustomerDansLaBaseDeDonnees(ModelCustomer customer) {

        try (Connection conn = DriverManager.getConnection(Model.DB_URL, Model.USER, Model.PASS)) {
            String query = "INSERT INTO customer (Customer_FirstName, Customer_LastName, Customer_Licence, Customer_Type, Customer_Address, Customer_Phone, Customer_password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3,customer.getLicence());
            statement.setInt(4, customer.getType());
            statement.setString(5, customer.getAddress());
            statement.setInt(6, customer.getPhone());
            statement.setInt(7, customer.getPassword());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Voiture enregistrée avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du client.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur SQL: " + ex.getMessage());
        }

    }
}