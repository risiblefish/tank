package sean.yu.tank.cor;

import sean.yu.tank.GameObject;

/**
 * @author: Sean Yu
 * @create: 2020-09-07 22:10
 **/
public interface Collider {
    void collide(GameObject o1, GameObject o2);
}
