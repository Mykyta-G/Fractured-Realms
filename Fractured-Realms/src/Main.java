import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {
    private static BufferedImage WelcomeBackgroundImage;
    private static JFrame mainFrame; // Store the main frame globally
    private static final String VERSION = "Version 0.1"; // Game version
    
    public static void main(String[] args) {
        // Create the main frame that will be used throughout the application
        mainFrame = new JFrame("Fractured Realms");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        
        // Show welcome screen first
        showWelcomeScreen();
        
        // Make the frame visible
        mainFrame.setVisible(true);
    }
    
    private static void showWelcomeScreen() {
        // Load background image
        try {
            WelcomeBackgroundImage = ImageIO.read(Main.class.getResourceAsStream("/resources/background-welcomescreen.png"));
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
        
        // Create main panel with background image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (WelcomeBackgroundImage != null) {
                    g.drawImage(WelcomeBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        
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
        newGameButton.addActionListener(e -> {
            // Switch to game screen in the same frame
            showGameScreen();
        });
        
        quitButton.addActionListener(e -> System.exit(0));
        
        // Add buttons to their panels
        newGamePanel.add(newGameButton);
        quitPanel.add(quitButton);
        
        // Add version label
        JLabel versionLabel = new JLabel(VERSION);
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
        mainPanel.add(contentPanel);
        
        // Set content pane - replacing any existing content
        mainFrame.setContentPane(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    private static JButton createPixelButton(String text) {
        JButton button = new JButton(text);
        
        // Make it look like the button in the screenshot but shorter width
        button.setBackground(new Color(96, 56, 19)); // Darker brown
        button.setForeground(new Color(255, 248, 220)); // Cream color
        button.setFont(new Font("Monospaced", Font.BOLD, 24));
        
        // Reduced width to match preference
        button.setPreferredSize(new Dimension(250, 60));
        
        // Remove focus border
        button.setFocusPainted(false);
        
        // Create a border that looks like the golden pixel border in the screenshot
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 219, 155), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Add hover effect but keep button non-stretched
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
    
    private static void showGameScreen() {
        // Create completely stock game content
        JPanel gamePanel = new JPanel();
        
        // Use default layout manager
        gamePanel.setLayout(new BorderLayout());
        
        // Add a label to indicate the game window is ready for implementation
        JLabel label = new JLabel("Game Window");
        label.setHorizontalAlignment(JLabel.CENTER);
        gamePanel.add(label, BorderLayout.CENTER);
        
        // Optional: Add a back button to return to welcome screen
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> showWelcomeScreen());
        gamePanel.add(backButton, BorderLayout.SOUTH);
        
        // Set the content pane - replacing welcome screen content
        mainFrame.setContentPane(gamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}