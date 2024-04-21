import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CarCarousel extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout); // Panel for the images
    private final Map<String, String> carImageMap = new HashMap<>(); // Maps car names to image paths
    private final Map<String, JButton> buttonMap = new HashMap<>(); // Maps car names to buttons
    private final Map<String, JLabel> labelMap = new HashMap<>(); // Maps car names to JLabels
    private final String[] carNames = {"Peugeot 208", "Nissan Juke", "Mercedes Classe A"};
    private int currentIndex = 0;

    public CarCarousel() {
        setTitle("Omnes Car Renting");
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Omnes Car Renting", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

        cardPanel.setBackground(Color.BLACK);
        addImagesToCardPanel();
        add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, carImageMap.size()));
        buttonPanel.setBackground(Color.BLACK);
        addButtonsToPanel(buttonPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        startImageCarouselTimer();

        // Add the resize listener to the JFrame
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeImages();
            }
        });
    }

    private void startImageCarouselTimer() {
        int delay = 3000; // milliseconds
        new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cycleImages();
            }
        }).start();
    }

    private void cycleImages() {
        // This assumes that there are always at least 3 images to cycle through
        currentIndex = (currentIndex + 1) % carImageMap.size();
        String carName = carNames[currentIndex];
        cardLayout.show(cardPanel, carName);

        // Update buttons to match the next set of images
        updateButtonLinks();
    }

    private void updateButtonLinks() {
        // Logic to update the buttons goes here
        // For example, you might have something like this:
        for (Map.Entry<String, JButton> entry : buttonMap.entrySet()) {
            String carName = entry.getKey();
            JButton button = entry.getValue();
            button.removeActionListener(button.getActionListeners()[0]); // Remove the old action listener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, carName);
                }
            });
        }
    }

    private void addButtonsToPanel(JPanel buttonPanel) {
        for (String carName : carNames) {
            JButton button = new JButton(carName);
            button.setForeground(Color.WHITE);
            button.setBackground(Color.BLACK);
            button.setFocusPainted(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, carName);
                }
            });
            buttonPanel.add(button);
            buttonMap.put(carName, button);
        }
    }

    private void resizeImages() {
        Dimension newSize = cardPanel.getSize();
        int width = (int) (newSize.width * 0.75); // 3/4 of the panel width
        int height = (int) (newSize.height * 0.75); // 3/4 of the panel height

        for (String carName : carImageMap.keySet()) {
            ImageIcon originalIcon = new ImageIcon(carImageMap.get(carName));
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(scaledImage);
            JLabel label = labelMap.get(carName);
            if (label != null) {
                label.setIcon(newIcon);
            }
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private void addImagesToCardPanel() {
        carImageMap.put("Peugeot 208", "C:\\Users\\User\\IdeaProjects\\onsenfou\\voiture1.jpg");
        carImageMap.put("Nissan Juke", "C:\\Users\\User\\IdeaProjects\\onsenfou\\voiture2.jpg");
        carImageMap.put("Mercedes Classe A", "C:\\Users\\User\\IdeaProjects\\onsenfou\\voiture5.jpg");

        for (String carName : carImageMap.keySet()) {
            ImageIcon icon = new ImageIcon(carImageMap.get(carName));
            JLabel label = new JLabel(icon);
            labelMap.put(carName, label);
            cardPanel.add(label, carName);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarCarousel::new);
    }
}
