package sean.yu.tank.abstractfactory;

import sean.yu.tank.ResourceManager;
import sean.yu.tank.TankFrame;

import java.awt.*;

import static sean.yu.tank.ResourceManager.playExplodeAudio;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 21:25
 **/
public class RectExplode extends AbstractExplode {
    //public properties
    public static final int WIDTH = ResourceManager.explodes[0].getWidth();
    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        playExplodeAudio();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10*step, 10*step);
        step++;
        if(step >= 15) {
            tf.explodesList.remove(this);
        }
        g.setColor(c);
    }


}
