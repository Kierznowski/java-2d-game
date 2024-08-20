package enitity;

import org.main.GamePanel;
import org.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    boolean hasAxe = false;
    boolean hasShovel = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        // coordinates of Player on the screen. Should always point center of the screen.
        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;
        // collisions
        collisionArea = new Rectangle(8, 16, 32, 30);
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 45;
        worldY = gamePanel.tileSize * 25;
        speed = 4;
        direction = "down";
    }

    public void update() {
        // check if Player moves
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            // check if collision doesn't occur, and then update position
            collisionOn = false;
            gamePanel.collisionManager.checkTile(this);
            int objIndex = gamePanel.collisionManager.checkObject(this, true);
            pickUpObject(objIndex);

            if(!collisionOn) {
                switch(direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            // animate movement, spriteNum alternate image of Player in draw() function
            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        // check if action happen
        if(keyHandler.actionPressed && hasAxe) {
            int col = worldX / gamePanel.tileSize;
            int row = worldY / gamePanel.tileSize;
            switch (direction) {
                case "up" -> {
                    row--;
                }
                case "down" -> {
                    row++;
                }
                case "left" -> {
                    col--;
                }
                case "right" -> {
                    col++;
                }
            }
           if(gamePanel.tileManager.mapTileNum[col][row] == 3) {
               gamePanel.tileManager.mapTileNum[col][row] = 0;
           }
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            String objectName = gamePanel.obj[i].name;
            switch(objectName) {
                case "Axe" -> {
                    hasAxe = true;
                    gamePanel.obj[i] = null;
                }
                case "Shovel" -> {
                    hasShovel = true;
                    gamePanel.obj[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up" -> {
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
            }
            case "down" -> {
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2   ;
                }
            }
            case "right" ->  {
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
            }
            case "left" -> {
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }
}

