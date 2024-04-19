package model;
import controller.CarController;
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
        String query = "DELETE FROM car WHERE Car_brand = ? AND Car_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//public class ModelListCar {
//    public Connection connection;
////    private String url;
////    private String username;
////    private String password;
//
////    public ModelListCar(String url, String username, String password) {
////        this.url = url;
////        this.username = username;
////        this.password = password;
////    }
//
//    public void connect() throws SQLException {
//        connection = DriverManager.getConnection(Model.DB_URL, Model.USER, Model.PASS);
//    }
//
//    public void disconnect() throws SQLException {
//        if (connection != null && !connection.isClosed()) {
//            connection.close();
//        }
//    }
//
//    public List<Tuple> getAllTuples(String tableName) throws SQLException {
//        List<Tuple> tuples = new ArrayList<>();
//        String query = "SELECT * FROM " + tableName;
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//            while (resultSet.next()) {
//                Tuple tuple = new Tuple(
//                        resultSet.getInt("id"),
//                        resultSet.getString("nom"),
//                        resultSet.getString("description")
//                        // Ajoutez d'autres colonnes selon votre table
//                );
//                tuples.add(tuple);
//            }
//        }
//        return tuples;
//    }
//
//    public void deleteTuple(String tableName, int id) throws SQLException {
//        String query = "DELETE FROM " + tableName + " WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        }
//    }
//}
