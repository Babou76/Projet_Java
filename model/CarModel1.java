package model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CarModel1 {
    public static final String DB_URL = "jdbc:mysql://localhost/website1";
    public static final String USER = "root";
    public static final String PASS = "";

    public Map<String, Integer> getCarCategoryCounts() {
        Map<String, Integer> categoryCounts = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sqlQuery = "SELECT Car_category, COUNT(*) AS count FROM car GROUP BY Car_category";
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String category = rs.getString("Car_category");
                int count = rs.getInt("count");
                categoryCounts.put(category, count);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return categoryCounts;
    }
}
