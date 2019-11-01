package com.qmakesoft.akita.protocol;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
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
	
	ChannelFuture future = null;
	
	/**
	 * 单一线程池，如果服务断开，会每隔10秒重新连接
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
	
	public AkitaClient() throws InterruptedException, ExecutionException, TimeoutException{
		connect();
	}
	
	public void connect() {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						doConnect();
					}catch (Exception e) {
						//连接异常结束
					}
					try {
						Thread.sleep(10000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void doConnect() {
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
		}
	
	}

}