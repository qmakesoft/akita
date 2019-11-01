package com.qmakesoft.akita.protocol;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
  * 服务端配置
 * @author Jerry.Zhao
 *
 */
@ConfigurationProperties(prefix="akita.server")
public class AkitaServerConfiguration extends Configuration{
	
	private int port = 5026;
	
	private int maxConcurrentCount = 20;
	
	private int requestTimeout = 30;
	
	private String password = "123456";

	@Override
	public int getRequestTimeout() {
		return requestTimeout;
	}
	
	@Override
	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int getMaxConcurrentCount() {
		return maxConcurrentCount;
	}

	@Override
	public void setMaxConcurrentCount(int maxConcurrentCount) {
		this.maxConcurrentCount = maxConcurrentCount;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@ConditionalOnMissingBean
	@ConditionalOnBean(AkitaServer.class)
	@Bean
	public AkitaServerHandler akitaServerHandler(){
		return new AkitaServerHandler();
	}
	
	@ConditionalOnMissingBean
	@ConditionalOnBean(AkitaServerHandler.class)
	@Bean
	public AkitaServerHeartbeatManager akitaServerHeartbeatManager(){
		return new AkitaServerHeartbeatManager();
	}
}
