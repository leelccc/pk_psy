package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        // 创建两个EventLoopGroup，bossGroup用于接受新的连接，workerGroup用于处理已经被接受的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // 创建服务器引导对象
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel作为服务器的通道实现
             .childHandler(new ChannelInitializer<SocketChannel>() { // 创建通道初始化对象
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     // 向管道加入处理器
                     ch.pipeline().addLast(new StringDecoder()); // 解码接收到的字节到字符串
                     ch.pipeline().addLast(new StringEncoder()); // 编码要发送的字符串到字节
                     ch.pipeline().addLast(new EchoServerHandler()); // 添加EchoServerHandler
                 }
             });

            ChannelFuture f = b.bind(8080).sync(); // 绑定服务器端口并同步等待直到绑定完成
            f.channel().closeFuture().sync(); // 等待服务器通道关闭
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}