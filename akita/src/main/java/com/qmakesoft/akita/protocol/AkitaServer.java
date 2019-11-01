package com.qmakesoft.akita.protocol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
  *  服务端
 * @author Jerry.Zhao
 *
 */
public class AkitaServer {

	@Autowired
	AkitaServerConfiguration akitaServerConfiguration;

	@Autowired
	AkitaServerHandler akitaServerHandler;

	/**
	 * 初始化线程池
	 */
	ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),new NameTreadFactory());
	
	/**
	 *  自定义名称的线程工厂
	 * @author Jerry.Zhao
	 *
	 */
	static class NameTreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
        	return new Thread(r, "AkitaClient-Thread-" + mThreadNum.getAndIncrement());
        }
    }
	
	public AkitaServer() {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
							.childHandler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel ch) throws Exception {
									// decoder
									ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
									// 解码客户端发过来的消息
									ch.pipeline()
											.addLast(new ProtobufDecoder(Protocol.AkitaMessage.getDefaultInstance()));
									// encoded
									ch.pipeline().addLast(new LengthFieldPrepender(4));
									ch.pipeline().addLast(new ProtobufEncoder());

									ch.pipeline().addLast(akitaServerHandler);
								}
							}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
					ChannelFuture f = b.bind(akitaServerConfiguration.getPort()).sync(); // (7)
					f.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					workerGroup.shutdownGracefully();
					bossGroup.shutdownGracefully();
				}
			}
		});
	}

}
