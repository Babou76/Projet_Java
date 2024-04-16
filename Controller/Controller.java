// Controller.java (Contrôleur)
package Controller;

import Model.*;
import View.*;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void registerCustomer(String firstName, String lastName, String password, String phoneNumber, String address) {
        model.registerCustomer(firstName, lastName, password, phoneNumber, address);
    }

    // Ajouter d'autres méthodes de contrôleur si nécessaire
}