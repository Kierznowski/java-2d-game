package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AxeObject extends SuperObject {

    public AxeObject() {
        name = "Axe";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/axe1.png"));
        } catch (IOException e) {
            System.out.println("No axe image found on resources.");
            e.printStackTrace();
        }
        collision = true;
    }
}
