package org.main;

import object.DoorsObject;
import object.KeyObject;
import object.SuperObject;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new KeyObject();
        gamePanel.obj[0].worldX = 13 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 15 * gamePanel.tileSize;

        gamePanel.obj[1] = new KeyObject();
        gamePanel.obj[1].worldX = 45 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 35 * gamePanel.tileSize;

        gamePanel.obj[2] = new DoorsObject();
        gamePanel.obj[2].worldX = 0 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 8 * gamePanel.tileSize;
    }
}

