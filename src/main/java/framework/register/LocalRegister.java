package framework.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: CH
 * @date: 2021-12-06
 */
public class LocalRegister {

    private static Map<String, Class<?>> map = new ConcurrentHashMap<>();

    public static Class<?> get(String interfaceName) {
        return map.get(interfaceName);
    }

    public static void register(String interfaceName, Class<?> clazz) {
        map.put(interfaceName, clazz);
    }

}
