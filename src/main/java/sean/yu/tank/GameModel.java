package sean.yu.tank;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import static sean.yu.tank.Group.BAD;
import static sean.yu.tank.Group.GOOD;

/**
 * @author: Sean Yu
 * @create: 2020-09-06 09:28
 **/
public class GameModel {
    Tank myTank;
    LinkedList<Bullet> bullets;
    LinkedList<Tank> tanks;
    LinkedList<Explode> explodesList;

    public GameModel() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        bullets = new LinkedList();
        tanks = new LinkedList();
        explodesList = new LinkedList();
        //初始化主战坦克
        myTank = new Tank(200, 400, Direction.DOWN, this, GOOD);
        //初始化敌方坦克
        Integer tankInitialCount = Integer.parseInt((String)PropertyManager.get("tankInitialCount"));
        for (int i = 0; i < tankInitialCount; i++) {
            tanks.add(new Tank(50 + i * 80, 200, Direction.DOWN, this, BAD));
        }
    }
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

    public Tank getMyTank() {
        return myTank;
    }
}
