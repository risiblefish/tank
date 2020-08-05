package sean.yu.tank;

import java.awt.*;

import static sean.yu.tank.ResourceManager.playExplodeAudio;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 21:25
 **/
public class Explode {
    //public properties
    public static final int WIDTH = ResourceManager.explodes[0].getWidth();
    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    //private properties
    private int x;
    private int y;
    private TankFrame tf;
    private boolean alive = true;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        playExplodeAudio();
    }

    //methods
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length) {
            tf.explodesList.remove(this);
        }
    }


}
