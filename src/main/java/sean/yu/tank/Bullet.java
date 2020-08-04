package sean.yu.tank;

import java.awt.*;

import static sean.yu.tank.Direction.DOWN;
import static sean.yu.tank.Group.BAD;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 21:25
 **/

public class Bullet {
    //public properties
    public static final int WIDTH = ResourceManager.bulletD.getWidth();
    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    //private final constants properties
    private static final int SPEED = 10;

    //private properties
    private int x;
    private int y;
    private Direction dir;
    private TankFrame tf;
    private boolean alive = true;
    private Group group;

    public Bullet(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    //getters and setters
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //methods
    public void paint(Graphics g) {
        //如果死了，就不绘制
        if(isDead()) {
            tf.bullets.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
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
        if(isDead()) {
            tf.bullets.remove(this);
        }
    }

    private boolean isDead() {
                //子弹超出边界
        return  (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
                //或者子弹与其他物体发生碰撞导致死亡
                || !alive;
    }

    /**
     * 碰撞检测，这里设置为不开启同阵营伤害
     * @param tank
     */
    public void collideWith(Tank tank) {
        //如果为同阵营，则不产生伤害
        if(this.group == tank.getGroup()) {
            return;
        }
        //TODO : 只用2个矩形来分别记录,而不是每次都new
        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        //判断2个矩形是否相交
        if (bulletRect.intersects(tankRect)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.alive = false;
    }
}
