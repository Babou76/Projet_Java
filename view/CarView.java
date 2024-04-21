package view;

import model.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CarView extends JPanel {
    private JTable carTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton viewButton;
    private JButton addButton;
    private JButton refreshButton;

    public CarView() {
        setLayout(new BorderLayout()); // Utilisation d'un BorderLayout pour placer les composants

        String[] columns = {"Marque", "Nom", "Date", "Catégorie", "Prix par jour", "Nombre de sièges", "Transmission", "Spécifications", "Discount"};
        tableModel = new DefaultTableModel(columns, 0);
        carTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(carTable);
        add(scrollPane, BorderLayout.CENTER); // Ajout du JScrollPane au centre du CarView

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

        add(buttonPanel, BorderLayout.SOUTH); // Ajout du JPanel des boutons en bas du CarView
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
                    car.getDiscount(),
            };
            tableModel.addRow(rowData);
        }
    }

    public int getSelectedRow() {
        return carTable.getSelectedRow();
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addViewButtonListener(ActionListener listener) {
        viewButton.addActionListener(listener);
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

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
                        + "Description : " + car.getDescription() + "\n",
                        STR."\{"Discount : "}\{car.getDiscount()}\n",
                JOptionPane.PLAIN_MESSAGE);
    }
}
