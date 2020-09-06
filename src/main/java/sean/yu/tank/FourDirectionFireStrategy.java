package sean.yu.tank;

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
    public void fire(Tank t) {
        //计算子弹的初始位置
        int bx = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bx, by, UP, t.getGm(), t.getGroup());
        new Bullet(bx, by, DOWN, t.getGm(), t.getGroup());
        new Bullet(bx, by, LEFT, t.getGm(), t.getGroup());
        new Bullet(bx, by, RIGHT, t.getGm(), t.getGroup());
        if(t.getGroup() == GOOD) {
            ResourceManager.playFireAudio();
        }
    }
}
