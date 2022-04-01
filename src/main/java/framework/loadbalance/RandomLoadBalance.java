package framework.loadbalance;

import framework.URL;

import java.util.List;
import java.util.Random;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public class RandomLoadBalance implements LoadBalance {

    @Override
    public URL get(List<URL> urls) {
        Random random = new Random();
        int index = random.nextInt(urls.size());
        return urls.get(index);
    }

}
