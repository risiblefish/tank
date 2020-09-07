package sean.yu.tank;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 19:34
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
