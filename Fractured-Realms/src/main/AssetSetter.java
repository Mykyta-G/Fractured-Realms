package main;

import main.object.OBJ_Door;
import main.object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        //KEYS
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 22 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 32 * gp.tileSize;
        gp.obj[1].worldY = 12 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 18 * gp.tileSize;

        //DOOR
        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 32 * gp.tileSize;
        gp.obj[1].worldY = 12 * gp.tileSize;
    }
}
