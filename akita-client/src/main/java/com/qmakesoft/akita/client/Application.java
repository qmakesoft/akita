package com.qmakesoft.akita.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.qmakesoft.akita.protocol.EnableAkitaClient;

@SpringBootApplication
@EnableAkitaClient
public class Application {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).run(args);
	}
	
}
