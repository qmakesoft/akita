package com.qmakesoft.akita.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.qmakesoft.akita.protocol.EnableAkitaServer;

@SpringBootApplication
@EnableAkitaServer
public class Application {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
	}
	
}
