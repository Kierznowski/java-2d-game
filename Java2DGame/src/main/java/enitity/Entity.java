package enitity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // position of the Entity on map
    public int worldX, worldY = 0;
    public int speed = 0;
    public Rectangle collisionArea;
    public boolean collisionOn = false;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}
