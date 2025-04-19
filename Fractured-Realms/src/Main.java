import javax.swing.*;

/**
 * Main class for the Fractured Realms game.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame gameWindow = new JFrame("Fractured Realms");
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);

            GamePanel gamePanel = new GamePanel();
            gameWindow.add(gamePanel);

            gameWindow.pack();

            gameWindow.setLocationRelativeTo(null);
            gameWindow.setVisible(true);
        });
    }
}