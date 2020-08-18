package sean.yu.tank.abstractfactory;

import sean.yu.tank.Direction;
import sean.yu.tank.Group;
import sean.yu.tank.TankFrame;


/**
 * 定义游戏的抽象工厂，其中包含3个组件 tank, explode, bullet
 *
 * @author: Unuts
 * @create: 2020-08-13 21:38
 **/
public abstract class AbstractGameFactory {
    public abstract AbstractTank createTank(int x, int y, Direction dir, TankFrame tf, Group group);

    public abstract AbstractExplode createExplode(int x, int y, TankFrame tf);

    public abstract AbstractBullet createBullet(int x, int y, Direction dir, TankFrame tf, Group group);
}
