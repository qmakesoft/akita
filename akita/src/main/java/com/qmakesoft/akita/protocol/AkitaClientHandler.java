package com.qmakesoft.akita.protocol;


import org.springframework.beans.factory.annotation.Autowired;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class AkitaClientHandler extends SimpleChannelInboundHandler<Protocol.AkitaMessage> {

	@Autowired
	AkitaClient akitaClient;

	@Autowired
	AkitaClientConfiguration akitaClientConfiguration;
	
	@Autowired
	AkitaClientMessageTemplate akitaMessageTemplate;
	
	protected void channelRead0(ChannelHandlerContext ctx, Protocol.AkitaMessage akitaMessage) throws Exception {
		//服务器心跳检查，回复服务端心跳
		if(akitaMessage.getCode() == AkitaMessageCodeConstant.HEARTBEAT){
			Protocol.AkitaMessage heartbeat = Protocol.AkitaMessage.newBuilder().setCode(AkitaMessageCodeConstant.HEARTBEAT).setMessageId("0").setMessage("0").build();
			ctx.channel().writeAndFlush(heartbeat);
			return;
		}

		//普通消息，服务器返回成功后把消息添加到等待的队列中
		if(akitaMessage.getCode() == AkitaMessageCodeConstant.RESPONSE_SUCCESS){
			akitaMessageTemplate.putMessage(akitaMessage);
			return;
		}
		
		//认证失败断开连接
		if(akitaMessage.getCode() == AkitaMessageCodeConstant.RESPONSE_UNAUTHENTICATED){
			ctx.close();
			return;
		}
		
		//认证成功
		if(akitaMessage.getCode() == AkitaMessageCodeConstant.RESPONSE_AUTHENTICATED){
			return;
		}
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Protocol.AkitaMessage akitaMessage = Protocol.AkitaMessage.newBuilder()
			.setCode(AkitaMessageCodeConstant.REQUEST_AUTHENTICATION)
			.setMessageId("0")
			.setMessage(akitaClientConfiguration.getPassword())
			.build();
		ctx.channel().writeAndFlush(akitaMessage);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

}
