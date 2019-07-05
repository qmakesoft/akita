package com.qmakesoft.akita.protocol;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix="akita.client")
public class AkitaClientConfiguration extends Configuration{
	
	private String host = "127.0.0.1";
	
	private int port = 1026111;

	private String password = "123456";
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	@ConditionalOnMissingBean
	@ConditionalOnBean(AkitaClient.class)
	@Bean
	public AkitaClientHandler akitaClientHandler(){
		return new AkitaClientHandler();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
