
# Java-2D-adventureGame

This project is a 2D mini-game fully written in core Java / Swing. The purpose of the project is to learn the basic concepts of game development, such as the game loop, controls, collision detection, image rendering, interaction with objects, etc. The minimum goal is to build the first level, which will include all the mentioned elements.

The game's storyline is inspired by the TV series Lost. You play as a castaway on an island. The first mission involves finding the hatch to an underground bunker.

Important note: The knowledge required to create this game is largely drawn from RyeSnow and the excellent content available on their YouTube channel.
## Screenshots

<img src="/screenshots/screen1.jpg"/>
<img src="/screenshots/screen2.jpg"/>
<img src="/screenshots/screen3.jpg"/>
<img src="/screenshots/screen4.jpg"/>


## Project Structure

The project is built using Maven and is divided into several modules:

- main module - contains the classes Main, GamePanel, KeyHandler, and CollisionManager:
    - Main - launches the program and contains the main() method.
    - GamePanel - initializes basic constants and variables, screen settings, and the game loop. It is a class that often "connects" other classes by providing access to variables from different classes.
    - KeyHandler - handles events triggered by the keyboard.
    - CollisionManager - implements collision detection between objects and the player.

- Entity module - contains everything related to the player, including enemies, monsters, creatures, and generally all living entities.
- Object module - contains all the objects that appear in the game (axe, shovel, key etc.), as well as the AssetSetter class, which places objects on the game map.
- Tile module - includes the Tile class, which defines the properties of tiles used to build the game board, and the TileManager class, which contains methods responsible for retrieving tile images, setting their properties, and rendering the map.
- All graphics, maps, and music are located in the resources folder.
- Maps are drawn using pixels, where each pixel color corresponds to a specific type of tile. Then, MapToNumericConverter.py converts each pixel into a numerical value and saves the resulting .txt file in the resources/maps folder.

(The project structure evolves over time, but I will do my best to keep the description updated to reflect the current state.)


