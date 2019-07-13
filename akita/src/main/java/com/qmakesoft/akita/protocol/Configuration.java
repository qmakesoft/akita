package com.qmakesoft.akita.protocol;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="akita")
public class Configuration {

	private int requestTimeout = 30;
	
	private int retryTime = 3;
	
	private int retryInterval = 500;
	
	public int getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}

	public int getRetryTime() {
		return retryTime;
	}

	public void setRetryTime(int retryTime) {
		this.retryTime = retryTime;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}
	
	private int maxConcurrentCount = 20;
	
	public int getMaxConcurrentCount() {
		return maxConcurrentCount;
	}

	public void setMaxConcurrentCount(int maxConcurrentCount) {
		this.maxConcurrentCount = maxConcurrentCount;
	}
}
