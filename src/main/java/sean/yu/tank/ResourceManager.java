package sean.yu.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-04 06:47
 **/

public class ResourceManager {
    public static BufferedImage tankL, tankR, tankU, tankD;
    static {
        try {
            tankL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("/images/tankL.gif"));
            tankR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("/images/tankR.gif"));
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("/images/tankU.gif"));
            tankD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("/images/tankD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
