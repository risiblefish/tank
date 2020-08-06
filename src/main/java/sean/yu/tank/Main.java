package sean.yu.tank;

import static sean.yu.tank.Group.BAD;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 19:34
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        Integer tankInitialCount = Integer.parseInt((String)PropertyManager.get("tankInitialCount"));
        for (int i = 0; i < tankInitialCount; i++) {
            tf.tanks.add(new Tank(50 + i * 80, 200, Direction.DOWN, tf, BAD));
        }
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
