package framework.protocol.dubbo;

import framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author: CH
 * @date: 2021-12-06
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<String> {

    private Invocation invocation;
    private ChannelHandlerContext ctx;
    private String result;

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify();
    }

    @Override
    public synchronized String call() throws Exception {
        ctx.writeAndFlush(invocation);
        wait();
        return result;
    }

}
