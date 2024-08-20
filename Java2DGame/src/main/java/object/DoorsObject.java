package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorsObject extends SuperObject {

    public DoorsObject() {
        name = "Doors";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doors1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
