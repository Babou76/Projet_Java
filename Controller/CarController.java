package controller;
import view.CarView;
import model.CarModel;
import model.Car;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarController {
    private CarModel model;
    private CarView view;

    public CarController(CarModel model, CarView view) {
        this.model = model;
        this.view = view;

        // Ajouter un écouteur pour le bouton Supprimer
        view.addDeleteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    Car car = model.getAllCars().get(selectedRow);
                    model.deleteCar(car);
                    updateCarList();
                }
            }
        });

        // Ajouter un écouteur pour le bouton Modifier
        view.addEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    Car car = model.getAllCars().get(selectedRow);

                    // Appeler une méthode dans la vue pour afficher le formulaire de modification
                    view.showEditCarForm(car);
                }
            }
        });

        // Ajouter un écouteur pour le bouton Consulter
        view.addViewButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    Car car = model.getAllCars().get(selectedRow);
                    // Appeler une méthode dans la vue pour afficher les détails de la voiture
                    view.showCarDetails(car);
                }
            }
        });
    }

    public void initialize() {
        updateCarList();
    }

    private void updateCarList() {
        List<Car> cars = model.getAllCars();
        view.updateCarList(cars);
    }
}

//public class ControllerListCar {
//    private ModelListCar modelListCar;
//    private DbViewCar dbViewCar;
//
//    public ControllerListCar(ModelListCar modelListCar, DbViewCar dbViewCar) {
//        this.modelListCar = modelListCar;
//        this.dbViewCar = dbViewCar;
//
//        // Ajouter un ActionListener pour le bouton "Supprimer" dans la vue
//        this.dbViewCar.addDeleteButtonListener(e -> handleDeleteButtonClick());
//    }
//
//    public void startApplication() {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                // Connexion au modèle et récupération des données initiales depuis la base de données
//                modelListCar.connect();
//                List<Tuple> tuples = modelListCar.getAllTuples("table_name");
//                modelListCar.disconnect();

//                // Affichage de la vue avec les données initiales
//                dbViewCar.refreshView(tuples);
//                dbViewCar.setVisible(true);
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données : " + e.getMessage());
//            }
//        });
//    }
//
//    private void handleDeleteButtonClick() {
//        // Récupérer l'ID du tuple sélectionné dans la vue
//        int selectedRow = dbViewCar.getSelectedRow();
//        if (selectedRow != -1) {
//            int idToDelete = dbViewCar.getIdAtRow(selectedRow);
//
//            try {
//                // Supprimer le tuple correspondant dans la base de données
//                modelListCar.connect();
//                modelListCar.deleteTuple("table_name", idToDelete);
//                modelListCar.disconnect();

//                // Mettre à jour la vue après la suppression
//                List<Tuple> updatedTuples = modelListCar.getAllTuples("table_name");
//                dbViewCar.refreshView(updatedTuples);
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du tuple : " + e.getMessage());
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un tuple à supprimer.");
//        }
//    }