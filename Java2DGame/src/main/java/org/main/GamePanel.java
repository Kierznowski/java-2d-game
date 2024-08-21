package org.main;

import enitity.Player;
import object.AssetSetter;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 px tiles
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 px
    public final int maxScreenCol = 16; // screen height tiles
    public final int maxScreenRow = 12; // screen width tiles
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px

    // FPS
    int FPS = 60;
    Sound music = new Sound();
    Sound soundEffects = new Sound();
    Thread gameThread;

    // WORLD SETTINGS
    public final int maxWorldCol = 96;
    public final int maxWorldRow = 80;

    KeyHandler keyHandler = new KeyHandler();
    public TileManager tileManager = new TileManager(this);
    public Player player = new Player(this, keyHandler);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionManager collisionManager = new CollisionManager(this);
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // map
        tileManager.draw(g2);
        // objects
        for(SuperObject object : obj) {
            if(object != null) {
                object.draw(g2, this);
            }
        }
        // player
        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffects.setFile(i);
        soundEffects.play();
    }
}