package sean.yu.tank.cor;

import sean.yu.tank.Bullet;
import sean.yu.tank.Explode;
import sean.yu.tank.GameObject;
import sean.yu.tank.Tank;

/**
 * @author: Sean Yu
 * @create: 2020-09-07 22:14
 **/
public class BulletTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            //如果为同阵营，则不产生伤害
            if (bullet.getGroup() == tank.getGroup()) {
                return;
            }
            //判断2个矩形是否相交
            if (bullet.getRect().intersects(tank.getRect())) {
                bullet.die();
                bullet.gm.add(new Explode(tank.getX(), tank.getY(), bullet.gm));
                tank.die();
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            collide(o2, o1);
        }
    }
}
