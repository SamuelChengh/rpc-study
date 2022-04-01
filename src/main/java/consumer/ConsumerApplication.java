package consumer;

import framework.proxy.ProxyFactory;
import provider.api.HelloService;

/**
 * @author: CH
 * @date: 2021-12-06
 */
public class ConsumerApplication {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("CH");
        System.out.println(result);

    }

}
