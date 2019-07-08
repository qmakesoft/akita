package com.qmakesoft.akita.protocol;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AkitaServerCommandAware implements ApplicationContextAware{

	public Map<Integer,AbstractCommand<Object>> commandMap = new HashMap<Integer, AbstractCommand<Object>>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, AbstractCommand> map = applicationContext.getBeansOfType(AbstractCommand.class);
		Set<Entry<String, AbstractCommand>> commandEntrySet = map.entrySet();
		for(Entry<String, AbstractCommand> commandEntry : commandEntrySet) {
			AbstractCommand<Object> command = commandEntry.getValue();
			commandMap.put(command.code(), command);
		}
	}

}
