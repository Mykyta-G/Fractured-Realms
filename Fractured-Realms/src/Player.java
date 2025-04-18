import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Represents the player character in the game.
 */
public class Player extends GameObject {
    private int speed;
    private BufferedImage sprite;
    
    /**
     * Creates a new player at the specified position.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Player(int x, int y) {
        super(x, y, 32, 32);
        this.speed = 5;
        loadSprite();
    }
    
    /**
     * Loads the player sprite.
     */
    private void loadSprite() {
        try {
            // This is a placeholder - in a real game, you'd use an actual sprite
            sprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = sprite.createGraphics();
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);
            g.fillOval(8, 8, 16, 16);
            g.dispose();
        } catch (Exception e) {
            System.out.println("Error creating player sprite: " + e.getMessage());
        }
    }
    
    /**
     * Moves the player by the specified amounts.
     * 
     * @param dx The x movement
     * @param dy The y movement
     */
    public void move(int dx, int dy) {
        this.x += dx * speed;
        this.y += dy * speed;
    }
    
    /**
     * Updates the player's state.
     */
    @Override
    public void update() {
        // Player update logic will go here (e.g., input handling)
    }
    
    /**
     * Draws the player on the specified graphics context.
     * 
     * @param g The graphics context
     */
    @Override
    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }
} 