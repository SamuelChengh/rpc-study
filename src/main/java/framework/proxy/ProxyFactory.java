package framework.proxy;

import framework.Invocation;
import framework.URL;
import framework.loadbalance.LoadBalance;
import framework.loadbalance.RandomLoadBalance;
import framework.protocol.Protocol;
import framework.protocol.ProtocolFactory;
import framework.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author: CH
 * @date: 2021-12-07
 */
public class ProxyFactory {

    public static <T> T getProxy(Class<?> interfaceClass) {

        Object object = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    Protocol protocol = ProtocolFactory.getProtocol();

                    // 负载均衡
                    List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                    LoadBalance loadBalance = new RandomLoadBalance();
                    URL url = loadBalance.get(urls);

                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                    return protocol.send(url, invocation);
                } catch (Exception e) {
                    // 服务容错
                    e.printStackTrace();
                }
                return null;
            }
        });

        return (T) object;
    }

}
