package sean.yu.tank.cor;

import sean.yu.tank.GameObject;
import sean.yu.tank.manager.PropertyManager;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Sean Yu
 * @create: 2020-09-08 06:27
 **/
public class ColliderChain {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() throws Exception {
        initColliders();
    }

    /**
     * 加载各种Collider对象
     * 方式：从配置文件读取要使用的碰撞器，然后初始化
     */
    private void initColliders() throws Exception {
        String colliderStr = (String) PropertyManager.get("colliders");
        String[] colliderArr = colliderStr.split(",");
        for (String colliderName : colliderArr) {
            Collider curr = (Collider) Class.forName(colliderName).getDeclaredConstructor().newInstance();
            colliders.add(curr);
        }
    }

    /**
     * 遍历每一个碰撞器，让2个gameobject进行碰撞检测
     *
     * @param o1
     * @param o2
     */
    public void collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            colliders.get(i).collide(o1, o2);
        }
    }
}
