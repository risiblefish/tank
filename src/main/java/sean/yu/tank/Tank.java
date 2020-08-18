package sean.yu.tank;

import sean.yu.tank.abstractfactory.AbstractTank;

import java.awt.*;
import java.util.Random;

import static sean.yu.tank.Direction.*;
import static sean.yu.tank.Group.BAD;
import static sean.yu.tank.Group.GOOD;
import static sean.yu.tank.ResourceManager.*;
import static sean.yu.tank.TankFrame.GAME_HEIGHT;
import static sean.yu.tank.TankFrame.GAME_WIDTH;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 20:49
 **/
public class Tank extends AbstractTank {
    //public properties
    public static final int WIDTH = goodTankD.getWidth();
    public static final int HEIGHT = goodTankD.getHeight();

    //private final constants properties
    private static final int SPEED = 5;
    private static final Random RANDOM = new Random();
    private static final int CHANGE_NUM = 8;

    public Tank(int x, int y, Direction dir, TankFrame tf, Group group)  {
        this.x = x;
        super.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = group == BAD ? badTankD.getWidth() : goodTankD.getWidth();
        rect.height = group == BAD ? badTankD.getWidth() : badTankD.getHeight();
        String stategyName = group == GOOD ? (String)PropertyManager.get("goodFireFS") : (String)PropertyManager.get("badFireFS");
        try {
            fs = (FireStrategy) Class.forName(stategyName).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //methods
    @Override
    public void paint(Graphics g) {
        if (!alive) {
            tf.tanks.remove(this);
            return;
        }

        switch (dir) {
            case LEFT:
                g.drawImage(getTankImage(this, LEFT), x, y, null);
                break;
            case RIGHT:
                g.drawImage(getTankImage(this, RIGHT), x, y, null);
                break;
            case UP:
                g.drawImage(getTankImage(this, UP), x, y, null);
                break;
            case DOWN:
                g.drawImage(getTankImage(this, DOWN), x, y, null);
                break;
        }
        move();
        boundsCheck();
        updateTankRect();
    }

    private void updateTankRect(){
        rect.x = this.x;
        rect.y = this.y;
    }

    //注意，-2是为了让边界稍微留出些许像素显得更美观一点
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > GAME_WIDTH - Tank.WIDTH - 2) {
            x = GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y > GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }

    private Image getTankImage(Tank tank, Direction dir) {
        Group group = tank.group;
        if (group == GOOD) {
            switch (dir) {
                case LEFT:
                    return goodTankL;
                case RIGHT:
                    return goodTankR;
                case UP:
                    return goodTankU;
                case DOWN:
                    return goodTankD;
            }
        } else {
            switch (dir) {
                case LEFT:
                    if (changeCount == CHANGE_NUM) {
                        changeCount = 0;
                        return badTankL2;
                    } else {
                        changeCount++;
                        return badTankL;
                    }
                case RIGHT:
                    if (changeCount == CHANGE_NUM) {
                        changeCount = 0;
                        return badTankR2;
                    } else {
                        changeCount++;
                        return badTankR;
                    }
                case UP:
                    if (changeCount == CHANGE_NUM) {
                        changeCount = 0;
                        return badTankU2;
                    } else {
                        changeCount++;
                        return badTankU;
                    }
                case DOWN:
                    if (changeCount == CHANGE_NUM) {
                        changeCount = 0;
                        return badTankD2;
                    } else {
                        changeCount++;
                        return badTankD;
                    }
            }
        }
        return null;
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

        if (RANDOM.nextInt(1000) > 900) {
            //随机改变方向
            this.dir = Direction.values()[RANDOM.nextInt(4)];
        }
    }

    @Override
    public void fire() {
       fs.fire(this);
    }

    public void die() {
        this.alive = false;
    }
}
