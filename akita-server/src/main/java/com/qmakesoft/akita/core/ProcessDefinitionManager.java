package com.qmakesoft.akita.core;

import com.qmakesoft.akita.protocol.AkitaException;

public class ProcessDefinitionManager {
	
	public static ProcessDefinition getProcessDefinition(String processDefinitionCode) {
		
		
		
		throw new AkitaException(101, "未找到流程定义，请确认是否存在该代码的流程定义 : " + processDefinitionCode);
	}
}
