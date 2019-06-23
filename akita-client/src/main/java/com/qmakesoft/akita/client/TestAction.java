package com.qmakesoft.akita.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qmakesoft.akita.protocol.AkitaClientMessageTemplate;
import com.qmakesoft.akita.protocol.AkitaMessageCodeConstant;
import com.qmakesoft.akita.protocol.Protocol;


@RestController
public class TestAction {

	@Autowired
	AkitaClientMessageTemplate messageTemplate;
	
	@RequestMapping(value="hello")
	public String a(){
		Protocol.AkitaMessage message = Protocol.AkitaMessage.newBuilder()
				.setCode(AkitaMessageCodeConstant.REQUEST_MESSAGE)
				.setMessageId(UUID.randomUUID().toString())
				.setMessage("hello Jerry!")
				.build();
		return messageTemplate.sendAndReceive(message);
	}
	
}
