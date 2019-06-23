package com.qmakesoft.akita.protocol;


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

public class AkitaClient {
	
	@Autowired
	AkitaClientConfiguration akitaClientConfiguration;

	@Autowired
	AkitaClientHandler akitaClientHandler;
	
	@Autowired
	AkitaClientMessageTemplate messageTemplate;
	
	public AkitaClient(){
		Thread akitaClientThread = new Thread(new Runnable() {
			@Override
			public void run() {
				EventLoopGroup group = new NioEventLoopGroup();
				try {
					Bootstrap b = new Bootstrap();
					b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
							.handler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel ch) throws Exception {
									ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
							        //这里是收到服务端发过来的消息,所以是对服务端的response解码
							        ch.pipeline().addLast(new ProtobufDecoder(Protocol.AkitaMessage.getDefaultInstance()));
							        // encoded
							        ch.pipeline().addLast(new LengthFieldPrepender(4));
							        ch.pipeline().addLast(new ProtobufEncoder());
									ch.pipeline().addLast(akitaClientHandler);
								}
							});
					ChannelFuture future = b.connect(akitaClientConfiguration.getHost(), akitaClientConfiguration.getPort()).sync();
					messageTemplate.init(future);
					future.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					group.shutdownGracefully();
				}
			}
		});
		akitaClientThread.start();
	}

}