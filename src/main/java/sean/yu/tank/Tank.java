package sean.yu.tank;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static sean.yu.tank.Group.BAD;
import static sean.yu.tank.Group.GOOD;
import static sean.yu.tank.ResourceManager.*;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 20:49
 **/
public class Tank {
    //public properties
    public static final int WIDTH = goodTankD.getWidth();
    public static final int HEIGHT = goodTankD.getHeight();

    //private final constants properties
    private static final int SPEED = 1;
    private static final Random RANDOM = new Random();

    //private properties
    private int x;
    private int y;
    private boolean alive = true;
    private Direction dir;
    private boolean moving = true;
    private TankFrame tf;
    private Group group;

    public Tank(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    //getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    //methods
    public void paint(Graphics g) {
        if (!alive) {
            tf.tanks.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group ==  GOOD? goodTankL : badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group ==  GOOD? goodTankR : badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group ==  GOOD? goodTankU : badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group ==  GOOD? goodTankD : badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        if (this.group == BAD) {
            performRobotAction();
        }
    }

    private void performRobotAction() {
        //随机发射子弹
        if (RANDOM.nextInt(100) > 95) {
            this.fire();
        }

        if (RANDOM.nextInt(10000) > 9900) {
            //随机改变方向
            this.setDir(Direction.values()[RANDOM.nextInt(4)]);
        }
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        //计算子弹的初始位置
        int bx = x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        this.tf.bullets.add(new Bullet(bx, by, dir, this.tf, this.getGroup()));
    }

    public void die() {
        this.alive = false;
    }
}
