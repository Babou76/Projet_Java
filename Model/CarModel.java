package model;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModel {
    private Connection connection;

    public CarModel() {
        try {
            // Création de la connexion à la base de données SQLite
            this.connection = DriverManager.getConnection(Model.DB_URL, Model.USER, Model.PASS);
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
                        rs.getString("Car_Description")
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
            pstmt.setString(9, car.getBrand()); // Condition WHERE: Marque
            pstmt.setString(10, car.getName()); // Condition WHERE: Nom
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

