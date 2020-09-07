package sean.yu.tank;

import java.awt.*;

/**
 * @author: Sean Yu
 * @create: 2020-09-07 21:43
 **/
public abstract class GameObject {
    int x;
    int y;
    public GameModel gm;
    public abstract void paint(Graphics g);
}
