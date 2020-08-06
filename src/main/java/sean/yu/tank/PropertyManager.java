package sean.yu.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-06 08:04
 **/

public class PropertyManager {
    private static final Properties PROPS = new Properties();

    static {
        try {
            PROPS.load(PropertyManager.class.getClassLoader().getResourceAsStream("config/game_init.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        return key == null ? null : PROPS.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PROPS.get("tankInitialCount"));
    }
}
