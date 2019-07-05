package com.qmakesoft.akita.core;

import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;

public abstract class AbstractSinglePathNodeAdapter extends AbstractNode {

	public Set<Path> todo(CommandContext context){
		doEvent(context);
		ProcessDefinition processDefinition = ProcessDefinitionManager.getProcessDefinition(context.getProcessDefinitionCode());
		return processDefinition.pathManager().getPathsBySourceNode(this);
	}
	
	protected abstract void doEvent(CommandContext context);
	
}
