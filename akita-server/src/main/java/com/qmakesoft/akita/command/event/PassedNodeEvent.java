package com.qmakesoft.akita.command.event;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractNode;

public class PassedNodeEvent extends AbstractEvent{

	public PassedNodeEvent(AbstractNode node,CommandContext context) {
		super(node,context);
	}

	@Override
	public void doCache() {
		
	}

	@Override
	public void doDatabase() {
		
	}
	
}
