package com.qmakesoft.akita.protocol;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="akita")
public class Configuration {

	private int requestTimeout = 30;
	
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
