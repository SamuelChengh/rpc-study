package framework.register;

import framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public class RemoteRegister {

    private static Map<String, List<URL>> map = new ConcurrentHashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = map.get(interfaceName);
        if (urls == null || urls.size() == 0) {
            urls = new ArrayList<>();
        }
        urls.add(url);
        map.put(interfaceName, urls);

        // 利用共享文件 解决跨进程问题
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        return getFile().get(interfaceName);
    }

    private static void saveFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\temp.txt"))) {
            oos.writeObject(map);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\temp.txt"))) {
            return (Map<String, List<URL>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
