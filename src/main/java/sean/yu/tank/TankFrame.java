package sean.yu.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import static sean.yu.tank.Direction.*;
import static sean.yu.tank.Group.GOOD;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 19:18
 **/
public class TankFrame extends Frame {

//    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    Tank myTank;
    LinkedList<Bullet> bullets;
    LinkedList<Tank> tanks;
    LinkedList<Explode> explodesList;


    public TankFrame() throws HeadlessException {
        bullets = new LinkedList();
        tanks = new LinkedList();
        explodesList = new LinkedList();
        myTank = new Tank(200, 400, Direction.DOWN, this, GOOD);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        addKeyListener(new TankKeyListener());
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    /**
     * 设置双缓冲防止屏幕刷新导致的闪烁
     * <p>
     * 闪烁的原因是：paint中的g在进行复杂计算时，跟不上肉眼的节奏
     * update方法会在系统调用paint方法之前被系统调用
     * 这里大致实现方式是： 先在update中画出与游戏窗口大小相同的图，用黑色填充，然后用update的g来paint坦克和子弹
     * 最后用drawImage将缓存中的图一次性画出
     * 即Paint是边画边填充，update是一次性画到缓存里，然后再从缓存中一次性画出来
     *
     * @param g //
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            this.offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //绘制游戏实时画面
    @Override
    public void paint(Graphics g) {
        //绘制游戏信息
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString(String.format("子弹数量: %s ", bullets.size()), 10, 60);
        g.drawString(String.format("敌人数量: %s ", tanks.size()), 10, 80);
        g.drawString(String.format("爆炸数量: %s ", tanks.size()), 10, 100);
        g.setColor(c);
        //绘制主战坦克
        myTank.paint(g);

        //绘制子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //绘制敌人
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        //每次绘制时对每颗子弹和坦克进行碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        //绘制爆炸
        for (int i = 0; i < explodesList.size(); i++) {
            explodesList.get(i).paint(g);
        }
    }

    /**
     * 处理对键盘的监听
     */
    class TankKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    System.out.println("按下了left");
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("按下了right");
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    System.out.println("按下了up");
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("按下了down");
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDirection();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    System.out.println("释放了left");
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("释放了right");
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    System.out.println("释放了up");
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("释放了down");
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDirection();
        }

        private void setMainTankDirection() {
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) {
                    myTank.setDir(LEFT);
                }
                if (bR) {
                    myTank.setDir(RIGHT);
                }
                if (bU) {
                    myTank.setDir(UP);
                }
                if (bD) {
                    myTank.setDir(DOWN);
                }
            }
        }
    }
}
