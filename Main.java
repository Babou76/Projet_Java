//Main

import controller.CarController;
import model.CarModel;
import view.CarView;
import view.ViewFormVoiture;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // public void run() {
//            Model model = new Model();
//            View view = new View();
//            Controller controller = new Controller(model, view);
//            view.setController(controller);
//            // view.showHomePage(); // Afficher la page d'accueil


            // Création du modèle, de la vue et du contrôleur

            ViewFormVoiture viewFormVoiture = new ViewFormVoiture();
            CarModel carModel = new CarModel();
            CarView carView = new CarView();
            CarController carController = new CarController(carModel, carView, viewFormVoiture);

            carController.initialize();
        });
    }
}