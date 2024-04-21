// Controller.java (Contrôleur)
package controller;

import model.Model; // Importation depuis le package model
import view.View; // Importation depuis le package view
import view.ViewEmployee;

import java.util.List;

public class Controller {
    public Model model;
    public View view; // Modification du type de view en View

    public Controller(Model model, View view) { // Modification du type de view en View
        this.model = model;
        this.view = view;
        view.setController(this);
    }
    public void showHomePage1() {
        view.dispose(); // Fermer la page de connexion ou d'enregistrement
        view = new View(); // Créer une nouvelle instance de la vue
        view.setController(this); // Afficher la page d'accueil
        view.showHomePage1(); // Vous pouvez commenter cette ligne car showHomePage1() n'est pas défini dans View
    }

    public void registerCustomer(String firstName, String lastName, String password, String phoneNumber, String address) {
        model.registerCustomer(firstName, lastName, password, phoneNumber, address);
        showHomePage1();
    }

    public void loginCustomer(String username, String password) {
        // Vérifier les informations de connexion dans le modèle
        boolean isValid = model.checkCredentials(username, password);
        if (isValid) {
            System.out.println("Connexion réussie !");
            view.showHomePage1(); // Afficher la page d'accueil après la connexion réussie
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

    public void loginEmployee(String username, String password) {
        // Vérifier les informations de connexion dans le modèle pour les employés
        boolean isValidEmployee = model.checkEmployeeCredentials(username, password);
        if (isValidEmployee) {
            System.out.println("Connexion réussie en tant qu'employé !");
            // Fermer la page précédente
            view.dispose();
            // Ajouter votre logique pour la page d'accueil pour les employés ici
            ViewEmployee employeeView = new ViewEmployee();
            employeeView.setVisible(true); // Afficher la vue de l'employé
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect pour l'employé.");
            // Afficher un message d'erreur si la connexion échoue pour les employés
        }
    }


    public void rechercherVoitures(double prixSouhaite, int nombreSieges, boolean estAutomatique, boolean hybride, boolean essence, boolean electrique, boolean diesel) {
        if (view != null) { // Vérifier que viewFiltre n'est pas null
            List<String> resultats = model.rechercherVoitures(prixSouhaite, nombreSieges, estAutomatique, hybride,essence, electrique, diesel);
            view.afficherResultat(resultats);
        } else {
            System.out.println("Erreur : ViewFiltre est null dans le Controller.");
        }
    }
}