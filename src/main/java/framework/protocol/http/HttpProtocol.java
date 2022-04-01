package framework.protocol.http;

import framework.Invocation;
import framework.protocol.Protocol;
import framework.URL;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostname(), url.getPort(), invocation);
    }

}
