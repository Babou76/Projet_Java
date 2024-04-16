package Model;
import java.util.Date;

public class ModelVoiture {
    private String marque;
    private String modele;
    private Date dateAchat;
    private String category;
    private int prix;
    private int nombreSiege;
    private String typeTransmission;
    private String specification;
    private String description;

    // Constructeur
    public ModelVoiture(String marque, String modele, Date dateAchat, String category,
                   int prix, int nombreSiege, String typeTransmission,
                   String specification, String description) {
        this.marque = marque;
        this.modele = modele;
        this.dateAchat = dateAchat;
        this.category = category;
        this.prix = prix;
        this.nombreSiege = nombreSiege;
        this.typeTransmission = typeTransmission;
        this.specification = specification;
        this.description = description;
    }

    // Getters et Setters (pour chaque attribut)
// Getters et Setters pour chaque attribut

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
}
