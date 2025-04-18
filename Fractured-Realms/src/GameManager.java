import javax.swing.*;

/**
 * GameManager is responsible for managing game screens and transitions.
 * It holds the main frame and handles switching between different screens.
 */
public class GameManager {
    private JFrame mainFrame;
    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;
    private Screen currentScreen;
    
    public static final String VERSION = "Version 0.1"; // Game version

    /**
     * Creates a new GameManager and initializes the game.
     */
    public GameManager() {
        initializeFrame();
        createScreens();
        setupNavigation();
        showWelcomeScreen();
    }
    
    /**
     * Initializes the main frame for the game.
     */
    private void initializeFrame() {
        mainFrame = new JFrame("Fractured Realms");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(true);
        mainFrame.setVisible(true);
    }
    
    /**
     * Creates all game screens.
     */
    private void createScreens() {
        welcomeScreen = new WelcomeScreen(this);
        gameScreen = new GameScreen(this);
    }
    
    /**
     * Sets up navigation between screens.
     */
    private void setupNavigation() {
        // Navigation is handled by the individual screens
    }
    
    /**
     * Shows the welcome screen.
     */
    public void showWelcomeScreen() {
        showScreen(welcomeScreen);
    }
    
    /**
     * Shows the game screen.
     */
    public void showGameScreen() {
        showScreen(gameScreen);
    }
    
    /**
     * Shows the specified screen and performs necessary transitions.
     * 
     * @param screen The screen to show
     */
    private void showScreen(Screen screen) {
        if (currentScreen != null) {
            currentScreen.onHide();
        }
        
        currentScreen = screen;
        screen.onShow();
        
        mainFrame.setContentPane(screen.getPanel());
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    /**
     * Exits the game.
     */
    public void exitGame() {
        System.exit(0);
    }
} 