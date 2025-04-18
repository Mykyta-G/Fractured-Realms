import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Represents a game realm/world that the player can explore.
 */
public class Realm {
    private String name;
    private BufferedImage backgroundImage;
    private List<GameObject> gameObjects;
    private Color backgroundColor;
    
    /**
     * Creates a new realm with the specified name and background path.
     * 
     * @param name The realm name
     * @param backgroundPath The resource path to the background image
     */
    public Realm(String name, String backgroundPath) {
        this.name = name;
        this.gameObjects = new ArrayList<>();
        this.backgroundColor = new Color(20, 20, 20); // Default dark background
        loadBackground(backgroundPath);
    }
    
    /**
     * Loads the realm's background image.
     * 
     * @param path The resource path
     */
    private void loadBackground(String path) {
        try {
            if (path != null && !path.isEmpty()) {
                backgroundImage = ImageIO.read(getClass().getResourceAsStream(path));
            }
        } catch (Exception e) {
            System.out.println("Error loading realm background: " + e.getMessage());
        }
    }
    
    /**
     * Adds a game object to this realm.
     * 
     * @param object The game object to add
     */
    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }
    
    /**
     * Gets the name of this realm.
     * 
     * @return The realm name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the background color for this realm.
     * 
     * @param color The background color
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }
    
    /**
     * Updates all game objects in this realm.
     */
    public void update() {
        for (GameObject obj : gameObjects) {
            obj.update();
        }
    }
    
    /**
     * Draws the realm and all its objects.
     * 
     * @param g The graphics context
     * @param width The viewport width
     * @param height The viewport height
     */
    public void draw(Graphics g, int width, int height) {
        // Draw background
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        } else {
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);
            
            // Draw a simple grid pattern for navigation
            g.setColor(new Color(40, 40, 40));
            for (int x = 0; x < width; x += 50) {
                g.drawLine(x, 0, x, height);
            }
            for (int y = 0; y < height; y += 50) {
                g.drawLine(0, y, width, y);
            }
        }
        
        // Draw all game objects
        for (GameObject obj : gameObjects) {
            obj.draw(g);
        }
    }
} 