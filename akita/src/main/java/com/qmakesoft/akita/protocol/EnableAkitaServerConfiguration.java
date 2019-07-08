package com.qmakesoft.akita.protocol;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

public class EnableAkitaServerConfiguration {

	
	@ConditionalOnMissingClass("com.qmakesoft.akita.protocol.AkitaServerCommandHandler")
	@Bean
	public AkitaServerCommandHandler AkitaServerCommandHandler() {
		return new AkitaServerCommandHandler();
	}
	
}
