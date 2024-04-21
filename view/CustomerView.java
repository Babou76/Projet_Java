package view;

import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView extends JPanel {
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton viewButton;
    private JButton addButton;
    private JButton refreshButton;

    public CustomerView() {
        setLayout(new BorderLayout()); // Utilisation d'un BorderLayout pour placer les composants

        String[] columns = {"Prenom", "Nom", "Licence", "Type", "Adresse", "Téléphone", "Mot de passe"};
        tableModel = new DefaultTableModel(columns, 0);
        customerTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(customerTable);
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

    public void updateCustomerList(List<Customer> customerList) {
        tableModel.setRowCount(0); // Efface toutes les lignes existantes
        for (Customer customer : customerList) {
            Object[] rowData = {
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getLicence(),
                    customer.getType(),
                    customer.getAddress(),
                    customer.getPhone(),
                    customer.getpassword(),
            };
            tableModel.addRow(rowData);
        }
    }

    public int getSelectedRow() {
        return customerTable.getSelectedRow();
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

    public void showCustomerDetails(Customer customer) {
        JOptionPane.showMessageDialog(this,
                "Détails du client : \n"
                        + "Marque : " + customer.getFirstName() + "\n"
                        + "Nom : " + customer.getLastName() + "\n"
                        + "Date : " + customer.getLicence() + "\n"
                        + "Catégorie : " + customer.getType() + "\n"
                        + "Prix par jour : " + customer.getAddress() + "\n"
                        + "Nombre de sièges : " + customer.getPhone() + "\n"
                        + "Transmission : " + customer.getpassword() + "\n"
        );
    }
}
