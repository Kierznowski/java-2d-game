package enitity;

import java.awt.image.BufferedImage;

public class Entity {
    int x, y = 0;
    int speed = 0;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}