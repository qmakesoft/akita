package com.qmakesoft.akita.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qmakesoft.akita.command.runtime.StartProcessInstanceCommand;
import com.qmakesoft.akita.protocol.AkitaServerCommandHandler;

public class CommandFactory extends AkitaServerCommandHandler{

	public static Map<Integer,Class<? extends AbstractCommand>> commandMap = new HashMap<>();
	
	static {
		commandMap.put(new Integer(1001), StartProcessInstanceCommand.class);
	}
	
	@Override
	public Object execute(Integer code, String message) {
		
		Class<? extends AbstractCommand> commandClass = commandMap.get(code);
		try {
			AbstractCommand command = commandClass.newInstance();
			command.initCommand(JSONObject.parseObject(message));
			return command.execute();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} 
		return null;
	}

}
