package com.qmakesoft.akita.protocol;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
  *  通讯客户端
 * @author Jerry.Zhao
 *
 */
public class AkitaClient {
	
	@Autowired
	AkitaClientConfiguration akitaClientConfiguration;

	@Autowired
	AkitaClientHandler akitaClientHandler;
	
	@Autowired
	AkitaClientMessageTemplate messageTemplate;
	
	Thread akitaClientThread = null;
	
	ChannelFuture future = null;
	
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
	
	public AkitaClient(){
		doConnect();
	}
	
	public void syncConnect() throws InterruptedException, ExecutionException, TimeoutException {
		Callable<Void> syncConnectCallable = new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				System.out.println("=================================");
				doConnect();
				System.out.println("=================================");
				return null;
			}
		};
		Future<Void> connectFuture = executorService.submit(syncConnectCallable);
		connectFuture.get(10, TimeUnit.SECONDS);
	}
	
	public void doConnect() {
		if(akitaClientThread != null && akitaClientThread.isAlive()) {
			return ;
		}
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup group = null;
				try {
					group = new NioEventLoopGroup();
					Bootstrap b = new Bootstrap();
					b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
							.handler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel ch) throws Exception {
									ch.pipeline().addLast(new IdleStateHandler(5,0,0));
									ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
							        //这里是收到服务端发过来的消息,所以是对服务端的response解码
							        ch.pipeline().addLast(new ProtobufDecoder(Protocol.AkitaMessage.getDefaultInstance()));
							        // encoded
							        ch.pipeline().addLast(new LengthFieldPrepender(4));
							        ch.pipeline().addLast(new ProtobufEncoder());
									ch.pipeline().addLast(akitaClientHandler);
								}
							});
					future = b.connect(akitaClientConfiguration.getHost(), akitaClientConfiguration.getPort()).sync();
					messageTemplate.init(future);
					future.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					//服务器连接失败
					e.printStackTrace();
				} finally {
					group.shutdownGracefully();
					System.out.println("============shutdown============");
				}
			}
		});
	}

}