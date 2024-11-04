package org.main;

import object.AxeObject;
import object.ShovelObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;

public class UserInterface {

    GamePanel gamePanel;
    Font georgia_30;
    BufferedImage axeImage;
    BufferedImage shovelImage;
    public boolean messageOn = false;
    public String message = "";
    int messageTime = 120; // 120 frames = 2 sec

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        georgia_30 = new Font("Georfia", Font.PLAIN, 30);
        AxeObject axe = new AxeObject();
        ShovelObject shovel = new ShovelObject();
        axeImage = axe.image;
        shovelImage = shovel.image;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(georgia_30);
        g2.setColor(Color.white);
        g2.drawString("Find some tool to get into this bush", 50, 50);
        if(gamePanel.player.hasAxe) {
            g2.drawImage(axeImage, gamePanel.screenWidth - 2*gamePanel.tileSize, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(gamePanel.player.hasShovel) {
            g2.drawImage(shovelImage, gamePanel.tileSize - 3*gamePanel.tileSize, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
        }

        if(messageOn) {
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
            messageTime--;
            if(messageTime == 0) {
                messageOn = false;
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}
