package com.qmakesoft.akita.protocol;

import java.util.LinkedHashMap;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;

public class AkitaServerHeartbeatManager {

	Map<ChannelHandlerContext,Long> clients = new LinkedHashMap<>();

	public void addClient(ChannelHandlerContext ctx) {
		clients.put(ctx,System.currentTimeMillis());
	}

	public void heartbeat(ChannelHandlerContext ctx) {
		clients.remove(ctx);
		clients.put(ctx,System.currentTimeMillis());
	}

	
}
