package view;

import controller.ControllerVoiture;
import model.ModelVoiture;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ViewFormVoiture {
    //private JFrame frame;
   private ControllerVoiture controllerVoitureResgister;
    private JTextField marqueField;
    private JTextField modelField;
    private JComboBox<String> categoryComboBox;
    private JTextField prixField;
    private JSpinner nombreSiegeSpinner;
    private JComboBox<String> transmissionComboBox;
    private JComboBox<String> specificationComboBox;
    private JTextArea descriptionArea;

//    public ViewFormVoiture(ControllerVoiture controllerVoiture) {
//        this.controllerVoitureResgister = controllerVoiture;
//    }
    // private JButton enregistrerButton;

//    public ViewFormVoiture() {
//    controllerVoitureResgister = new ControllerVoiture();
////
//    }

    public void showForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        // Initialisation des composants graphiques

        marqueField = new JTextField(20);
        modelField = new JTextField(20);
        String[] categori = {"A", "B", "C", "D"};
        categoryComboBox = new JComboBox<>(categori);
        prixField = new JTextField(10);
        nombreSiegeSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 100, 1));
        String[] transmissions = {"Automatique", "Manuelle"};
        transmissionComboBox = new JComboBox<>(transmissions);
        String[] specifications = {"Essence", "Diesel", "Hybride", "Electrique"};
        specificationComboBox = new JComboBox<>(specifications);
        descriptionArea = new JTextArea(5, 20);
        // enregistrerButton = new JButton("Enregistrer");

        // Configuration du layout

        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        formPanel.add(new JLabel("Marque:"));
        formPanel.add(marqueField);
        formPanel.add(new JLabel("Modèle:"));
        formPanel.add(modelField);
        formPanel.add(new JLabel("Catégorie:"));
        formPanel.add(categoryComboBox);
        formPanel.add(new JLabel("Prix:"));
        formPanel.add(prixField);
        formPanel.add(new JLabel("Nombre de sièges:"));
        formPanel.add(nombreSiegeSpinner);
        formPanel.add(new JLabel("Type de transmission:"));
        formPanel.add(transmissionComboBox);
        formPanel.add(new JLabel("Spécification:"));
        formPanel.add(specificationComboBox);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));
        // formPanel.add(enregistrerButton);
       // int result = JOptionPane.showConfirmDialog(null, formPanel, "Enregistrement Voiture", JOptionPane.OK_CANCEL_OPTION);
        int result = JOptionPane.showConfirmDialog(null, formPanel, "Enregistrement Voiture", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (controllerVoitureResgister != null) {
                controllerVoitureResgister.enregister();
            } else {
                System.out.println("Erreur : controllerVoitureResgister non initialisé !");
                // Gérer l'erreur de manière appropriée (afficher un message d'erreur, lever une exception, etc.)
            }
        }
    }

    public ModelVoiture getVoitureFromInput() {
        String marque = marqueField.getText();
        String model = modelField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        int prix = Integer.parseInt(prixField.getText());
        int nombreSiege = (int) nombreSiegeSpinner.getValue();
        String transmission = (String) transmissionComboBox.getSelectedItem();
        String specification = (String) specificationComboBox.getSelectedItem();
        String description = descriptionArea.getText();

        return new ModelVoiture(marque, model, new Date(), category, prix, nombreSiege, transmission, specification, description);
    }
}
