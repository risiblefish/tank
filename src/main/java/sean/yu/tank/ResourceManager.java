package sean.yu.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 资源管理
 *
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-04 06:47
 **/
public class ResourceManager {
    public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    public static BufferedImage badTankL, badTankR, badTankU, badTankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    private static final ExecutorService soundPool = Executors.newFixedThreadPool(20);

    static {
        try {
            //加载主战坦克图片
            goodTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            //加载敌军坦克图片
            badTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            //加载子弹图片
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            //加载爆炸效果图片
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream(String.format("images/e%s.gif", i + 1)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playExplodeAudio() {
        soundPool.submit(() -> new Audio("audio/explode.wav").play());
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
