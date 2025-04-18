import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The welcome screen of the game, showing the title and main menu options.
 */
public class WelcomeScreen extends Screen {
    private BufferedImage backgroundImage;
    
    /**
     * Creates a new welcome screen.
     * 
     * @param gameManager The game manager
     */
    public WelcomeScreen(GameManager gameManager) {
        super(gameManager);
        loadResources();
        createUI();
    }
    
    /**
     * Loads screen resources like images.
     */
    private void loadResources() {
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/resources/background-welcomescreen.png"));
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
    }
    
    /**
     * Creates the user interface for this screen.
     */
    private void createUI() {
        // Create main panel with background image
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        
        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        
        // Add title
        JLabel titleLabel = new JLabel("FRACTURED REALMS");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 80));
        titleLabel.setForeground(new Color(255, 248, 220)); // Cream color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel for each button to prevent stretching
        JPanel newGamePanel = new JPanel();
        newGamePanel.setOpaque(false);
        JPanel quitPanel = new JPanel();
        quitPanel.setOpaque(false);
        
        // Create pixel-styled buttons
        JButton newGameButton = createPixelButton("NEW GAME");
        JButton quitButton = createPixelButton("QUIT");
        
        // Add action listeners
        newGameButton.addActionListener(e -> gameManager.showGameScreen());
        quitButton.addActionListener(e -> gameManager.exitGame());
        
        // Add buttons to their panels
        newGamePanel.add(newGameButton);
        quitPanel.add(quitButton);
        
        // Add version label
        JLabel versionLabel = new JLabel(GameManager.VERSION);
        versionLabel.setForeground(new Color(200, 200, 200, 150));
        versionLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add spacing
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(60));
        contentPanel.add(newGamePanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(quitPanel);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(versionLabel);
        
        // Add content panel to main panel
        panel.add(contentPanel);
    }
    
    /**
     * Creates a pixel-styled button with the specified text.
     * 
     * @param text The button text
     * @return The styled button
     */
    private JButton createPixelButton(String text) {
        JButton button = new JButton(text);
        
        // Style button
        button.setBackground(new Color(96, 56, 19)); // Darker brown
        button.setForeground(new Color(255, 248, 220)); // Cream color
        button.setFont(new Font("Monospaced", Font.BOLD, 24));
        
        // Set size
        button.setPreferredSize(new Dimension(250, 60));
        
        // Remove focus border
        button.setFocusPainted(false);
        
        // Create a border
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 219, 155), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(120, 70, 20)); // Slightly lighter on hover
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(96, 56, 19)); // Back to original
            }
        });
        
        return button;
    }
} 