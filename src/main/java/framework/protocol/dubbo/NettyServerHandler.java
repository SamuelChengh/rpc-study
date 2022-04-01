package framework.protocol.dubbo;

import framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import framework.register.LocalRegister;

import java.lang.reflect.Method;

/**
 * @author: CH
 * @date: 2021-12-06
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        String interfaceName = invocation.getInterfaceName();
        Class<?> interfaceClass = LocalRegister.get(interfaceName);
        String methodName = invocation.getMethodName();
        Method method = interfaceClass.getMethod(methodName, invocation.getParamTypes());
        String result = (String) method.invoke(interfaceClass.newInstance(), invocation.getParams());
        ctx.writeAndFlush(result);
    }

}
