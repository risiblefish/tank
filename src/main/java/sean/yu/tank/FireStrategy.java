package sean.yu.tank;

import sean.yu.tank.abstractfactory.AbstractTank;

public interface FireStrategy {
    void fire(AbstractTank tank);
}
