package com.qmakesoft.akita.protocol;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public class EnableAkitaServerConfiguration {

	
	@ConditionalOnMissingClass("com.qmakesoft.akita.protocol.AkitaServerCommandHandler")
	@Bean
	public AkitaServerCommandHandler akitaServerCommandHandler() {
		return new AkitaServerCommandHandler();
	}
	
}
