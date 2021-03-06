package framework.protocol.dubbo;

import framework.Invocation;
import framework.protocol.Protocol;
import framework.URL;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new NettyClient().send(url.getHostname(), url.getPort(), invocation);
    }

}
