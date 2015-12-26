package net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.CharsetUtil;

public class NettyEchoServer {
	private int port;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

    public NettyEchoServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(
                    		 new ReadTimeoutHandler(30),
                    		 new LineBasedFrameDecoder(64),
                    		 new StringDecoder(CharsetUtil.ISO_8859_1),
                    		 new EchoServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)
             .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new NettyEchoServer(port).run();
    }
/*
 * Be aware that messages are not released after the {@link #channelRead(ChannelHandlerContext, Object)}
 * method returns automatically. If you are looking for a {@link ChannelInboundHandler} implementation that
 * releases the received messages automatically, please see {@link SimpleChannelInboundHandler}.
 */
	private class EchoServerHandler extends ChannelInboundHandlerAdapter {

	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    	String text = (String)msg;
	    	if("exit".equalsIgnoreCase(text)){
	    		text=text+"!";
	    	}
	    	text=text+"\n";
	    	ByteBuf encoded = ctx.alloc().buffer(text.length());
	    	encoded.writeBytes(text.getBytes(CharsetUtil.ISO_8859_1));
	    	// No need to release this ByteBuf cause we don't know when it would be sent
	    	// Netty will do it for us
	    	ctx.writeAndFlush(encoded);
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        // Close the connection when an exception is raised.
	    	if(cause instanceof TimeoutException){
	    		System.out.println("client idle for 30 seconds..disconnect.");
	    	}else{
	    		cause.printStackTrace();
	    	}
	        ctx.close();
	    }
	}
}
