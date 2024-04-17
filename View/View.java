// View.java (Vue)
package View;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Controller controller;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JTextField phoneNumberField;
    private JTextField addressField;

    public View() {
        setTitle("Page d'Accueil");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Réservations possibles");

        JButton registerButton = new JButton("Enregistrement");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegistrationPage(); // Afficher la page d'enregistrement
            }
        });

        JButton purchaseButton = new JButton("Achats");
        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajouter le code pour accéder à la page d'achats
            }
        });

        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));
        buttonsPanel.add(registerButton);
        buttonsPanel.add(purchaseButton);
        buttonsPanel.add(exitButton);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        add(panel);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showRegistrationPage() {
        // Afficher un formulaire pour l'enregistrement
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        phoneNumberField = new JTextField(20);
        addressField = new JTextField(20);

        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Mot de passe:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Numéro de téléphone:"));
        formPanel.add(phoneNumberField);
        formPanel.add(new JLabel("Adresse:"));
        formPanel.add(addressField);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Enregistrement", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            String phoneNumber = phoneNumberField.getText();
            String address = addressField.getText();

            controller.registerCustomer(firstName, lastName, password, phoneNumber, address);
        }
    }

    public void showHomePage() {
        setVisible(true); // Afficher la page d'accueil
    }

    // Ajouter d'autres méthodes pour afficher les autres pages (par exemple, la page d'achats)
}