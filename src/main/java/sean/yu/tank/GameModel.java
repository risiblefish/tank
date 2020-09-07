package sean.yu.tank;

import sean.yu.tank.cor.ColliderChain;
import sean.yu.tank.manager.PropertyManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static sean.yu.tank.Group.BAD;
import static sean.yu.tank.Group.GOOD;

/**
 * @author: Sean Yu
 * @create: 2020-09-06 09:28
 **/
public class GameModel {
    Tank myTank;

    private List<GameObject> gameObjects;
    private ColliderChain colliderChain = new ColliderChain();

    public GameModel() throws Exception {
        gameObjects = new ArrayList();

        //初始化主战坦克
        myTank = new Tank(200, 400, Direction.DOWN, this, GOOD);
        //初始化敌方坦克
        Integer tankInitialCount = Integer.parseInt((String) PropertyManager.get("tankInitialCount"));
        for (int i = 0; i < tankInitialCount; i++) {
            gameObjects.add(new Tank(50 + i * 80, 200, Direction.DOWN, this, BAD));
        }
    }

    public void add(GameObject go) {
        gameObjects.add(go);
    }

    public void remove(GameObject go) {
        gameObjects.remove(go);
    }

    public void paint(Graphics g){
        //绘制游戏信息
        Color c = g.getColor();
        g.setColor(Color.white);

        g.drawString(String.format("子弹数量: %s ", getBulletSize()), 10, 60);
        g.drawString(String.format("敌人数量: %s ", getEnemyTankSize()), 10, 80);
        g.drawString(String.format("爆炸数量: %s ", getExplodeSize()), 10, 100);
        g.setColor(c);
        //绘制主战坦克
        myTank.paint(g);

        //绘制所有GameObject
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        //对所有GameObject彼此进行碰撞检测,使用碰撞器责任链来完成
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }

    private int getBulletSize() {
        int count = 0;
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Bullet) {
                count++;
            }
        }
        return count;
    }

    private int getEnemyTankSize() {
        int count = 0;
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Tank) {
                count++;
            }
        }
        return count;
    }

    private int getExplodeSize() {
        int count = 0;
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) instanceof Explode) {
                count++;
            }
        }
        return count;
    }
}
