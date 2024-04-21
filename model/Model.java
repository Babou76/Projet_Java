package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Model {
    public static final String DB_URL = "jdbc:mysql://localhost/website1";
    public static final String USER = "root";
    public static final String PASS = "";
    public void registerCustomer(String firstName, String lastName, String password, String phoneNumber, String address) {
        // Code pour enregistrer un nouveau client dans la base de données
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Random random = new Random();
            long customerLicence = Math.abs(random.nextLong()) % 100000000000000L;

            String sqlInsert = "INSERT INTO customer (customer_FirstName, customer_LastName, Customer_password, Customer_Phone, Customer_address, Customer_Licence, Customer_Type) VALUES (?, ?, ?, ?, ?, ?, 0)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, password);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, address);
            pstmt.setLong(6, customerLicence);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            // Gérer l'erreur
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur
        }
    }
    public boolean checkCredentials(String username, String password) {
        boolean isValid = false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sqlQuery = "SELECT * FROM customer WHERE customer_FirstName=? AND Customer_password=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            isValid = rs.next(); // Vérifie s'il y a au moins une ligne correspondante dans le résultat

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            // Gérer l'erreur
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur
        }
        return isValid;
    }

    public boolean checkEmployeeCredentials(String username, String password) {
        boolean isValid = false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sqlQuery = "SELECT * FROM employee WHERE Employee_FirstName=? AND Employee_Password=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            isValid = rs.next(); // Vérifie s'il y a au moins une ligne correspondante dans le résultat

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            // Gérer l'erreur
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur
        }
        return isValid;
    }



    public List<String> rechercherVoitures(double prixSouhaite, int nombreSieges, boolean estAutomatique, boolean hybride, boolean essence, boolean electrique, boolean diesel) {
        List<String> resultats = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Construire la requête SQL en fonction des paramètres
            String sql = "SELECT ID, Car_Brand, Car_Description, Car_NbSeat, Car_PricePerDay FROM car WHERE Car_PricePerDay <= ? AND Car_NbSeat >= ? AND Car_availibility=1";

            // Filtrage par transmission
            if (estAutomatique) {
                sql += " AND Car_transmission = 'Automatique'";
            } else {
                sql += " AND Car_transmission = 'Manuelle'";
            }

            // Filtrage par spécifications
            if (hybride) {
                sql += " AND Car_specification = 'Hybride'";
            }
            if (essence) {
                sql += " AND Car_specification = 'Essence'";
            }
            if (electrique) {
                sql += " AND Car_specification = 'Electrique'";
            }
            if (diesel) {
                sql += " AND Car_specification = 'Diesel'";
            }

            // Préparer la déclaration
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, prixSouhaite);
            pstmt.setInt(2, nombreSieges);

            // Exécuter la requête
            rs = pstmt.executeQuery();

            // Parcourir les résultats et les ajouter à la liste des résultats
            while (rs.next()) {
                String ID = rs.getString("ID");
                String brand = rs.getString("Car_Brand");
                String description = rs.getString("Car_Description");
                int nbSeat = rs.getInt("Car_NbSeat");
                double pricePerDay = rs.getDouble("Car_PricePerDay");
                resultats.add("ID: " + ID + ", Marque: " + brand + ", Description: " + description + ", Nombre de sièges: " + nbSeat + ", Prix par jour: " + pricePerDay);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultats;
    }


    public static class CustomerModel {
        private Connection connection;

        public CustomerModel() {
            try {
                // Création de la connexion à la base de données SQLite
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Car> getAllCars() {
            List<Car> cars = new ArrayList<>();
            String query = "SELECT * FROM car";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    Car car = new Car(
                            rs.getString("Car_brand"),
                            rs.getString("Car_name"),
                            rs.getDate("Car_Date"),
                            rs.getString("Car_category"),
                            rs.getDouble("Car_PricePerday"),
                            rs.getInt("Car_NbSeat"),
                            rs.getString("Car_transmission"),
                            rs.getString("Car_specification"),
                            rs.getString("Car_Description"),
                            rs.getString("Car_Discount")
                    );
                    cars.add(car);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return cars;
        }

        public void deleteCar(Car car) {
            // String query = "DELETE FROM car WHERE Car_brand = ? AND Car_name = ?";
            String query = "DELETE FROM car WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                // Rechercher l'ID de l'enregistrement à supprimer en fonction du nom de la voiture
                int carId = getCarIdByName(car.getName());

                // Utiliser l'ID trouvé comme condition de suppression dans la requête SQL
                pstmt.setInt(1, carId);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Voiture supprimée avec succès !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Méthode auxiliaire pour récupérer l'ID de l'enregistrement en fonction du nom de la voiture
        private int getCarIdByName(String carName) throws SQLException {
            String query = "SELECT id FROM car WHERE Car_name = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, carName);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    }
                }
            }
            // Si aucun enregistrement correspondant n'est trouvé, retourner -1 ou lever une exception
            throw new SQLException("Aucun enregistrement de voiture trouvé avec le nom : " + carName);
        }

        // Méthode pour mettre à jour une voiture dans la base de données
        public void updateCar(Car car) {
            String query = "UPDATE car SET Car_brand = ?, Car_name = ?, Car_category = ?, " +
                    "Car_PricePerday = ?, Car_NbSeat = ?, Car_transmission = ?, " +
                    "Car_specification = ?, Car_Description = ? WHERE Car_name = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, car.getBrand());
                pstmt.setString(2, car.getName());
                pstmt.setString(3, car.getCategory());
                pstmt.setDouble(4, car.getPricePerDay());
                pstmt.setInt(5, car.getNumberOfSeats());
                pstmt.setString(6, car.getTransmission());
                pstmt.setString(7, car.getSpecification());
                pstmt.setString(8, car.getDescription());
                pstmt.setString(9, car.getName()); // Condition WHERE: Nom
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
}