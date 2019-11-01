package com.qmakesoft.akita.protocol;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
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

import com.qmakesoft.akita.protocol.Protocol.AkitaMessage;

import io.netty.channel.ChannelFuture;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public class AkitaClientMessageTemplate {
	
	ChannelFuture channelFuture;
	
	@Autowired
	Configuration configuration;
	
	@Autowired
	AkitaClient akitaClient;

	private Map<String,BlockingQueue<Protocol.AkitaMessage>> responseMap = new LinkedHashMap<>();
	
	/**
	 * 初始化线程池
	 */
	final ExecutorService executorService = new ThreadPoolExecutor(1, 1,
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
        	return new Thread(r, "AkitaClientMessage-Thread-" + mThreadNum.getAndIncrement());
        }
    }
	
	void init(ChannelFuture channelFuture) {
		this.channelFuture = channelFuture;
	}
	
	private boolean isReady() {
		return channelFuture != null && channelFuture.channel().isActive();
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
