package controller;
import model.Customer;
import model.CustomerModel;
import view.CustomerView;
import view.ViewFormCustomer;
import view.ViewFormVoiture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerController {
    private CustomerModel model;
    private CustomerView view;
    private ViewFormCustomer viewFormCustomer; // Ajout de l'instance de ViewFormVoiture

    public void setViewFormCustomer(ViewFormCustomer viewFormCustomer) {
        this.viewFormCustomer = viewFormCustomer;
    }


    public CustomerController(CustomerModel model, CustomerView view, ViewFormCustomer viewFormCustomer) {
        this.model = model;
        this.view = view;
        this.viewFormCustomer = viewFormCustomer; // Initialisation de viewFormVoiture

        // Ajouter un écouteur pour le bouton Ajouter
        view.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFormCustomer.showForm(); // Utilisation de viewFormVoiture pour afficher le formulaire
            }
        });

        // Ajouter un écouteur pour le bouton Supprimer
        view.addDeleteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    Customer customer = model.getAllCustomers().get(selectedRow);
                    model.deleteCustomer(customer);
                    updateCustomerList();
                }
            }
        });

        // Ajouter un écouteur pour le bouton Refresh
        view.addRefreshButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerList();
            }
        });

        // Ajouter un écouteur pour le bouton Modifier
        view.addEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    Customer selectedCustomer = model.getAllCustomers().get(selectedRow);
                    viewFormCustomer.showEditCustomerForm(selectedCustomer); // Utilisation de viewFormVoiture pour afficher le formulaire de modification
                }
            }
        });

        // Ajouter un écouteur pour le bouton Consulter
        view.addViewButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow != -1) {
                    Customer customer = model.getAllCustomers().get(selectedRow);
                    view.showCustomerDetails(customer);
                }
            }
        });
    }


    public void initialize() {
        updateCustomerList();
    }

    public void updateCustomerList() {
        List<Customer> customers = model.getAllCustomers();
        view.updateCustomerList(customers);
    }

    public void modifyCustomer(Customer modifiedCustomer) {
        model.updateCustomer(modifiedCustomer);
        updateCustomerList();
    }
}
