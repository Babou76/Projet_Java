package controller;

import model.Car;
import model.CarModel;
import view.CarView;
import view.ViewFormVoiture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarController {
    private CarModel model;
    private CarView view;
    private ViewFormVoiture viewFormVoiture;

    public CarController() {
    }

    public CarController(CarModel model, CarView view, ViewFormVoiture viewFormVoiture) {
        this.model = model;
        this.view = view;
        this.viewFormVoiture = viewFormVoiture;

        // Ajouter un écouteur pour le bouton Ajouter
        view.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFormVoiture.showForm();
            }
        });

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

        // Ajouter un écouteur pour le bouton Refresh
        view.addRefreshButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateCarList();
            }
        });

        // Ajouter un écouteur pour le bouton Modifier
        view.addEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    // Récupérer la voiture sélectionnée dans la table
                    Car selectedCar = model.getAllCars().get(selectedRow);

                    // Afficher le formulaire de modification avec les données de la voiture sélectionnée
                    viewFormVoiture.showEditCarForm(selectedCar);
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

    public void updateCarList() {
        List<Car> cars = model.getAllCars();
        view.updateCarList(cars);
    }

    // Méthode pour gérer la modification de l'enregistrement
    public void modifyCar(Car modifiedCar) {
        model = new CarModel();
        // Appeler la méthode du modèle pour effectuer la modification dans la base de données
        model.updateCar(modifiedCar);

        // Mettre à jour la liste des voitures affichées dans la vue

        updateCarList();
    }
}

