package sean.yu.tank;

import sean.yu.tank.abstractfactory.AbstractTank;

import static sean.yu.tank.Direction.*;
import static sean.yu.tank.Group.GOOD;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-12 07:17
 **/
public class FourDirectionFireStrategy implements FireStrategy{
    @Override
    public void fire(AbstractTank t) {
        //计算子弹的初始位置
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bx, by, UP, t.tf, t.group);
        new Bullet(bx, by, DOWN, t.tf, t.group);
        new Bullet(bx, by, LEFT, t.tf, t.group);
        new Bullet(bx, by, RIGHT, t.tf, t.group);
        if(t.group == GOOD) {
            ResourceManager.playFireAudio();
        }
    }
}
