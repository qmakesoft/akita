package com.qmakesoft.akita.command.event;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractNode;

public abstract class Event {
	
	CommandContext context;
	
	AbstractNode targetNode;
	
	public Event(AbstractNode node,CommandContext context) {
		this.targetNode = node;
		this.context = context;
	}
	
	public abstract void doCache();
	
	public abstract void doDatabase();
	
}
