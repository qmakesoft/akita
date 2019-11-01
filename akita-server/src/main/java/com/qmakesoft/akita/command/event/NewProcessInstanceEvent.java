package com.qmakesoft.akita.command.event;

import java.util.logging.Logger;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.cache.SimpleCache;
import com.qmakesoft.akita.core.AbstractNode;
import com.qmakesoft.akita.model.ProcessInstance;

public class NewProcessInstanceEvent extends AbstractEvent{
	
	Logger logger = Logger.getLogger(NewProcessInstanceEvent.class.getName());
	
	public NewProcessInstanceEvent(AbstractNode node,CommandContext context) {
		super(node,context);
	}
	
	@Override
	public void doCache() {
		ProcessInstance processInstance = new ProcessInstance(); 
		processInstance.setBusinessForm(context.getBusinessForm());
		processInstance.setBusinessKey(context.getBusinessKey());
		processInstance.setBusinessType(context.getBusinessType());
		processInstance.setComment(context.getComment());
		processInstance.setProcessDefinitionCode(context.getProcessDefinitionCode());
		processInstance.setCreator(context.getOperator());
		processInstance.setProcessInstanceId(context.getProcessInstanceId());
		processInstance.setVersion(context.getVersion());
		logger.info("创建流程实例对象 ： " + JSONObject.wrap(processInstance));
		SimpleCache.putObjectInProcessInstance(processInstance.getProcessInstanceId(), processInstance);
	}
	
	@Override
	public void doDatabase() {
		
	}
	
}
