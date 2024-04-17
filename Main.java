//Main
import Model.*;
import Controller.*;
import View.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           // public void run() {
                Model model = new Model();
                View view = new View();
                Controller controller = new Controller(model, view);
                view.setController(controller);
                view.showHomePage(); // Afficher la page d'accueil


                ViewFormVoiture formulaireVoiture = new ViewFormVoiture();
                ControllerVoiture controllerVoiture = new ControllerVoiture(formulaireVoiture);
                formulaireVoiture.afficher();
           // }
        });
    }
}