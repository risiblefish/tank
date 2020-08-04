package sean.yu.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 资源管理
 *
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-04 06:47
 **/
public class ResourceManager {
    public static BufferedImage
            tankL, tankR, tankU, tankD,
            bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            //加载坦克图片
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);

            //加载子弹图片
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            //加载爆炸效果图片
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream(String.format("images/e%s.gif", i + 1)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
