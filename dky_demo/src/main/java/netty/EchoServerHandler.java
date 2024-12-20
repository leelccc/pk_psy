package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("第一步");
        if (msg instanceof String) {
            ByteBuf in = Unpooled.wrappedBuffer(((String) msg).getBytes());
            System.out.println("Server received: " + in.toString(io.netty.util.CharsetUtil.UTF_8));
            ctx.write(in); // 将消息写入输出缓冲区，但不实际发送出去
        } else {
            System.out.println("Server received unsupported type: " + msg.getClass());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("第二步");
        ctx.flush(); // 刷新输出缓冲区，将消息发送出去
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("第三步");
        cause.printStackTrace();
        ctx.close();
    }
}