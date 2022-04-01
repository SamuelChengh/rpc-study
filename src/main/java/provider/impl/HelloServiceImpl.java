package provider.impl;

import provider.api.HelloService;

/**
 * @author: CH
 * @date: 2021-11-29
 */
public class HelloServiceImpl implements HelloService {

    public String sayHello(String userName) {
        return "hello " + userName;
    }

}
