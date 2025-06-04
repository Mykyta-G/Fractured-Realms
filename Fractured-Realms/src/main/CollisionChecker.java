package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "leftup":
                // Check both left and up collisions
                int leftupLeftCol = (int)((entityLeftWorldX - entity.speed * 0.707)/gp.tileSize);
                int leftupTopRow = (int)((entityTopWorldY - entity.speed * 0.707)/gp.tileSize);

                // Check the corner tile
                tileNum1 = gp.tileM.mapTileNum[leftupLeftCol][leftupTopRow];
                if (gp.tileM.tile[tileNum1].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "rightup":
                // Check both right and up collisions
                int rightupRightCol = (int)((entityRightWorldX + entity.speed * 0.707)/gp.tileSize);
                int rightupTopRow = (int)((entityTopWorldY - entity.speed * 0.707)/gp.tileSize);

                // Check the corner tile
                tileNum1 = gp.tileM.mapTileNum[rightupRightCol][rightupTopRow];
                if (gp.tileM.tile[tileNum1].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "downleft":
                // Check both down and left collisions
                int downleftLeftCol = (int)((entityLeftWorldX - entity.speed * 0.707)/gp.tileSize);
                int downleftBottomRow = (int)((entityBottomWorldY + entity.speed * 0.707)/gp.tileSize);

                // Check the corner tile
                tileNum1 = gp.tileM.mapTileNum[downleftLeftCol][downleftBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "downright":
                // Check both down and right collisions
                int downrightRightCol = (int)((entityRightWorldX + entity.speed * 0.707)/gp.tileSize);
                int downrightBottomRow = (int)((entityBottomWorldY + entity.speed * 0.707)/gp.tileSize);

                // Check the corner tile
                tileNum1 = gp.tileM.mapTileNum[downrightRightCol][downrightBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
