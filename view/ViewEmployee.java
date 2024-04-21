package view;

import controller.CarController;
import model.CarModel;
import model.CarModel1;
import model.CustomerModel;
import controller.CustomerController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ViewEmployee extends JFrame {
    private JPanel carPanel;
    private JPanel popularityPanel;
    private JPanel financePanel;
    private JPanel clientPanel; // Nouveau panneau pour l'onglet client
    private CarView carView;
    private CarModel1 carModel1;
    private CarModel carModel;
    private CarController carController;
    private CustomerModel customerModel;
    private CustomerController customerController;
    private CustomerView customerView;
    private JPanel customerPanel;

    public ViewEmployee() {
        setTitle("Application de Location de Voitures");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation des instances
        carModel1 = new CarModel1();
        carModel = new CarModel();
        carView = new CarView();
        carController = new CarController(carModel, carView, new ViewFormVoiture(carModel, carView));
        customerModel = new CustomerModel();
        customerView = new CustomerView();
        customerController = new CustomerController(customerModel, customerView, new ViewFormCustomer(customerModel, customerView));

        // Onglets
        JTabbedPane tabbedPane = new JTabbedPane();
        carPanel = new JPanel();
        financePanel = new JPanel();
        popularityPanel = new JPanel();
        customerPanel = new JPanel(); // Initialisation du panneau client



        tabbedPane.addTab("Voitures", carPanel);
        tabbedPane.addTab("Finance", financePanel);
        tabbedPane.addTab("Popularité", popularityPanel);
        tabbedPane.addTab("Client", customerPanel); // Ajouter l'onglet client

        // Gestion des onglets
        tabbedPane.addChangeListener(e -> {
            int selectedTab = tabbedPane.getSelectedIndex();

            if (selectedTab == 0) { // Si "Voitures" est sélectionné
                carPanel.removeAll();
                carPanel.add(carView);
                carController.initialize();
                revalidate();
                repaint();
            }
            else if (selectedTab == 1) { // Si "Finance" est sélectionné
                financePanel.removeAll();

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.addValue(30000, "Revenus", "Janvier");
                dataset.addValue(25000, "Revenus", "Février");
                dataset.addValue(27000, "Revenus", "Mars");

                JFreeChart barChart = ChartFactory.createBarChart(
                        "Revenus Mensuels",
                        "Mois",
                        "Revenu",
                        dataset,
                        org.jfree.chart.plot.PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );

                ChartPanel chartPanel = new ChartPanel(barChart);
                financePanel.add(chartPanel);
                revalidate();
                repaint();
            }
            else if (selectedTab == 2) { // Si "Popularité" est sélectionné
                popularityPanel.removeAll();

                // Récupérer les proportions des catégories
                Map<String, Integer> categoryCounts = carModel1.getCarCategoryCounts();

                DefaultPieDataset pieDataset = new DefaultPieDataset();
                categoryCounts.forEach((category, count) -> {
                    pieDataset.setValue("Catégorie " + category, count);
                });

                JFreeChart pieChart = ChartFactory.createPieChart(
                        "Inventaire des Catégories",
                        pieDataset,
                        true,
                        true,
                        false
                );

                ChartPanel pieChartPanel = new ChartPanel(pieChart);
                popularityPanel.add(pieChartPanel);
                revalidate();
                repaint();
            }
            else if (selectedTab == 3) { // Si "Client" est sélectionné
                customerPanel.removeAll();
                customerPanel.add(customerView);
                customerController.initialize();
                revalidate();
                repaint();
            }
        });

        // Agencement des composants
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
}
