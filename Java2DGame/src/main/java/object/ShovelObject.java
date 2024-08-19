package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ShovelObject extends SuperObject {

    public ShovelObject() {
        name = "Shovel";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shovel1.png"));
        } catch (IOException e) {
            System.out.println("No shovel picture found in resources.");
            e.printStackTrace();

        }
    }
}
