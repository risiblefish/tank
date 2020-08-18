package sean.yu.tank.abstractfactory;

import sean.yu.tank.*;

/**
 * 默认的工厂
 */
public class DefaultFactory extends AbstractGameFactory {
    @Override
    public AbstractTank createTank(int x, int y, Direction dir, TankFrame tf, Group group) {
        return new Tank(x, y, dir, tf, group);
    }

    @Override
    public AbstractExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public AbstractBullet createBullet(int x, int y, Direction dir, TankFrame tf, Group group) {
        return new Bullet(x,y,dir,tf,group);
    }
}

