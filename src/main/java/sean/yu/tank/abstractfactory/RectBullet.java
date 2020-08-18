package sean.yu.tank.abstractfactory;

import sean.yu.tank.Direction;
import sean.yu.tank.Explode;
import sean.yu.tank.Group;
import sean.yu.tank.TankFrame;

import java.awt.*;

import static sean.yu.tank.ResourceManager.bulletD;
import static sean.yu.tank.TankFrame.GAME_HEIGHT;
import static sean.yu.tank.TankFrame.GAME_WIDTH;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 21:25
 **/

public class RectBullet extends AbstractBullet {
    //public properties
    public static final int WIDTH = bulletD.getWidth();
    public static final int HEIGHT = bulletD.getHeight();

    //private final constants properties
    private static final int SPEED = 10;

    public RectBullet(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        this.tf.bullets.add(this);
    }


    //methods
    @Override
    public void paint(Graphics g) {
        //如果死了，就不绘制
        if(isDead()) {
            tf.bullets.remove(this);
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(c);

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

        //每次移动之后更新子弹矩形（用于碰撞检测）
        updateBulletRect();

        if(isDead()) {
            tf.bullets.remove(this);
        }
    }

    private void updateBulletRect(){
        rect.x = this.x;
        rect.y = this.y;
    }

    private boolean isDead() {
                //子弹超出边界
        return  (x < 0 || y < 0 || x > GAME_WIDTH || y > GAME_HEIGHT)
                //或者子弹与其他物体发生碰撞导致死亡
                || !alive;
    }

    /**
     * 碰撞检测，这里设置为不开启同阵营伤害
     * @param tank
     */
    @Override
    public void collideWith(AbstractTank tank) {
        //如果为同阵营，则不产生伤害
        if(this.group == tank.group) {
            return;
        }
        //判断2个矩形是否相交
        if (this.rect.intersects(tank.rect)) {
            this.die();
            tf.explodesList.add(new RectExplode(x,y,tf));
            tank.die();
        }
    }
    private void die() {
        this.alive = false;
    }
}
