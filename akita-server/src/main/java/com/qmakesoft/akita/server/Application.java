package com.qmakesoft.akita.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.qmakesoft.akita.command.CommandFactory;
import com.qmakesoft.akita.protocol.EnableAkitaServer;

@SpringBootApplication
@EnableAkitaServer
public class Application {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
	}
	
	@Bean
	public CommandFactory commandFactory() {
		return new CommandFactory();
	}
}
