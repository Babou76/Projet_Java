// Model.java (Modèle)
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Model {
   // public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/website1";
    public static final String USER = "root";
    public static final String PASS = "";

    public void registerCustomer(String firstName, String lastName, String password, String phoneNumber, String address) {
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
}