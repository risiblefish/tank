package sean.yu.tank.abstractfactory;


import sean.yu.tank.Direction;
import sean.yu.tank.Group;
import sean.yu.tank.TankFrame;

import java.awt.*;

/**
 * @author: Unuts
 * @create: 2020-08-13 21:39
 **/
public abstract class AbstractBullet {

    // properties
    public int x;
    public int y;
    public Direction dir;
    public TankFrame tf;
    public boolean alive = true;
    public Group group;
    public Rectangle rect = new Rectangle();


    public abstract void paint(Graphics g);
    public abstract void collideWith(AbstractTank tank);
}
