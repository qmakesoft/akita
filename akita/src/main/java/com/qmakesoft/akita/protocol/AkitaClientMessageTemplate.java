package com.qmakesoft.akita.protocol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
	
	@Autowired
	AkitaClient akitaClient;

	ExecutorService executorService;
	
	private Map<String,BlockingQueue<Protocol.AkitaMessage>> responseMap = new LinkedHashMap<>();
	
	void init(ChannelFuture channelFuture) {
		this.channelFuture = channelFuture;
		if(executorService == null) {
			executorService = Executors.newFixedThreadPool(configuration.getMaxConcurrentCount());
		}
	}
	
	private boolean isReady() {
		return channelFuture != null && channelFuture.channel().isActive() && executorService != null;
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
	
	public String sendAndReceive(Protocol.AkitaMessage akitaMessage) throws Exception {
		Callable<String> task = new Callable<String>() {
			@Override
			public String call() throws Exception {
				String messageId = akitaMessage.getMessageId();
				BlockingQueue<Protocol.AkitaMessage> serverMessageQueue = new LinkedBlockingQueue<Protocol.AkitaMessage>(1);
				try{
					responseMap.put(messageId, serverMessageQueue);
					channelFuture.channel().writeAndFlush(akitaMessage);
					AkitaMessage serverMessage = serverMessageQueue.poll(configuration.getRequestTimeout(),TimeUnit.SECONDS);
					return serverMessage.getMessage();
				}finally{
					responseMap.remove(messageId);
				}
			}
		};
		try {
			if(isReady()) {
				Future<String> future = executorService.submit(task);
				return future.get(configuration.getRequestTimeout(), TimeUnit.SECONDS);
			}else {
				retryConnect();
				return sendAndReceive(akitaMessage);
			}
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			throw e;
		}
	}
	
	private void retryConnect() throws TimeoutException {
		int i = 0;
		while(!isReady() && i < configuration.getRetryTime()) {
			try {
				akitaClient.syncConnect();
			}catch (Exception e) {
				//
			}
			if(isReady()) {
				return ;
			}
			i++;
			try {
				Thread.sleep(configuration.getRetryInterval());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		throw new TimeoutException("timeout");
	}
}
