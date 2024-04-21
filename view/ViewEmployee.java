package view;

import controller.CarController;
import model.CarModel;

import javax.swing.*;
import java.awt.*;

public class ViewEmployee extends JFrame {
    private JPanel carPanel;
    private CarView carView;
    private CarModel carModel; // Ajout de l'instance de CarModel
    private CarController carController; // Ajout de l'instance de CarController

    public ViewEmployee() {
        setTitle("Application de Location de Voitures");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation de CarModel et CarController
        carModel = new CarModel();
        carView = new CarView();
        carController = new CarController(carModel, carView, new ViewFormVoiture(carModel, carView)); // Le dernier argument est viewFormVoiture que vous n'avez pas fourni

        // Header avec l'introduction de l'entreprise
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel headerLabel = new JLabel("Bienvenue chez RentACar. Louez votre voiture rapidement et facilement !");
        headerPanel.add(headerLabel);

        // Onglets pour afficher la liste des clients, des voitures, la finance et la popularité
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel customerPanel = new JPanel();
        carPanel = new JPanel(); // Création d'un JPanel pour afficher CarView
        JPanel financePanel = new JPanel();
        JPanel popularityPanel = new JPanel();
        tabbedPane.addTab("Clients", customerPanel);
        tabbedPane.addTab("Voitures", carPanel); // Ajout de carPanel à l'onglet "Voitures"
        tabbedPane.addTab("Finance", financePanel);
        tabbedPane.addTab("Popularité", popularityPanel);

        // Footer avec les copyrights
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel copyrightLabel = new JLabel("\u00a9 2024 RentACar. Tous droits réservés.");
        footerPanel.add(copyrightLabel);

        // Agencement des composants dans la fenêtre
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);

        // Ajout de CarView au carPanel lorsque l'onglet "Voitures" est sélectionné
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 1) { // Si l'onglet "Voitures" est sélectionné
                carPanel.removeAll(); // Supprime tout contenu précédent
                carPanel.add(carView); // Ajoute CarView à carPanel
                carController.initialize(); // Initialise la liste des voitures dans CarView
                revalidate(); // Rafraîchit l'interface utilisateur
                repaint();
            }
        });
    }
}
