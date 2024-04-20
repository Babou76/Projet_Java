package view;

import controller.CarController;
import controller.ControllerVoiture;
import model.Car;
import model.ModelVoiture;

import javax.swing.*;
import java.util.Date;

public class ViewFormVoiture {
    //private JFrame frame;
   private ControllerVoiture controllerVoitureResgister;
    private CarController carControllerModified;
    private JTextField marqueField;
    private JTextField modelField;
    private JComboBox<String> categoryComboBox;
    private JTextField prixField;
    private JSpinner nombreSiegeSpinner;
    private JComboBox<String> transmissionComboBox;
    private JComboBox<String> specificationComboBox;
    private JTextArea descriptionArea;

    public void showForm() {
        controllerVoitureResgister = new ControllerVoiture();
        JPanel formPanel = new JPanel();
       // formPanel.setLayout(new GridLayout(5, 2));

        // Initialisation des composants graphiques

        marqueField = new JTextField(20);
       // marqueField.setPreferredSize(new Dimension(10, 5)); // Définit une dimension personnalisée (largeur x hauteur)
        modelField = new JTextField(20);
        String[] categori = {"A", "B", "C", "D"};
        categoryComboBox = new JComboBox<>(categori);
        prixField = new JTextField(10);
        nombreSiegeSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        String[] transmissions = {"Automatique", "Manuelle"};
        transmissionComboBox = new JComboBox<>(transmissions);
        String[] specifications = {"Essence", "Diesel", "Hybride", "Electrique"};
        specificationComboBox = new JComboBox<>(specifications);
        descriptionArea = new JTextArea(5, 20);

        // Configuration du layout

        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
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

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Enregistrement Voiture", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            controllerVoitureResgister.enregister(getVoitureFromInput());
//
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


    // Méthode pour afficher le formulaire de modification avec les données de la voiture sélectionnée
    public void showEditCarForm(Car car) {

        carControllerModified = new CarController();
        // Création du formulaire de modification
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Remplissage des champs avec les données de la voiture sélectionnée
        marqueField = new JTextField(car.getBrand());
        modelField = new JTextField(car.getName());
        categoryComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D"});
        categoryComboBox.setSelectedItem(car.getCategory());
        prixField = new JTextField(String.valueOf(car.getPricePerDay()));
        nombreSiegeSpinner = new JSpinner(new SpinnerNumberModel(car.getNumberOfSeats(), 1, 10, 1));
        transmissionComboBox = new JComboBox<>(new String[]{"Automatique", "Manuelle"});
        transmissionComboBox.setSelectedItem(car.getTransmission());
        specificationComboBox = new JComboBox<>(new String[]{"Essence", "Diesel", "Hybride", "Electrique"});
        specificationComboBox.setSelectedItem(car.getSpecification());
        descriptionArea = new JTextArea(car.getDescription());

        // Ajout des composants au formulaire
        formPanel.add(new JLabel("Marque:"));
        formPanel.add(marqueField);
        formPanel.add(new JLabel("Modèle:"));
        formPanel.add(modelField);
        formPanel.add(new JLabel("Catégorie:"));
        formPanel.add(categoryComboBox);
        formPanel.add(new JLabel("Prix par jour:"));
        formPanel.add(prixField);
        formPanel.add(new JLabel("Nombre de sièges:"));
        formPanel.add(nombreSiegeSpinner);
        formPanel.add(new JLabel("Transmission:"));
        formPanel.add(transmissionComboBox);
        formPanel.add(new JLabel("Spécification:"));
        formPanel.add(specificationComboBox);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));

        // Affichage du formulaire dans une boîte de dialogue
        int result = JOptionPane.showConfirmDialog(null, formPanel, "Modifier Voiture",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Si l'utilisateur valide les modifications
        if (result == JOptionPane.OK_OPTION) {
            // Récupérer les valeurs modifiées depuis les champs du formulaire
            String brand = marqueField.getText();
            String name = modelField.getText();
            String category = (String) categoryComboBox.getSelectedItem();
            double pricePerDay = Double.parseDouble(prixField.getText());
            int numberOfSeats = (int) nombreSiegeSpinner.getValue();
            String transmission = (String) transmissionComboBox.getSelectedItem();
            String specification = (String) specificationComboBox.getSelectedItem();
            String description = descriptionArea.getText();

            // Création de l'objet Car avec les nouvelles valeurs
            Car modifiedCar = new Car(brand, name, car.getDate(), category, pricePerDay,
                    numberOfSeats, transmission, specification, description);

            // Appeler la méthode du contrôleur pour effectuer la modification
            carControllerModified.modifyCar(modifiedCar);
        }
    }

}
