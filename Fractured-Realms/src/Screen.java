import javax.swing.*;

/**
 * Base class for all game screens.
 * Provides common functionality and structure for different game screens.
 */
public abstract class Screen {
    protected JPanel panel;
    protected GameManager gameManager;
    
    /**
     * Creates a new Screen with a reference to the game manager.
     * 
     * @param gameManager The game manager
     */
    public Screen(GameManager gameManager) {
        this.gameManager = gameManager;
        this.panel = new JPanel();
    }
    
    /**
     * Gets the panel for this screen.
     * 
     * @return The screen's panel
     */
    public JPanel getPanel() {
        return panel;
    }
    
    /**
     * Called when this screen becomes active.
     */
    public void onShow() {
        // Override in subclasses if needed
    }
    
    /**
     * Called when this screen is hidden.
     */
    public void onHide() {
        // Override in subclasses if needed
    }
} 