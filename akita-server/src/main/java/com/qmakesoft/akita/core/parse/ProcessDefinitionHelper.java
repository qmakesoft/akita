package com.qmakesoft.akita.core.parse;

import com.alibaba.fastjson.JSON;
import com.qmakesoft.akita.core.AbstractNode;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.core.ProcessDefinition;
import com.qmakesoft.akita.core.node.CountersignNode;
import com.qmakesoft.akita.core.node.DecisionNode;
import com.qmakesoft.akita.core.node.EndNode;
import com.qmakesoft.akita.core.node.ForkNode;
import com.qmakesoft.akita.core.node.JoinNode;
import com.qmakesoft.akita.core.node.NoticeNode;
import com.qmakesoft.akita.core.node.ProgramNode;
import com.qmakesoft.akita.core.node.StartNode;
import com.qmakesoft.akita.core.node.SubProcessNode;
import com.qmakesoft.akita.core.node.WorkNode;

public class ProcessDefinitionHelper {

	public static ProcessDefinition parse(String processDefinitionJson) {
		ProcessDefinitionObject processDefinitionObject = JSON.parseObject(processDefinitionJson,ProcessDefinitionObject.class);
		ProcessDefinition processDefinition = new ProcessDefinition();
		for(NodeObject nodeObject : processDefinitionObject.getNodes()) {
			AbstractNode node = parseNode(nodeObject);
			processDefinition.nodeManager().addNode(node);
		}
		
		for(Path path : processDefinitionObject.getPaths()) {
			processDefinition.pathManager().addPath(path);
		}
		
		return processDefinition;
	}
	
	private static AbstractNode parseNode(NodeObject nodeObject) {
		
		if(StartNode.TYPE_NAME.equals(nodeObject.type)) {
			return new StartNode(nodeObject);
		}
		
		if(CountersignNode.TYPE_NAME.equals(nodeObject.type)) {
			return new CountersignNode(nodeObject);
		}
		
		if(DecisionNode.TYPE_NAME.equals(nodeObject.type)) {
			return new DecisionNode(nodeObject);
		}
		
		if(EndNode.TYPE_NAME.equals(nodeObject.type)) {
			return new EndNode(nodeObject);
		}
		
		if(ForkNode.TYPE_NAME.equals(nodeObject.type)) {
			return new ForkNode(nodeObject);
		}
		
		if(JoinNode.TYPE_NAME.equals(nodeObject.type)) {
			return new JoinNode(nodeObject);
		}
		
		if(NoticeNode.TYPE_NAME.equals(nodeObject.type)) {
			return new NoticeNode(nodeObject);
		}
		
		if(ProgramNode.TYPE_NAME.equals(nodeObject.type)) {
			return new ProgramNode(nodeObject);
		}
		
		if(SubProcessNode.TYPE_NAME.equals(nodeObject.type)) {
			return new SubProcessNode(nodeObject);
		}
		
		if(WorkNode.TYPE_NAME.equals(nodeObject.type)) {
			return new WorkNode(nodeObject);
		}

		throw new RuntimeException("未知的节点类型定义：["+nodeObject.type+"]");
	}
	
}
