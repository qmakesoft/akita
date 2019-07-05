package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.NewProcessInstanceEvent;
import com.qmakesoft.akita.command.event.NodeHistoryEvent;
import com.qmakesoft.akita.core.AbstractSinglePathNodeAdapter;
import com.qmakesoft.akita.core.FirstNode;
import com.qmakesoft.akita.core.parse.NodeObject;

public class StartNode extends AbstractSinglePathNodeAdapter implements FirstNode{

	public static final String TYPE_NAME = "start";
	
	public StartNode(NodeObject nodeObject) {
		this.setCode(nodeObject.getCode());
	}

	@Override
	public void doEvent(CommandContext context) {
		//创建流程
		context.addEvent(new NewProcessInstanceEvent(this,context));//流程实例
		context.addEvent(new NodeHistoryEvent(this,context));
	}

}
