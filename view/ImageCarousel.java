package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ImageCarousel extends JPanel {
    private List<ImageIcon> images; // Liste des images
    private int currentIndex; // Index de l'image courante
    private JLabel imageLabel; // Affichage de l'image
    private Timer timer; // Timer pour le carrousel automatique

    public ImageCarousel() {
        // Créez une liste d'images
        images = new ArrayList<>();

        // Taille à laquelle vous voulez que toutes les images soient redimensionnées
        int width = 400; // Largeur cible
        int height = 300; // Hauteur cible

        // Ajouter des images avec redimensionnement
        images.add(redimensionnerImage("src/Images/image1.jpg", width, height)); // Remplacez par les chemins réels des images
        images.add(redimensionnerImage("src/Images/image2.jpg", width, height));
        images.add(redimensionnerImage("src/Images/image3.jpg", width, height));
        images.add(redimensionnerImage("src/Images/image4.jpg", width, height));
        images.add(redimensionnerImage("src/Images/image5.jpg", width, height));

        currentIndex = 0; // L'index de départ

        setLayout(new BorderLayout());

        // Initialisation du label pour afficher l'image
        imageLabel = new JLabel(images.get(currentIndex));
        add(imageLabel, BorderLayout.CENTER); // L'image au centre

        // Configuration du timer pour changement automatique toutes les 3 secondes
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < images.size() - 1) {
                    currentIndex++; // Avancez vers l'image suivante
                } else {
                    currentIndex = 0; // Revient au début
                }
                updateImage(); // Mettez à jour l'image affichée
            }
        });

        timer.start(); // Démarrer le timer

        // Ajoutez le carrousel au panneau principal
        JPanel carouselPanel = new JPanel();
        carouselPanel.setLayout(new BorderLayout());
        carouselPanel.add(imageLabel, BorderLayout.CENTER); // L'image au centre

        add(carouselPanel, BorderLayout.CENTER); // Ajoutez le carrousel au centre du panneau principal
    }

    // Méthode pour redimensionner l'image
    private ImageIcon redimensionnerImage(String cheminImage, int largeur, int hauteur) {
        ImageIcon imageIcon = new ImageIcon(cheminImage);
        Image image = imageIcon.getImage(); // Extraire l'image
        Image redimensionnee = image.getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH); // Redimensionner
        return new ImageIcon(redimensionnee);
    }

    // Méthode pour mettre à jour l'image affichée
    private void updateImage() {
        imageLabel.setIcon(images.get(currentIndex)); // Mettez à jour l'image du label
    }
}
