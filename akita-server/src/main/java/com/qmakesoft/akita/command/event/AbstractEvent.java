package com.qmakesoft.akita.command.event;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractNode;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public abstract class AbstractEvent {
	
	CommandContext context;
	
	AbstractNode targetNode;
	
	public AbstractEvent(AbstractNode node,CommandContext context) {
		this.targetNode = node;
		this.context = context;
	}
	
	public abstract void doCache();
	
	public abstract void doDatabase();
	
}
