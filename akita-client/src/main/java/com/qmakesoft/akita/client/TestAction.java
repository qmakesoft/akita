package com.qmakesoft.akita.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qmakesoft.akita.protocol.AkitaClientMessageTemplate;
import com.qmakesoft.akita.protocol.Protocol;


@RestController
public class TestAction {

	@Autowired
	AkitaClientMessageTemplate messageTemplate;
	
	@RequestMapping(value="hello")
	public String a(){
		Protocol.AkitaMessage message = Protocol.AkitaMessage.newBuilder()
				.setCode(2001)
				.setMessageId(UUID.randomUUID().toString())
				.setMessage("{'processDefinitionCode' : 'processDefinitionCode111'}")
				.build();
		return messageTemplate.sendAndReceive(message);
	}
	
}
