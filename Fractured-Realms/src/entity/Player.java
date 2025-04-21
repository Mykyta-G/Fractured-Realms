package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    
    // Animation variables
    private int spriteCounter = 0;
    private int spriteNum = 0; // Start at 0 for array indexing
    private final int animationSpeed = 10; // Speed of animation (lower = faster)
    private final int idleAnimationSpeed = 20; // Slower speed for idle animations
    
    // Arrays to hold animation frames for each direction - walking
    private BufferedImage[] upFrames;
    private BufferedImage[] downFrames;
    private BufferedImage[] leftDownFrames;
    private BufferedImage[] rightDownFrames;
    private BufferedImage[] leftUpFrames;
    private BufferedImage[] rightUpFrames;
    
    // Arrays to hold animation frames for each direction - idle
    private BufferedImage[] idleUpFrames;
    private BufferedImage[] idleDownFrames;
    private BufferedImage[] idleLeftDownFrames;
    private BufferedImage[] idleRightDownFrames;
    private BufferedImage[] idleLeftUpFrames;
    private BufferedImage[] idleRightUpFrames;
    
    // Spritesheet configuration
    private final int numFrames = 8; // 8 columns in walk spritesheet
    private final int idleNumFrames = 8; // Number of frames in idle spritesheet
    private final int numDirections = 6; // 6 rows in your spritesheet
    
    // Last facing direction (for idle animations)
    private String lastDirection = "down";
    
    // Movement state
    private boolean isMoving = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        lastDirection = "down";
    }

    public void getPlayerImage() {
        try {
            // Initialize animation frame arrays for walking
            downFrames = new BufferedImage[numFrames];
            upFrames = new BufferedImage[numFrames];
            leftDownFrames = new BufferedImage[numFrames];
            rightDownFrames = new BufferedImage[numFrames];
            leftUpFrames = new BufferedImage[numFrames];
            rightUpFrames = new BufferedImage[numFrames];
            
            // Initialize animation frame arrays for idle
            idleDownFrames = new BufferedImage[idleNumFrames];
            idleUpFrames = new BufferedImage[idleNumFrames];
            idleLeftDownFrames = new BufferedImage[idleNumFrames];
            idleRightDownFrames = new BufferedImage[idleNumFrames];
            idleLeftUpFrames = new BufferedImage[idleNumFrames];
            idleRightUpFrames = new BufferedImage[idleNumFrames];
            
            // Load walk sprite sheets
            BufferedImage upSheet = ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk_up.png"));
            BufferedImage leftDownSheet = ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk_left_down.png"));
            BufferedImage rightDownSheet = ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk_right_down.png"));
            BufferedImage downSheet = ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk_down.png"));
            BufferedImage leftUpSheet = ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk_left_up.png"));
            BufferedImage rightUpSheet = ImageIO.read(getClass().getResourceAsStream("/player/Walk/walk_right_up.png"));
            
            // Load idle sprite sheets
            BufferedImage idleUpSheet = ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_up.png"));
            BufferedImage idleLeftDownSheet = ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_left_down.png"));
            BufferedImage idleRightDownSheet = ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_right_down.png"));
            BufferedImage idleDownSheet = ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_down.png"));
            BufferedImage idleLeftUpSheet = ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_left_up.png"));
            BufferedImage idleRightUpSheet = ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_right_up.png"));
            
            // Calculate dimensions for walking frames
            int upFrameWidth = upSheet.getWidth() / numFrames;
            int upFrameHeight = upSheet.getHeight();
            
            int downFrameWidth = downSheet.getWidth() / numFrames;
            int downFrameHeight = downSheet.getHeight();
            
            int leftDownFrameWidth = leftDownSheet.getWidth() / numFrames;
            int leftDownFrameHeight = leftDownSheet.getHeight();
            
            int rightDownFrameWidth = rightDownSheet.getWidth() / numFrames;
            int rightDownFrameHeight = rightDownSheet.getHeight();
            
            int leftUpFrameWidth = leftUpSheet.getWidth() / numFrames;
            int leftUpFrameHeight = leftUpSheet.getHeight();
            
            int rightUpFrameWidth = rightUpSheet.getWidth() / numFrames;
            int rightUpFrameHeight = rightUpSheet.getHeight();
            
            // Calculate dimensions for idle frames
            int idleUpFrameWidth = idleUpSheet.getWidth() / idleNumFrames;
            int idleUpFrameHeight = idleUpSheet.getHeight();
            
            int idleDownFrameWidth = idleDownSheet.getWidth() / idleNumFrames;
            int idleDownFrameHeight = idleDownSheet.getHeight();
            
            int idleLeftDownFrameWidth = idleLeftDownSheet.getWidth() / idleNumFrames;
            int idleLeftDownFrameHeight = idleLeftDownSheet.getHeight();
            
            int idleRightDownFrameWidth = idleRightDownSheet.getWidth() / idleNumFrames;
            int idleRightDownFrameHeight = idleRightDownSheet.getHeight();
            
            int idleLeftUpFrameWidth = idleLeftUpSheet.getWidth() / idleNumFrames;
            int idleLeftUpFrameHeight = idleLeftUpSheet.getHeight();
            
            int idleRightUpFrameWidth = idleRightUpSheet.getWidth() / idleNumFrames;
            int idleRightUpFrameHeight = idleRightUpSheet.getHeight();
            
            // Extract walk animation frames
            for (int i = 0; i < numFrames; i++) {
                upFrames[i] = upSheet.getSubimage(i * upFrameWidth, 0, upFrameWidth, upFrameHeight);
                downFrames[i] = downSheet.getSubimage(i * downFrameWidth, 0, downFrameWidth, downFrameHeight);
                leftDownFrames[i] = leftDownSheet.getSubimage(i * leftDownFrameWidth, 0, leftDownFrameWidth, leftDownFrameHeight);
                rightDownFrames[i] = rightDownSheet.getSubimage(i * rightDownFrameWidth, 0, rightDownFrameWidth, rightDownFrameHeight);
                leftUpFrames[i] = leftUpSheet.getSubimage(i * leftUpFrameWidth, 0, leftUpFrameWidth, leftUpFrameHeight);
                rightUpFrames[i] = rightUpSheet.getSubimage(i * rightUpFrameWidth, 0, rightUpFrameWidth, rightUpFrameHeight);
            }
            
            // Extract idle animation frames
            for (int i = 0; i < idleNumFrames; i++) {
                idleUpFrames[i] = idleUpSheet.getSubimage(i * idleUpFrameWidth, 0, idleUpFrameWidth, idleUpFrameHeight);
                idleDownFrames[i] = idleDownSheet.getSubimage(i * idleDownFrameWidth, 0, idleDownFrameWidth, idleDownFrameHeight);
                idleLeftDownFrames[i] = idleLeftDownSheet.getSubimage(i * idleLeftDownFrameWidth, 0, idleLeftDownFrameWidth, idleLeftDownFrameHeight);
                idleRightDownFrames[i] = idleRightDownSheet.getSubimage(i * idleRightDownFrameWidth, 0, idleRightDownFrameWidth, idleRightDownFrameHeight);
                idleLeftUpFrames[i] = idleLeftUpSheet.getSubimage(i * idleLeftUpFrameWidth, 0, idleLeftUpFrameWidth, idleLeftUpFrameHeight);
                idleRightUpFrames[i] = idleRightUpSheet.getSubimage(i * idleRightUpFrameWidth, 0, idleRightUpFrameWidth, idleRightUpFrameHeight);
            }

            // Set default initial images
            up = upFrames[0];
            down = downFrames[0];
            left = leftDownFrames[0];
            right = rightDownFrames[0];
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        isMoving = false;
        
        // Handle movement in all 8 directions with consistent speed
        if(keyH.upPressed && keyH.leftPressed) {
            direction = "leftup";
            lastDirection = direction; // Save last direction for idle state
            y -= speed * 0.7;  // Diagonal speed adjustment
            x -= speed * 0.7;
            isMoving = true;
        } else if(keyH.upPressed && keyH.rightPressed) {
            direction = "rightup";
            lastDirection = direction;
            y -= speed * 0.7;  // Diagonal speed adjustment
            x += speed * 0.7;
            isMoving = true;
        } else if(keyH.downPressed && keyH.leftPressed) {
            direction = "leftdown";
            lastDirection = direction;
            y += speed * 0.7;  // Diagonal speed adjustment
            x -= speed * 0.7;
            isMoving = true;
        } else if(keyH.downPressed && keyH.rightPressed) {
            direction = "rightdown";
            lastDirection = direction;
            y += speed * 0.7;  // Diagonal speed adjustment
            x += speed * 0.7;
            isMoving = true;
        } else if(keyH.upPressed) {
            direction = "up";
            lastDirection = direction;
            y -= speed;  // Full speed for cardinal directions
            isMoving = true;
        } else if(keyH.downPressed){
            direction = "down";
            lastDirection = direction;
            y += speed;  // Full speed for cardinal directions
            isMoving = true;
        } else if(keyH.leftPressed) {
            direction = "leftdown";
            lastDirection = direction;
            x -= speed;  // Full speed for cardinal directions
            isMoving = true;
        } else if(keyH.rightPressed) {
            direction = "rightdown";
            lastDirection = direction;
            x += speed;  // Full speed for cardinal directions
            isMoving = true;
        } else {
            // If not moving, set direction to the last direction + "idle"
            direction = lastDirection + "idle";
        }
        
        // Handle animation timing
        spriteCounter++;
        if (isMoving) {
            // Faster animation for movement
            if(spriteCounter > animationSpeed) {
                spriteNum++;
                if(spriteNum >= numFrames) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        } else {
            // Slower animation for idle
            if(spriteCounter > idleAnimationSpeed) {
                spriteNum++;
                if(spriteNum >= idleNumFrames) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
        // Get the current frame based on direction and animation state
        switch(direction) {
            // Walking animations
            case "up":
                image = upFrames[spriteNum];
                break;
            case "down":
                image = downFrames[spriteNum];
                break;
            case "leftdown":
                image = leftDownFrames[spriteNum];
                break;
            case "rightdown":
                image = rightDownFrames[spriteNum];
                break;
            case "leftup":
                image = leftUpFrames[spriteNum];
                break;
            case "rightup":
                image = rightUpFrames[spriteNum];
                break;
                
            // Idle animations
            case "upidle":
                image = idleUpFrames[spriteNum];
                break;
            case "downidle":
                image = idleDownFrames[spriteNum];
                break;
            case "leftdownidle":
                image = idleLeftDownFrames[spriteNum];
                break;
            case "rightdownidle":
                image = idleRightDownFrames[spriteNum];
                break;
            case "leftupidle":
                image = idleLeftUpFrames[spriteNum];
                break;
            case "rightupidle":
                image = idleRightUpFrames[spriteNum];
                break;
                
            // Default fallback
            default:
                image = idleDownFrames[0]; // Default idle position
                break;
        }
        
        // Scale by the game's scale factor (3)
        int width = image.getWidth() * 3;
        int height = image.getHeight() * 3;
        
        // Center the image on the player's position
        int drawX = x + (gp.tileSize - width) / 2;
        int drawY = y + (gp.tileSize - height) / 2;
        
        g2.drawImage(image, drawX, drawY, width, height, null);
    }
}
