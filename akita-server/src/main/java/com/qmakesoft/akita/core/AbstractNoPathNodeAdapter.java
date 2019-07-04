package com.qmakesoft.akita.core;

import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;

public abstract class AbstractNoPathNodeAdapter extends AbstractNode implements NoPathable {

	public Set<Path> todo(CommandContext context){
		doEvent(context);
		return null;
	}
	
	protected abstract void doEvent(CommandContext context);
	
}
