package com.qmakesoft.akita.command.deploy;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qmakesoft.akita.command.deploy.DeployCommand.Params;
import com.qmakesoft.akita.core.parse.ProcessDefinitionHelper;
import com.qmakesoft.akita.protocol.AbstractCommand;
import com.qmakesoft.akita.protocol.transaction.AkitaTransaction;

@Component
public class DeployCommand extends AbstractCommand<Params> implements AkitaTransaction {
	
	@Override
	public Object execute(Params params) {
		String processDefinitionVersion = UUID.randomUUID().toString();
		ProcessDefinitionHelper.parse(params.processDefinitionJson);
		//TODO 插入数据到数据库
		JSONObject json = new JSONObject();
		json.put("processDefinitionCode", params.processDefinitionCode);
		json.put("processDefinitionVersion", processDefinitionVersion);
		return json;
	}

	@Override
	public int code() {
		return 1001;
	}

	public static class Params {
		
		String processDefinitionCode;
		
		String processDefinitionJson;

		public String getProcessDefinitionCode() {
			return processDefinitionCode;
		}

		public void setProcessDefinitionCode(String processDefinitionCode) {
			this.processDefinitionCode = processDefinitionCode;
		}

		public String getProcessDefinitionJson() {
			return processDefinitionJson;
		}

		public void setProcessDefinitionJson(String processDefinitionJson) {
			this.processDefinitionJson = processDefinitionJson;
		}
	}
	
}
