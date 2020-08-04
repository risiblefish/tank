package sean.yu.tank;

import java.awt.*;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 20:49
 **/

public class Tank {
    //public properties
    public static final int WIDTH = ResourceManager.tankD.getWidth();
    public static final int HEIGHT = ResourceManager.tankD.getHeight();

    private int x;
    private int y;
    private static final int SPEED = 5;
    private Direction dir;
    boolean moving = false;
    private TankFrame tf;

    public Tank(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD, x, y, null);
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
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    //思考： 这里如何把frame的子弹传给tank对象？
    public void fire() {
        //计算子弹的初始位置
        int bx = x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        this.tf.bullets.add(new Bullet(bx, by, dir, this.tf));
    }
}
