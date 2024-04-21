package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.Controller; // Importation depuis le package controller

public class View extends JFrame {
    public Controller controller;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField passwordField;
    private JTextField phoneNumberField;
    private JTextField addressField;

    private JTextField usernameField;


    private JButton rechercherButton;
    private JTextArea resultatArea;
    private JTextField prixField;
    private JTextField siegesField;
    private JCheckBox automatiqueCheckBox;







    public View() {
        setTitle("Page d'Accueil");
        setSize(800, 600); // Ajustement de la taille de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Header avec le nom de l'entreprise et une brève introduction
        JLabel headerLabel = new JLabel("Bienvenue chez RentACar. Louez votre voiture rapidement et facilement !");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headerLabel, BorderLayout.NORTH);

        // Boutons d'enregistrement, de connexion pour les clients et de connexion pour les employés
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));

        JButton registerButton = new JButton("Enregistrement");
        registerButton.setPreferredSize(new Dimension(50, 50)); // Ajustement de la taille du bouton
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegistrationPage(); // Afficher la page d'enregistrement
            }
        });
        buttonsPanel.add(registerButton);

        JButton loginButton = new JButton("Connexion Client");
        loginButton.setPreferredSize(new Dimension(50, 50)); // Ajustement de la taille du bouton
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginPage(); // Afficher la page de connexion
            }
        });
        buttonsPanel.add(loginButton);

        JButton employeeLoginButton = new JButton("Connexion Employé");
        employeeLoginButton.setPreferredSize(new Dimension(50, 50)); // Ajustement de la taille du bouton
        employeeLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEmployeeLoginPage(); // Afficher la page de connexion employé
            }
        });
        buttonsPanel.add(employeeLoginButton);

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



    public void showLoginPage() {
        // Afficher un formulaire pour la connexion
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2));

        usernameField = new JTextField(20);
        passwordField = new JTextField(20);

        formPanel.add(new JLabel("Nom d'utilisateur:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Mot de passe:"));
        formPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Connexion", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            controller.loginCustomer(username, password);
        }
    }

    public void showEmployeeLoginPage() {
        // Afficher un formulaire pour la connexion employé
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2));

        usernameField = new JTextField(20);
        passwordField = new JTextField(20);

        formPanel.add(new JLabel("Nom d'utilisateur:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Mot de passe:"));
        formPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Connexion Employé", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            controller.loginEmployee(username, password);
        }
    }
    public void showHomePage() {
        setVisible(true); // Afficher la page d'accueil
    }

    public void showHomePage1() {
        // Création de l'en-tête
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.RED); // Couleur de fond de l'en-tête
        headerPanel.setPreferredSize(new Dimension(800, 100)); // Taille de l'en-tête

        // Ajout d'un label dans l'en-tête
        JLabel headerLabel = new JLabel("Bienvenue sur la page d'accueil");
        headerLabel.setForeground(Color.WHITE); // Couleur du texte
        headerPanel.add(headerLabel);

        // Création du cadran de présentation
        JPanel presentationPanel = new JPanel();
        presentationPanel.setLayout(new BorderLayout());

        // Ajout du contenu dans le cadran de présentation
        JTextArea presentationText = new JTextArea("Ici, vous pouvez découvrir nos produits et services.");
        presentationText.setEditable(false); // Empêcher la modification du texte
        presentationText.setLineWrap(true); // Activation du retour à la ligne automatique
        JScrollPane scrollPane = new JScrollPane(presentationText); // Ajout d'une barre de défilement
        presentationPanel.add(scrollPane, BorderLayout.CENTER);

        // Création du pied de page
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.GRAY); // Couleur de fond du pied de page
        footerPanel.setPreferredSize(new Dimension(800, 50)); // Taille du pied de page

        // Ajout des boutons dans le pied de page
        JButton exitButton = new JButton("Quitter");

        // Ajout de l'ActionListener pour le bouton "Quitter"
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });



        // Ajout des boutons au pied de page
        footerPanel.add(exitButton);

        // Supprimer tous les composants existants du contenu principal
        getContentPane().removeAll();

        JButton purchaseButton = new JButton("Reservation");
        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewFiltre();
            }
        });
        footerPanel.add(purchaseButton);

        // Organisation de la disposition des composants dans la fenêtre
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(presentationPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Actualisation de l'affichage
        revalidate();
        repaint();
    }
    // Ajouter d'autres méthodes pour afficher les autres pages (par exemple, la page d'achats)




















    // Déclaration des cases à cocher pour les spécifications de voiture
    private JCheckBox hybrideCheckBox;
    private JCheckBox essenceCheckBox;
    private JCheckBox electriqueCheckBox;
    private JCheckBox dieselCheckBox;

        public void ViewFiltre() {
            // Création de la fenêtre pour le filtre
            JFrame filterFrame = new JFrame("Recherche de Voitures");
            filterFrame.setSize(600, 400); // Ajustement de la taille de la fenêtre
            filterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement la fenêtre du filtre
            filterFrame.setLocationRelativeTo(this); // Positionnement par rapport à la fenêtre de la page d'accueil

            // Initialisation des cases à cocher pour les spécifications
            hybrideCheckBox = new JCheckBox("Hybride");
            essenceCheckBox = new JCheckBox("Essence");
            electriqueCheckBox = new JCheckBox("Electrique");
            dieselCheckBox = new JCheckBox("Diesel");

            // Ajout des composants pour le filtre dans la fenêtre du filtre
            rechercherButton = new JButton("Rechercher");
            automatiqueCheckBox = new JCheckBox("Automatique");
            prixField = new JTextField(15); // Ajustement de la taille du champ de texte
            siegesField = new JTextField(15); // Ajustement de la taille du champ de texte

            resultatArea = new JTextArea();
            resultatArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Ajustement de la taille de la police
            JScrollPane scrollPane = new JScrollPane(resultatArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(550, 200)); // Ajustement de la taille du composant de défilement

            rechercherButton.setPreferredSize(new Dimension(120, 30)); // Ajustement de la taille du bouton

            rechercherButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double prixSouhaite = Double.parseDouble(prixField.getText());
                    int nombreSieges = Integer.parseInt(siegesField.getText());
                    boolean estAutomatique = automatiqueCheckBox.isSelected();
                    boolean hybride = hybrideCheckBox.isSelected();
                    boolean essence = essenceCheckBox.isSelected();
                    boolean electrique = electriqueCheckBox.isSelected();
                    boolean diesel = dieselCheckBox.isSelected();
                    controller.rechercherVoitures(prixSouhaite, nombreSieges, estAutomatique, hybride, essence, electrique, diesel);
                    // Après la recherche, faites défiler la zone de résultat vers le bas
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
                        }
                    });
                }
            });

            JPanel formPanel = new JPanel(new GridLayout(6, 2)); // Modification de la grille pour inclure les cases à cocher
            formPanel.add(new JLabel("Prix souhaité :"));
            formPanel.add(prixField);
            formPanel.add(new JLabel("Nombre de sièges :"));
            formPanel.add(siegesField);
            formPanel.add(new JLabel("Transmission :"));
            formPanel.add(automatiqueCheckBox);
            formPanel.add(new JLabel("Spécifications :"));
            formPanel.add(hybrideCheckBox);
            formPanel.add(new JLabel());
            formPanel.add(essenceCheckBox);
            formPanel.add(new JLabel());
            formPanel.add(electriqueCheckBox);
            formPanel.add(new JLabel());
            formPanel.add(dieselCheckBox);
            formPanel.add(new JLabel());
            formPanel.add(rechercherButton);

            // Ajout du formulaire et de la zone de résultat à la fenêtre des filtres
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajustement de la marge intérieure
            contentPanel.add(formPanel, BorderLayout.NORTH);
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            // Ajout du contenu à la fenêtre des filtres
            filterFrame.getContentPane().add(contentPanel);

            // Affichage de la fenêtre du filtre
            filterFrame.setVisible(true);
        }


        public void afficherResultat(List<String> resultats) {
        resultatArea.setText(""); // Effacer le texte précédent
        for (String resultat : resultats) {
            resultatArea.append(resultat + "\n");
        }
    }


}

