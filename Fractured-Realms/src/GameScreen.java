import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The main game screen where gameplay takes place.
 */
public class GameScreen extends Screen {
    private Player player;
    private Map<String, Realm> realms;
    private String currentRealmId;
    private GamePanel gamePanel;
    private boolean[] keyStates;
    
    /**
     * Creates a new game screen.
     * 
     * @param gameManager The game manager
     */
    public GameScreen(GameManager gameManager) {
        super(gameManager);
        keyStates = new boolean[256];
        initializeGame();
        createUI();
    }
    
    /**
     * Initializes game components.
     */
    private void initializeGame() {
        // Create player
        player = new Player(100, 100);
        
        // Create realms
        realms = new HashMap<>();
        
        // Create forest realm
        Realm forestRealm = new Realm("Forest", "/resources/forest_background.png");
        forestRealm.setBackgroundColor(new Color(20, 60, 20)); // Dark green if no image
        realms.put("forest", forestRealm);
        
        // Create cave realm
        Realm caveRealm = new Realm("Cave", "/resources/cave_background.png");
        caveRealm.setBackgroundColor(new Color(50, 30, 20)); // Dark brown if no image
        realms.put("cave", caveRealm);
        
        // Set initial realm
        currentRealmId = "forest";
    }
    
    /**
     * Creates the user interface for this screen.
     */
    private void createUI() {
        // Use border layout for the main panel
        panel.setLayout(new BorderLayout());
        
        // Create game panel for rendering the game
        gamePanel = new GamePanel();
        panel.add(gamePanel, BorderLayout.CENTER);
        
        // Create control panel at the bottom
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(new Color(30, 30, 30));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Current realm indicator
        JLabel realmLabel = new JLabel("Current Realm: " + getRealm().getName());
        realmLabel.setForeground(Color.WHITE);
        controlPanel.add(realmLabel, BorderLayout.WEST);
        
        // Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> gameManager.showWelcomeScreen());
        controlPanel.add(backButton, BorderLayout.EAST);
        
        // Realm switching controls
        JPanel realmControls = new JPanel();
        realmControls.setOpaque(false);
        JButton forestButton = new JButton("Forest Realm");
        forestButton.addActionListener(e -> teleportToRealm("forest"));
        JButton caveButton = new JButton("Cave Realm");
        caveButton.addActionListener(e -> teleportToRealm("cave"));
        realmControls.add(forestButton);
        realmControls.add(caveButton);
        controlPanel.add(realmControls, BorderLayout.CENTER);
        
        panel.add(controlPanel, BorderLayout.SOUTH);
        
        // Set up keyboard input
        setupKeyboardInput();
    }
    
    /**
     * Sets up keyboard input handling.
     */
    private void setupKeyboardInput() {
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyStates[e.getKeyCode()] = true;
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                keyStates[e.getKeyCode()] = false;
            }
        });
    }
    
    /**
     * Gets the current realm.
     * 
     * @return The current realm
     */
    private Realm getRealm() {
        return realms.get(currentRealmId);
    }
    
    /**
     * Teleports the player to a different realm.
     * 
     * @param realmId The ID of the realm to teleport to
     */
    private void teleportToRealm(String realmId) {
        if (realms.containsKey(realmId)) {
            currentRealmId = realmId;
            System.out.println("Teleported to " + getRealm().getName());
        }
    }
    
    /**
     * Updates the game state.
     */
    private void updateGame() {
        // Handle player movement
        int dx = 0, dy = 0;
        
        if (keyStates[KeyEvent.VK_UP] || keyStates[KeyEvent.VK_W]) dy = -1;
        if (keyStates[KeyEvent.VK_DOWN] || keyStates[KeyEvent.VK_S]) dy = 1;
        if (keyStates[KeyEvent.VK_LEFT] || keyStates[KeyEvent.VK_A]) dx = -1;
        if (keyStates[KeyEvent.VK_RIGHT] || keyStates[KeyEvent.VK_D]) dx = 1;
        
        player.move(dx, dy);
        
        // Update current realm
        getRealm().update();
    }
    
    /**
     * Called when this screen becomes active.
     */
    @Override
    public void onShow() {
        // Initialize or reset game state
        System.out.println("Game screen activated");
        gamePanel.requestFocus();
        
        // Start the game loop
        startGameLoop();
    }
    
    /**
     * Called when this screen is hidden.
     */
    @Override
    public void onHide() {
        // Save game state or perform cleanup
        System.out.println("Game screen hidden");
    }
    
    /**
     * Starts the game loop.
     */
    private void startGameLoop() {
        // Create a simple game loop using a Timer
        Timer gameLoop = new Timer(16, e -> {
            updateGame();
            gamePanel.repaint();
        });
        gameLoop.start();
    }
    
    /**
     * Custom panel for rendering the game.
     */
    private class GamePanel extends JPanel {
        public GamePanel() {
            setBackground(Color.BLACK);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Get current realm
            Realm realm = getRealm();
            
            // Draw realm
            realm.draw(g, getWidth(), getHeight());
            
            // Draw player
            player.draw(g);
        }
    }
} 