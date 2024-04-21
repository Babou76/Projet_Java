import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Pay extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost/website1";
    static final String USER = "root";
    static final String PASS = "";

    private JLabel priceLabel;
    private JTextField discountField;
    private JButton applyButton;
    private double price = 0;

    public Pay() { /* Transférer int id du vehicule correspondant*/
        setTitle("Paiement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        priceLabel = new JLabel("Prix: ");
        discountField = new JTextField(10);
        applyButton = new JButton("Appliquer Réduction");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Réduction : "));
        inputPanel.add(discountField);
        inputPanel.add(applyButton);
        JButton BuyButton = new JButton("Valider achat");
        inputPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        inputPanel.add(BuyButton);

        add(priceLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        getPrice();

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyDiscount(price);
            }
        });

        BuyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); /* Fonction vers paiement CB */
            }
        });

        setVisible(true);
    }

    private void applyDiscount(double price) {
            int discount = 0;
            String discountStr = discountField.getText();
            if (!discountStr.isEmpty()) {
                if(discountStr.equals("ARGSIO")) {
                    discount = 15;
                }
                else if (discountStr.equals("QZKUOP")) {
                    discount = 20;
                }

                double discountedPrice = price * (1 - (double) discount / 100);
                priceLabel.setText("Prix (Réduction " + discount + "%): " + discountedPrice + " €");
            }
        }

    public double getPrice(/*id_vehicule, licenceUser*/) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT Car_PricePerDay FROM car WHERE id = 120000");
            ResultSet rs2 = stmt2.executeQuery("SELECT Booking_Start, Booking_End FROM booking WHERE LicenceUser= 127841259635894");

            if (rs.next() && rs2.next()) {
                price = rs.getDouble("Car_PricePerDay");

                Timestamp startTimestamp = rs2.getTimestamp("Booking_Start");
                Timestamp endTimestamp = rs2.getTimestamp("Booking_End");

                // Calculate the difference in milliseconds
                long differenceInMillis = endTimestamp.getTime() - startTimestamp.getTime();

                // Convert milliseconds to days (as float or double)
                double differenceInDays = differenceInMillis / (1000.0 * 60 * 60 * 24);

                String discountStr = discountField.getText();
                if (!discountStr.isEmpty()) {
                    double discount = Double.parseDouble(discountStr);
                    double discountedPrice = price * differenceInDays * (1 - discount / 100);
                    priceLabel.setText("Prix (Réduction " + discount + "%): " + discountedPrice + " €");
                }
                else {
                    priceLabel.setText("Prix: " + price * differenceInDays + " €");
                }
            }

            rs.close();
            rs2.close();
            stmt.close();
            stmt2.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return price;
    }

        public static void main (String[]args){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Pay();
                }
            });
        }
}

