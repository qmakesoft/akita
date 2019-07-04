package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.NewProcessInstanceEvent;
import com.qmakesoft.akita.command.event.NodeHistoryEvent;
import com.qmakesoft.akita.core.AbstractSinglePathNodeAdapter;
import com.qmakesoft.akita.core.FirstNode;

public class StartNode extends AbstractSinglePathNodeAdapter implements FirstNode{

	@Override
	public void doEvent(CommandContext context) {
		//创建流程
		context.addEvent(new NewProcessInstanceEvent(this,context));//流程实例
		context.addEvent(new NodeHistoryEvent(this,context));
	}

}
