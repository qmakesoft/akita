package com.qmakesoft.akita.protocol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;

import com.qmakesoft.akita.protocol.Protocol.AkitaMessage;

import io.netty.channel.ChannelFuture;

public class AkitaClientMessageTemplate {
	
	ChannelFuture channelFuture;
	
	@Autowired
	Configuration configuration;

	ExecutorService executorService;
	
	private Map<String,BlockingQueue<Protocol.AkitaMessage>> responseMap = new LinkedHashMap<>();
	
	void init(ChannelFuture channelFuture) {
		this.channelFuture = channelFuture;
		executorService = Executors.newFixedThreadPool(
				configuration.getMaxConcurrentCount());
	}

	public void putMessage(Protocol.AkitaMessage akitaMessage){
		String messageId = akitaMessage.getMessageId();
		BlockingQueue<Protocol.AkitaMessage> serverMessageQueue = responseMap.get(messageId);
		if(serverMessageQueue == null){
			return;
		}
		try {
			serverMessageQueue.put(akitaMessage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String sendAndReceive(Protocol.AkitaMessage akitaMessage){
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				String messageId = akitaMessage.getMessageId();
				channelFuture.channel().writeAndFlush(akitaMessage);
				BlockingQueue<Protocol.AkitaMessage> serverMessageQueue = new LinkedBlockingQueue<Protocol.AkitaMessage>(1);
				responseMap.put(messageId, serverMessageQueue);
				try{
					AkitaMessage serverMessage = serverMessageQueue.take();
					return serverMessage.getMessage();
				}finally{
					responseMap.remove(messageId);
				}
			}
		});
		executorService.execute(task);
		try {
			String result = task.get(configuration.getRequestTimeout(), TimeUnit.SECONDS);
			return result;
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
