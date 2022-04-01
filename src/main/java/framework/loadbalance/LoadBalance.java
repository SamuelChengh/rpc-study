package framework.loadbalance;

import framework.URL;

import java.util.List;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public interface LoadBalance {

    URL get(List<URL> urls);

}
