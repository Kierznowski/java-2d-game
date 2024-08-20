package org.main;

import enitity.Entity;

public class CollisionManager {

    GamePanel gamePanel;

    public CollisionManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        //coordinates of the collision boundaries of the entity
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;
        //checking which tiles are adjacent to Player
        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;
        int tileNum1, tileNum2;

        switch(entity.direction) {
            case("up") -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            } case("down") -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }

            }
            case ("left") -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }

            }
            case ("right") -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }

            }
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gamePanel.obj.length; i++) {
            if(gamePanel.obj[i] != null) {
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                gamePanel.obj[i].collisionArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].collisionArea.x;
                gamePanel.obj[i].collisionArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].collisionArea.y;

                switch(entity.direction) {
                    case("up") -> {
                        entity.collisionArea.y -= entity.speed;
                        if(entity.collisionArea.intersects(gamePanel.obj[i].collisionArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } case("down") -> {
                        entity.collisionArea.y += entity.speed;
                        if(entity.collisionArea.intersects(gamePanel.obj[i].collisionArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case ("left") -> {
                        entity.collisionArea.x -= entity.speed;
                        if(entity.collisionArea.intersects(gamePanel.obj[i].collisionArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case ("right") -> {
                        entity.collisionArea.x += entity.speed;
                        if(entity.collisionArea.intersects(gamePanel.obj[i].collisionArea)) {
                            if(gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                }
                // bringing back default values for collision area
                entity.collisionArea.x = entity.collisionAreaDefaultX;
                entity.collisionArea.y = entity.collisionAreaDefaultY;
                gamePanel.obj[i].collisionArea.x = gamePanel.obj[i].collisionAreaDefaultX;
                gamePanel.obj[i].collisionArea.y = gamePanel.obj[i].collisionAreaDefaultY;
            }
        }
        return index;
    }
}
