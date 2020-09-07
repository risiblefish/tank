package sean.yu.tank;

import sean.yu.tank.manager.ResourceManager;

import java.awt.*;

import static sean.yu.tank.manager.ResourceManager.playExplodeAudio;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 21:25
 **/
public class Explode extends GameObject{
    //public properties
    public static final int WIDTH = ResourceManager.explodes[0].getWidth();
    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    //private properties
    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
        playExplodeAudio();
    }

    //methods
    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length) {
            gm.remove(this);
        }
    }


}
