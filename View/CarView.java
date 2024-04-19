package view;

import controller.CarController;
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

    public CarView() {
        setTitle("Liste des Voitures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);

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

    // Méthode pour afficher le formulaire de modification
    public void showEditCarForm(Car car) {
        JOptionPane.showMessageDialog(this,
                "Formulaire de Modification pour : \n"
                        + "Marque : " + car.getBrand() + "\n"
                        + "Nom : " + car.getName() + "\n"
                        + "Date : " + car.getDate() + "\n"
                        + "Catégorie : " + car.getCategory() + "\n"
                        + "Prix par jour : " + car.getPricePerDay() + "\n"
                        + "Nombre de sièges : " + car.getNumberOfSeats() + "\n"
                        + "Transmission : " + car.getTransmission() + "\n"
                        + "Spécifications : " + car.getSpecification() + "\n"
                        + "Description : " + car.getDescription(),
                "Modification de Voiture",
                JOptionPane.PLAIN_MESSAGE);
    }
}


//public class DbViewCar extends JFrame {
//    private JTable table;
//    private JButton deleteButton;
//
//    public DbViewCar(List<Tuple> tuples) {
//        setTitle("Table Viewer");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        String[] columnNames = {"ID", "Nom", "Description"};
//        Object[][] data = new Object[tuples.size()][3];
//
//        for (int i = 0; i < tuples.size(); i++) {
//            Tuple tuple = tuples.get(i);
//            data[i][0] = tuple.getId();
//            data[i][1] = tuple.getNom();
//            data[i][2] = tuple.getDescription();
//        }
//
//        table = new JTable(data, columnNames);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        deleteButton = new JButton("Supprimer");
//        deleteButton.addActionListener(e -> {
//            int selectedRow = table.getSelectedRow();
//            if (selectedRow != -1) {
//                int idToDelete = (int) table.getValueAt(selectedRow, 0);
//                try {
//                    // Supprimer le tuple de la base de données
//                    ModelListCar model = new ModelListCar();
//                    model.connect();
//                    model.deleteTuple("table_name", idToDelete);
//                    model.disconnect();
//
//                    // Mettre à jour la vue après la suppression
//                    refreshView();
//                } catch (SQLException ex) {
//                    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du tuple : " + ex.getMessage());
//                }
//            }
//        });
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(deleteButton);
//
//        setLayout(new BorderLayout());
//        add(scrollPane, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        pack();
//        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
//    }

//    public void refreshView() {
//        try {
//            DatabaseModel model = new DatabaseModel("jdbc:mysql://localhost:3306/database_name", "username", "password");
//            model.connect();
//            List<Tuple> updatedTuples = model.getAllTuples("table_name");
//            model.disconnect();
//
//            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//            tableModel.setRowCount(0); // Effacer le contenu actuel de la table
//
//            for (Tuple tuple : updatedTuples) {
//                tableModel.addRow(new Object[]{tuple.getId(), tuple.getNom(), tuple.getDescription()});
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données : " + e.getMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                DatabaseModel model = new DatabaseModel("jdbc:mysql://localhost:3306/database_name", "username", "password");
//                model.connect();
//                List<Tuple> tuples = model.getAllTuples("table_name");
//                model.disconnect();
//
//                DatabaseView view = new DatabaseView(tuples);
//                view.setVisible(true);
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données : " + e.getMessage());
//            }
//        });
//    }
//}
