package sean.yu.tank.abstractfactory;

import sean.yu.tank.Direction;
import sean.yu.tank.Group;
import sean.yu.tank.TankFrame;

/**
 * @author: Sean Yu
 * @create: 2020-08-18 05:17
 **/
public class RectFactory extends AbstractGameFactory {

    @Override
    public AbstractTank createTank(int x, int y, Direction dir, TankFrame tf, Group group) {
        return null;
    }

    @Override
    public AbstractExplode createExplode(int x, int y, TankFrame tf) {
        return null;
    }

    @Override
    public AbstractBullet createBullet(int x, int y, Direction dir, TankFrame tf, Group group) {
        return null;
    }
}
