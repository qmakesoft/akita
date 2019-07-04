package com.qmakesoft.akita.core;

import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;

public abstract class AbstractMultiplePathNodeAdapter extends AbstractNode {

	public Set<Path> todo(CommandContext context){
		return doEvent(context);
	}
	
	protected abstract Set<Path> doEvent(CommandContext context);
}
