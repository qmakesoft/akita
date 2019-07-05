package com.qmakesoft.akita.command.deploy;

import java.util.Map;

import com.qmakesoft.akita.command.AbstractCommand;

public class DeployCommand extends AbstractCommand{
	
	String processDefinitionCode;
	
	String processDefinitionName;
	
	String processDefinitionJson;
	
	@Override
	protected Object execute() {
		return null;
	}
	@Override
	protected void initCommand(Map<String, Object> message) {
		processDefinitionCode = (String)message.get("processDefinitionCode");
		processDefinitionName = (String)message.get("processDefinitionName");
		processDefinitionJson = (String)message.get("processDefinitionJson");
	}
	
}
