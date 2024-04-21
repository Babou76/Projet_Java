package model;

import java.util.Date;

public class ModelVoiture {
    private int id;
    private String marque;
    private String modele;
    private Date dateAchat;
    private String category;
    private int prix;
    private int nombreSiege;
    private String typeTransmission;
    private String specification;
    private String description;
    private double discount;

    // Constructeur
    public ModelVoiture(String marque, String modele, Date dateAchat, String category,
                        int prix, int nombreSiege, String typeTransmission,
                        String specification, String description, double discount) {
        this.marque = marque;
        this.modele = modele;
        this.dateAchat = dateAchat;
        this.category = category;
        this.prix = prix;
        this.nombreSiege = nombreSiege;
        this.typeTransmission = typeTransmission;
        this.specification = specification;
        this.description = description;
        this.discount = discount;
    }

    // Getters et Setters (pour chaque attribut)
// Getters et Setters pour chaque attribut

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public String getCategory() {
        return category;
    }

    public int getPrix() {
        return prix;
    }

    public int getNombreSiege() {
        return nombreSiege;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public String getSpecification() {
        return specification;
    }

    public String getDescription() {
        return description;
    }
    public double getDiscount() {
        return discount;
    }

}
