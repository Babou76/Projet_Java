package model;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    private Connection connection;

    public CustomerModel() {
        try {
            // Création de la connexion à la base de données SQLite
            this.connection = DriverManager.getConnection(Model.DB_URL, Model.USER, Model.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("Customer_FirstName"),
                        rs.getString("Customer_LastName"),
                        rs.getString("Customer_Licence"),
                        rs.getInt("Customer_Type"),
                        rs.getString("Customer_Address"),
                        rs.getString("Customer_Phone"),
                        rs.getString("Customer_password")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public void deleteCustomer(Customer customer) {
        // String query = "DELETE FROM car WHERE Car_brand = ? AND Car_name = ?";
        String query = "DELETE FROM customer WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            // Rechercher l'ID de l'enregistrement à supprimer en fonction du nom de la voiture
            int customerId = getCustomerIdByName(customer.getFirstName());

            // Utiliser l'ID trouvé comme condition de suppression dans la requête SQL
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Voiture supprimée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode auxiliaire pour récupérer l'ID de l'enregistrement en fonction du nom de la voiture
    private int getCustomerIdByName(String customerName) throws SQLException {
        String query = "SELECT id FROM customer WHERE Customer_FirstName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customerName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        // Si aucun enregistrement correspondant n'est trouvé, retourner -1 ou lever une exception
        throw new SQLException("Aucun enregistrement de voiture trouvé avec le nom : " + customerName);
    }

    // Méthode pour mettre à jour une voiture dans la base de données
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customer SET Customer_FirstName = ?, Customer_LastName = ?, Customer_Licence = ?, " +
                "Customer_Type = ?, Customer_Address = ?, Customer_Phone = ?, " +
                "Customer_password = ? WHERE Customer_FirstName = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getLicence());
            pstmt.setInt(4, customer.getType());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getPhone());
            pstmt.setString(7, customer.getpassword());
            pstmt.setString(8, customer.getFirstName()); // Condition WHERE: Nom
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Voiture modifié avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification de la voiture.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur SQL: " + ex.getMessage());
        }
    }


}
