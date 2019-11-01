package com.qmakesoft.akita.command.event;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.cache.SimpleCache;
import com.qmakesoft.akita.core.AbstractNode;

public class CompelteTaskEvent extends AbstractEvent {
	
	public CompelteTaskEvent(AbstractNode node, CommandContext context) {
		super(node, context);
	}

	@Override
	public void doCache() {
		SimpleCache.removeTaskInProcessInstance(context.getProcessInstanceId(), context.getTaskId());
		SimpleCache.removeTaskInParticipant(context.getOperator(),context.getTaskId());
	}

	@Override
	public void doDatabase() {
		
	}

}
