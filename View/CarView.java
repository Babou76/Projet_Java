package view;

import model.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CarView extends JFrame {
    private JTable carTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton viewButton;
    private JButton addButton;
    private JButton refreshButton;

    public CarView() {
        setTitle("Liste des Voitures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columns = {"Marque", "Nom", "Date", "Catégorie", "Prix par jour", "Nombre de sièges", "Transmission", "Spécifications", "Statut"};
        tableModel = new DefaultTableModel(columns, 0);
        carTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(carTable);
        getContentPane().add(scrollPane);

        // Création des boutons avec des icônes

        deleteButton = new JButton(new ImageIcon("src/Images/delete_icon.png"));
        editButton = new JButton(new ImageIcon("src/Images/edit_icon.png"));
        viewButton = new JButton(new ImageIcon("src/Images/view_icon.png"));
        addButton = new JButton(new ImageIcon("src/Images/add_icon.png"));
        refreshButton = new JButton(new ImageIcon("src/Images/refresh_icon.png"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateCarList(List<Car> carList) {
        tableModel.setRowCount(0); // Efface toutes les lignes existantes
        for (Car car : carList) {
            Object[] rowData = {
                    car.getBrand(),
                    car.getName(),
                    car.getDate(),
                    car.getCategory(),
                    car.getPricePerDay(),
                    car.getNumberOfSeats(),
                    car.getTransmission(),
                    car.getSpecification(),
                    //car.getDescription()
            };
            tableModel.addRow(rowData);
        }
    }

    public int getSelectedRow() {
        return carTable.getSelectedRow();
    }

    // Méthode pour ajouter un écouteur au bouton Supprimer
    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    // Méthode pour ajouter un écouteur au bouton Modifier
    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    // Méthode pour ajouter un écouteur au bouton Consulter
    public void addViewButtonListener(ActionListener listener) {
        viewButton.addActionListener(listener);
    }

    // Méthode pour ajouter un écouteur au bouton Ajouter
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    // Méthode pour ajouter un écouteur au bouton Refresh
    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }


    // Méthode pour afficher les détails de la voiture
    public void showCarDetails(Car car) {
        JOptionPane.showMessageDialog(this,
                "Détails de la Voiture : \n"
                        + "Marque : " + car.getBrand() + "\n"
                        + "Nom : " + car.getName() + "\n"
                        + "Date : " + car.getDate() + "\n"
                        + "Catégorie : " + car.getCategory() + "\n"
                        + "Prix par jour : " + car.getPricePerDay() + "\n"
                        + "Nombre de sièges : " + car.getNumberOfSeats() + "\n"
                        + "Transmission : " + car.getTransmission() + "\n"
                        + "Spécifications : " + car.getSpecification() + "\n"
                        + "Description : " + car.getDescription(),
                "Détails de Voiture",
                JOptionPane.PLAIN_MESSAGE);
    }
}

