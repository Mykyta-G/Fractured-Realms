import java.awt.*;

/**
 * Base class for all game objects that can exist in a realm.
 */
public abstract class GameObject {
    protected int x, y;
    protected int width, height;
    
    /**
     * Creates a new game object at the specified position.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @param width The width
     * @param height The height
     */
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Gets the x coordinate.
     * 
     * @return The x coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y coordinate.
     * 
     * @return The y coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets the width.
     * 
     * @return The width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gets the height.
     * 
     * @return The height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets the position.
     * 
     * @param x The new x coordinate
     * @param y The new y coordinate
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Checks if this object collides with another.
     * 
     * @param other The other game object
     * @return True if the objects collide
     */
    public boolean collidesWith(GameObject other) {
        return x < other.x + other.width &&
               x + width > other.x &&
               y < other.y + other.height &&
               y + height > other.y;
    }
    
    /**
     * Updates the object's state.
     */
    public abstract void update();
    
    /**
     * Draws the object.
     * 
     * @param g The graphics context
     */
    public abstract void draw(Graphics g);
} 