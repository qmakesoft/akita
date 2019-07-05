package com.qmakesoft.akita.core.node.handle;

import com.qmakesoft.akita.core.parse.NodeObject;

public class DecisionHandleFactory {

	public static DecisionHandle factory(NodeObject nodeObject) {
		
		if(GroovyScriptDecisionHandle.TYPE_NAME.equals(nodeObject.getDecisionType())){
			return new GroovyScriptDecisionHandle(nodeObject.getGroovyScript());
		}

		throw new RuntimeException("未找到定义判断处理器类型 : ["+nodeObject.getDecisionType()+"]");
		
	}
	
}
