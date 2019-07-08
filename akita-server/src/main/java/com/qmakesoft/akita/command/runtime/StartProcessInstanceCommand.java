package com.qmakesoft.akita.command.runtime;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qmakesoft.akita.command.AkitaTransaction;
import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.runtime.StartProcessInstanceCommand.Params;
import com.qmakesoft.akita.core.ProcessDefinition;
import com.qmakesoft.akita.core.ProcessDefinitionManager;
import com.qmakesoft.akita.protocol.AbstractCommand;

@Component
public class StartProcessInstanceCommand extends AbstractCommand<Params> implements AkitaTransaction{
	
	@Override
	public Object execute(Params params) {
		ProcessDefinition processDefinition = ProcessDefinitionManager.getProcessDefinition(params.processDefinitionCode);
		String processInstanceId = UUID.randomUUID().toString();
		boolean isEnd = processDefinition.nodeManager().startNode()
				.launch(new CommandContext()
						.processInstanceId(processInstanceId)
						.processDefinitionCode(params.processDefinitionCode)
						.version(params.version)
						.operator(params.operator)
						.businessForm(params.businessForm)
						.businessKey(params.businessKey)
						.businessType(params.businessType)
						.comment(params.comment));
		
		// 组装返回结果
		JSONObject result = new JSONObject();
		result.put("processInstanceId", processInstanceId);
		result.put("isEnd", isEnd);
		return result;
	}

	@Override
	public int code() {
		return 2001;
	}

	public static class Params {

		String processDefinitionCode;
		String version;
		String operator;
		String businessForm;
		String businessKey;
		String businessType;
		String comment;

		public String getProcessDefinitionCode() {
			return processDefinitionCode;
		}

		public void setProcessDefinitionCode(String processDefinitionCode) {
			this.processDefinitionCode = processDefinitionCode;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public String getBusinessForm() {
			return businessForm;
		}

		public void setBusinessForm(String businessForm) {
			this.businessForm = businessForm;
		}

		public String getBusinessKey() {
			return businessKey;
		}

		public void setBusinessKey(String businessKey) {
			this.businessKey = businessKey;
		}

		public String getBusinessType() {
			return businessType;
		}

		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

	}

}
