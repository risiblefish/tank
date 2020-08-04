package sean.yu.tank;

import java.awt.*;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 20:49
 **/

public class Tank {
    private int x;
    private int y;
    private static final int SPEED = 5;
    private Direction dir;
    boolean moving = false;
    private TankFrame tf = null;

    public Tank(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color og = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        move();
        g.setColor(og);
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
        this.tf.bullets.add(new Bullet(x, y, dir, this.tf));
    }
}
