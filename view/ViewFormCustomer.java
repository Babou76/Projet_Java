package view;

import controller.CustomerController;
import controller.ControllerCustomer;
import model.Customer;
import model.CustomerModel;
import model.ModelCustomer;

import javax.swing.*;
import java.util.Date;

public class ViewFormCustomer {
    //private JFrame frame;
    private ControllerCustomer controllerCustomerResgister;
    private CustomerController customerControllerModified;
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JTextField LicenceField;
    private JTextField TypeField;
    private JTextField phoneField;
    private JTextField passwordField;
    private JTextField AddressField;
    private CustomerModel customerModel;
    private CustomerView customerView;
    private CustomerController customerController;

    public ViewFormCustomer(CustomerModel customerModel, CustomerView customerView) {
        this.customerModel = customerModel;
        this.customerView = customerView;
    }
    public void showForm() {
        controllerCustomerResgister = new ControllerCustomer(); // Remplacez ControllerVoiture() par le constructeur approprié si nécessaire
        JPanel formPanel = new JPanel();

        // Initialisation des composants graphiques

        FirstNameField = new JTextField(20);
        // marqueField.setPreferredSize(new Dimension(10, 5)); // Définit une dimension personnalisée (largeur x hauteur)
        LastNameField = new JTextField(20);
        LicenceField = new JTextField(20);
        TypeField = new JTextField(20);
        phoneField = new JTextField(20);
        passwordField = new JTextField(20);
        AddressField = new JTextField( 20);

        // Configuration du layout

        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(new JLabel("Prenom :"));
        formPanel.add(FirstNameField);
        formPanel.add(new JLabel("Nom :"));
        formPanel.add(LastNameField);
        formPanel.add(new JLabel("Licence :"));
        formPanel.add(LicenceField);
        formPanel.add(new JLabel("Type :"));
        formPanel.add(TypeField);
        formPanel.add(new JLabel("Téléphone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Mot de Passe : "));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Adresse : "));
        formPanel.add(new JScrollPane(AddressField));

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Enregistrement Client", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            controllerCustomerResgister.enregister(getCustomerFromInput());
        }
    }

    public ModelCustomer getCustomerFromInput() {
        String FirstName = FirstNameField.getText();
        String LastName = LastNameField.getText();
        String Licence = LicenceField.getText();
        int Type = Integer.parseInt(TypeField.getText());
        String Address = AddressField.getText();
        int phone = Integer.parseInt(phoneField.getText());
        int password = Integer.parseInt(passwordField.getText());

        return new ModelCustomer(FirstName, LastName, Licence, Address, Type, phone, password);
    }


    // Méthode pour afficher le formulaire de modification avec les données de la voiture sélectionnée
    public void showEditCustomerForm(Customer customer) {
        customerControllerModified = new CustomerController(customerModel, customerView, this);

        customerController = new CustomerController(customerModel, customerView, this);
        // Création du formulaire de modification
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Remplissage des champs avec les données de la voiture sélectionnée
        FirstNameField = new JTextField(customer.getFirstName());
        LastNameField = new JTextField(customer.getLastName());
        LicenceField = new JTextField(customer.getLicence());
        TypeField = new JTextField(String.valueOf(customer.getType()));
        phoneField = new JTextField(customer.getPhone());
        passwordField = new JTextField(customer.getpassword());
        AddressField = new JTextField(customer.getAddress());

        // Ajout des composants au formulaire
        formPanel.add(new JLabel("Prenom :"));
        formPanel.add(FirstNameField);
        formPanel.add(new JLabel("Nom :"));
        formPanel.add(LastNameField);
        formPanel.add(new JLabel("Licence :"));
        formPanel.add(LicenceField);
        formPanel.add(new JLabel("Type :"));
        formPanel.add(TypeField);
        formPanel.add(new JLabel("Téléphone :"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Mot de Passe :"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Adresse :"));
        formPanel.add(new JScrollPane(AddressField));

        // Affichage du formulaire dans une boîte de dialogue
        int result = JOptionPane.showConfirmDialog(null, formPanel, "Modification du client",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Si l'utilisateur valide les modifications
        if (result == JOptionPane.OK_OPTION) {
            // Récupérer les valeurs modifiées depuis les champs du formulaire
            String FirstName = FirstNameField.getText();
            String LastName = LastNameField.getText();
            String Licence = LicenceField.getText();
            int Type = Integer.parseInt(TypeField.getText());
            String password = phoneField.getText();
            String Phone = phoneField.getText();
            if (Phone.isEmpty()) {
                // Gérer le cas où le champ est vide
            } else {
                int PhoneField = Integer.parseInt(Phone); // Cela ne provoquera pas d'erreur si le champ n'est pas vide
            }
            String Address = AddressField.getText();

            // Création de l'objet Car avec les nouvelles valeurs
            Customer modifiedCustomer = new Customer(FirstName, LastName, Licence, Type, Address,
                    Phone, password);

            // Appeler la méthode du contrôleur pour effectuer la modification
            customerControllerModified.modifyCustomer(modifiedCustomer);
        }
    }
}