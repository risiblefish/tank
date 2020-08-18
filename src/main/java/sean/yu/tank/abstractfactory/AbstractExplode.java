package sean.yu.tank.abstractfactory;

import sean.yu.tank.TankFrame;

import java.awt.*;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-13 21:39
 **/

public abstract class AbstractExplode {
    //properties
    public int x;
    public int y;
    public TankFrame tf;
    public int step = 0;

    public abstract void paint(Graphics g);
}
