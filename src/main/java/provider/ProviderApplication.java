package provider;

import framework.URL;
import framework.protocol.Protocol;
import framework.protocol.ProtocolFactory;
import framework.register.LocalRegister;
import framework.register.RemoteRegister;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;

/**
 * @author: CH
 * @date: 2021-11-29
 */
public class ProviderApplication {

    public static void main(String[] args) {

        // 1、本地注册
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        // 2、远程注册
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(HelloService.class.getName(), url);

        // 3、启动Tomcat或Netty
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);

    }

}
