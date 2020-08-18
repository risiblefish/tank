package sean.yu.tank.abstractfactory;

import sean.yu.tank.Direction;
import sean.yu.tank.FireStrategy;
import sean.yu.tank.Group;
import sean.yu.tank.TankFrame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-13 21:39
 **/

public abstract class AbstractTank {

    //properties
    public int x;
    public int y;
    public boolean alive = true;
    public Direction dir;
    public boolean moving = true;
    public TankFrame tf;
    public Group group;
    //用于控制绘制坦克图片的替换，当达到CHANGE_NUM时，就替换成另一张，从而让坦克的灯"闪"起来
    public int changeCount = 0;
    public Rectangle rect = new Rectangle();
    public FireStrategy fs;

    public abstract void paint(Graphics g);

    public abstract void die();

    public abstract void fire();
}
