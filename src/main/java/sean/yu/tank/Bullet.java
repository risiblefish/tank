package sean.yu.tank;

import java.awt.*;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 21:25
 **/

public class Bullet {
    private static final int SPEED = 1;
    private static final int WIDTH = 5, HEIGHT = 5;
    private int x;
    private int y;
    private Direction dir;
    private TankFrame tf;

    public Bullet(int x, int y, Direction dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color og = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        move();
        g.setColor(og);
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
        updateLifeState();
    }

    private void updateLifeState() {
        if (isDead()) {
            System.out.println("bullet is dead");
            tf.bullets.remove(this);
        }
    }

    private boolean isDead() {
        System.out.println(String.format("x: %s y %s", x, y));
        boolean flag =  (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT);
        boolean flag1 = x < 0;
        boolean flag2 = y < 0;
        boolean flag3 = x > TankFrame.GAME_WIDTH;
        boolean flag4 = y > TankFrame.GAME_HEIGHT;
        System.out.println(String.format("f1: %s, f2 : %s, f3 : %s, f4 : %s, flag : %s"
                ,flag1,flag2,flag3,flag4,flag1||flag2||flag3||flag4));
        return flag;
    }
}
