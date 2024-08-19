package org.main;

import object.*;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new KeyObject();
        gamePanel.obj[0].worldX = 13 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 15 * gamePanel.tileSize;

        gamePanel.obj[1] = new AxeObject();
        gamePanel.obj[1].worldX = 72 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 23 * gamePanel.tileSize;


        gamePanel.obj[2] = new ShovelObject();
        gamePanel.obj[2].worldX = 78 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 39 * gamePanel.tileSize;

        gamePanel.obj[3] = new DoorsObject();
        gamePanel.obj[3].worldX = 0 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 8 * gamePanel.tileSize;
    }
}

