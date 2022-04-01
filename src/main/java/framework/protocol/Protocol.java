package framework.protocol;

import framework.Invocation;
import framework.URL;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public interface Protocol {

    void start(URL url);

    String send(URL url, Invocation invocation);

}
