import controller.Controller;
import model.Model;
import view.View;
import controller.CarController;
import model.CarModel;
import view.CarView;
import view.ViewFormVoiture;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            Model model = new Model();
            View view = new View();
            Controller controller = new Controller(model, view);
            view.setController(controller);
            view.showHomePage();

            CarModel carModel = new CarModel();
            CarView carView = new CarView();

            // Utilisez-les pour créer une instance de ViewFormVoiture
            ViewFormVoiture viewFormVoiture = new ViewFormVoiture(carModel, carView);
            CarController carController = new CarController(carModel, carView, viewFormVoiture);
            carController.setViewFormVoiture(viewFormVoiture); // Assignez l'instance de ViewFormVoiture à votre CarController
            carController.initialize();
            SwingUtilities.invokeLater(CarCarousel::new);
            }
        });
    }
