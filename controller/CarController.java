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
    private ViewFormVoiture viewFormVoiture; // Ajout de l'instance de ViewFormVoiture

    public void setViewFormVoiture(ViewFormVoiture viewFormVoiture) {
        this.viewFormVoiture = viewFormVoiture;
    }


    public CarController(CarModel model, CarView view, ViewFormVoiture viewFormVoiture) {
        this.model = model;
        this.view = view;
        this.viewFormVoiture = viewFormVoiture; // Initialisation de viewFormVoiture

        // Ajouter un écouteur pour le bouton Ajouter
        view.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFormVoiture.showForm(); // Utilisation de viewFormVoiture pour afficher le formulaire
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
                    Car selectedCar = model.getAllCars().get(selectedRow);
                    viewFormVoiture.showEditCarForm(selectedCar); // Utilisation de viewFormVoiture pour afficher le formulaire de modification
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

    public void modifyCar(Car modifiedCar) {
        model.updateCar(modifiedCar);
        updateCarList();
    }
}
