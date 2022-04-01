package framework.protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;
import framework.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: CH
 * @date: 2021-11-29
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 处理请求，返回结果
        try {
            ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
            Invocation invocation = (Invocation) ois.readObject();

            String interfaceName = invocation.getInterfaceName();
            Class<?> interfaceClass = LocalRegister.get(interfaceName);

            String methodName = invocation.getMethodName();
            Method method = interfaceClass.getMethod(methodName, invocation.getParamTypes());
            String result = (String) method.invoke(interfaceClass.newInstance(), invocation.getParams());

            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }

}
