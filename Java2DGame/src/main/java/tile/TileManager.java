package tile;

import org.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;            // array of different types of Tiles
    public int[][] mapTileNum;     // matrix of numbers representing map

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol] [gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/maps/island-1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass2.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water0.png")));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall0.png")));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree2.png")));
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand1.png")));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth1.png")));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/borders/water-sand-right-up.png")));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/borders/water-sand-right-down.png")));
            tile[7].collision = true;
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/borders/water-sand-left-down.png")));
            tile[8].collision = true;
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/borders/water-sand-left-up.png")));
            tile[9].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR during loading tiles images.");
        }
    }

    public void loadMap(String mapPath) {
        try {
            InputStream mapFile = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(mapFile));

            for(int row = 0; row < gamePanel.maxWorldRow; row++) {
                String line = br.readLine();
                for(int col = 0; col < gamePanel.maxWorldCol; col++) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for(int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
            for(int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++) {
                int tileNum = mapTileNum[worldCol][worldRow];
                int worldX = worldCol * gamePanel.tileSize;
                int worldY = worldRow * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                // checking if tile is visible on the screen and if draw it
                if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX ||
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX ||
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY ||
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)  {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }

            }
        }
    }
}
