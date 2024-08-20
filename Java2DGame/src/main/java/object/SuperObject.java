package object;

import org.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle collisionArea = new Rectangle(0, 0, 48, 48);
    public int collisionAreaDefaultX = 0;
    public int collisionAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        // checking if tile is visible on the screen and if draw it
        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX ||
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX ||
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY ||
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)  {
            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
