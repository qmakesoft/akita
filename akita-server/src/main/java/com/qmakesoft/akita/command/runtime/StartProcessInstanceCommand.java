package com.qmakesoft.akita.command.runtime;

import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
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
	public Object execute() {
		ProcessDefinition processDefinition = ProcessDefinitionManager.getProcessDefinition(processDefinitionCode);
		String processInstanceId = UUID.randomUUID().toString();
		boolean isEnd = processDefinition.nodeManager().startNode().launch(
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

		//组装返回结果
		JSONObject result = new JSONObject();
		result.put("processInstanceId", processInstanceId);
		result.put("isEnd", isEnd);
		return result;
	}

	@Override
	protected void initCommand(Map<String, Object> message) {
		processDefinitionCode = (String)message.get("processDefinitionCode");
		version = (String)message.get("version");
		operator = (String)message.get("operator");
		businessForm = (String)message.get("businessForm");
		businessKey = (String)message.get("businessKey");
		businessType = (String)message.get("businessType");
		comment = (String)message.get("comment");
	}

	
}
