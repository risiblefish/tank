package sean.yu.tank.cor;

import sean.yu.tank.*;

/**
 * 设置坦克之间的碰撞器，如果2个敌方坦克相撞，则各自回退到相撞前的位置上去
 * @author: Sean Yu
 * @create: 2020-09-07 22:14
 **/
public class TankTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.setPosition(t1.getOldX(), t1.getOldY());
                t2.setPosition(t2.getOldX(), t2.getOldY());
            }
        }
    }
}
