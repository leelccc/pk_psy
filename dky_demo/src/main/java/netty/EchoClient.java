package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new StringDecoder());
                     ch.pipeline().addLast(new StringEncoder());
                     ch.pipeline().addLast(new EchoClientHandler());
                 }
             });

            ChannelFuture f = b.connect("localhost", 8080).sync();
            // 我如何再发送消息呢
            Thread.sleep(1000);
            f.channel().writeAndFlush("ffffff");
            Thread.sleep(1000);
            f.channel().writeAndFlush("fs");
            Thread.sleep(1000);
            f.channel().writeAndFlush("fffasdffff");
            Thread.sleep(1000);
            f.channel().writeAndFlush("ff22333ffff");
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}