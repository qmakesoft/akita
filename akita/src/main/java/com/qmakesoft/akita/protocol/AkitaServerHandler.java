package com.qmakesoft.akita.protocol;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class AkitaServerHandler extends SimpleChannelInboundHandler<Protocol.AkitaMessage> {
	
	@Autowired
	AkitaServerConfiguration akitaServerConfiguration;
	
	@Autowired
	AkitaServerHeartbeatManager akitaServerHeartbeatManager;
	
	@Autowired
	AkitaServerCommandHandler akitaServerCommandHandler;
	
	protected void channelRead0(ChannelHandlerContext ctx, Protocol.AkitaMessage message) throws Exception {
		//收到客户端的认证请求信息
		if(message.getCode() == AkitaMessageCodeConstant.REQUEST_AUTHENTICATION){
			boolean authenticated = authentication(ctx,message);
			if(authenticated){
				akitaServerHeartbeatManager.addClient(ctx);
			}
			return;
		}
		//收到客户端的请求信息
		if(message.getCode() == AkitaMessageCodeConstant.REQUEST_MESSAGE){
			Protocol.AkitaMessage response = null;
			response = Protocol.AkitaMessage.newBuilder()
					.setCode(AkitaMessageCodeConstant.RESPONSE_SUCCESS)
					.setMessageId(message.getMessageId())
					.setMessage(message.getMessageId() + " : Received client message success ." + message.getMessage())
					.build();
			ctx.writeAndFlush(response);
			return;
		}
		
		//收到客户端的心跳信息
		if(message.getCode() == AkitaMessageCodeConstant.HEARTBEAT){
			akitaServerHeartbeatManager.heartbeat(ctx);
			return;
		}
		
		//业务命令
		Object obj = akitaServerCommandHandler.execute(message.getCode(), message.getMessage());
		Protocol.AkitaMessage response = null;
		response = Protocol.AkitaMessage.newBuilder()
				.setCode(AkitaMessageCodeConstant.RESPONSE_SUCCESS)
				.setMessageId(message.getMessageId())
				.setMessage(JSON.toJSONString(obj))
				.build();
		ctx.writeAndFlush(response);
	}

	private boolean authentication(ChannelHandlerContext ctx,Protocol.AkitaMessage message) {
		if(message.getMessage() != null && message.getMessage().equals(akitaServerConfiguration.getPassword())){
			ctx.channel().writeAndFlush(Protocol.AkitaMessage.newBuilder()
					.setCode(AkitaMessageCodeConstant.RESPONSE_AUTHENTICATED)
					.setMessageId(message.getMessageId())
					.setMessage(message.getMessageId() + " : Received client message success ." + message.getMessage())
					.build());
			return true;
		}else{
			ctx.channel().writeAndFlush(Protocol.AkitaMessage.newBuilder()
					.setCode(AkitaMessageCodeConstant.RESPONSE_UNAUTHENTICATED)
					.setMessageId(message.getMessageId())
					.setMessage(message.getMessageId() + " : Received client message success ." + message.getMessage())
					.build());
			return false;
		}
	}
	
}
