package View;

import Model.ModelVoiture;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;

public class ViewFormVoiture {
    private JFrame frame;
    private JTextField marqueField;
    private JTextField modelField;
    private JComboBox<String> categoryComboBox;
    private JTextField prixField;
    private JSpinner nombreSiegeSpinner;
    private JComboBox<String> transmissionComboBox;
    private JComboBox<String> specificationComboBox;
    private JTextArea descriptionArea;
    private JButton enregistrerButton;

    public ViewFormVoiture() {
        frame = new JFrame("Enregistrement Voiture");

        // Initialisation des composants graphiques

        marqueField = new JTextField(20);
        modelField = new JTextField(20);
        String[] category = {"A", "B", "C", "D"};
        categoryComboBox = new JComboBox<>(category);
        prixField = new JTextField(10);
        nombreSiegeSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 100, 1));
        String[] transmissions = {"Automatique", "Manuelle"};
        transmissionComboBox = new JComboBox<>(transmissions);
        String[] specifications = {"Essence", "Diesel", "Hybride", "Electrique"};
        specificationComboBox = new JComboBox<>(specifications);
        descriptionArea = new JTextArea(5, 20);
        enregistrerButton = new JButton("Enregistrer");

        // Configuration du layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Marque:"));
        panel.add(marqueField);
        panel.add(new JLabel("Modèle:"));
        panel.add(modelField);
        panel.add(new JLabel("Catégorie:"));
        panel.add(categoryComboBox);
        panel.add(new JLabel("Prix:"));
        panel.add(prixField);
        panel.add(new JLabel("Nombre de sièges:"));
        panel.add(nombreSiegeSpinner);
        panel.add(new JLabel("Type de transmission:"));
        panel.add(transmissionComboBox);
        panel.add(new JLabel("Spécification:"));
        panel.add(specificationComboBox);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descriptionArea));
        panel.add(enregistrerButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void afficher() {
        frame.pack();
        frame.setVisible(true);
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

    public void setEnregistrerListener(ActionListener listener) {
        enregistrerButton.addActionListener(listener);
    }
}
