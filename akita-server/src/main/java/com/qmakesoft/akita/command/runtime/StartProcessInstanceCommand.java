package com.qmakesoft.akita.command.runtime;

import java.util.UUID;

import com.qmakesoft.akita.command.AbstractCommand;
import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.ProcessDefinition;
import com.qmakesoft.akita.core.ProcessDefinitionManager;

public class StartProcessInstanceCommand extends AbstractCommand{
	
	String processDefinitionCode;
	
	String version;
	
	String operator;
	
	String businessForm;
	
	String businessKey;
	
	String businessType;
	
	String comment;
	
	@Override
	public void execute() {
		ProcessDefinition processDefinition = ProcessDefinitionManager.getProcessDefinition(processDefinitionCode);
		String processInstanceId = UUID.randomUUID().toString();
		processDefinition.nodeManager().startNode().launch(
			new CommandContext()
			.processInstanceId(processInstanceId)
			.processDefinitionCode(processDefinitionCode)
			.version(version)
			.operator(operator)
			.businessForm(businessForm)
			.businessKey(businessKey)
			.businessType(businessType)
			.comment(comment)
		);
	}
	
}
