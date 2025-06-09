package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // --- SET-UP-WINDOW ---
            JFrame pregameWindow = new JFrame("Fractured Realms");
            pregameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pregameWindow.setResizable(false);
            pregameWindow.setSize(500, 600);
            pregameWindow.setLocationRelativeTo(null);

            // Background panel with custom painting
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image bg = new ImageIcon(Main.class.getResource("/Image/Background.jpg")).getImage();
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            };
            backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

            // Load title image
            ImageIcon icon = new ImageIcon(Main.class.getResource("/Image/TitleCard.png"));
            Image scaled = icon.getImage().getScaledInstance(450, 250, Image.SCALE_SMOOTH);
            JLabel titleLabel = new JLabel(new ImageIcon(scaled));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Buttons
            JButton startButton = new JButton("Start Game");
            JButton editorButton = new JButton("Game Editor");

            Dimension buttonSize = new Dimension(200, 50);
            startButton.setMaximumSize(buttonSize);
            editorButton.setMaximumSize(buttonSize);

            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            editorButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Layout components with extra spacing
            backgroundPanel.add(Box.createVerticalStrut(100)); // Push image down more
            backgroundPanel.add(titleLabel);
            backgroundPanel.add(Box.createVerticalStrut(20)); // Space between image and buttons
            backgroundPanel.add(startButton);
            backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            backgroundPanel.add(editorButton);
            backgroundPanel.add(Box.createVerticalGlue());

            // Add panel to frame
            pregameWindow.add(backgroundPanel);
            pregameWindow.setVisible(true);

            // Button actions
            startButton.addActionListener(e -> {
                pregameWindow.setVisible(false);

                // Create game window and panel only when Start is clicked
                JFrame gameWindow = new JFrame("Fractured Realms");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameWindow.setResizable(false);

                GamePanel gamePanel = new GamePanel();
                gameWindow.add(gamePanel);
                gameWindow.pack();
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);

                gamePanel.setupGame();
                gamePanel.startGameThread();
            });

            editorButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(pregameWindow, "Game Editor Coming Soon!");
            });
        });
    }
}
