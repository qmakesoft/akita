package com.qmakesoft.akita.core;

import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public abstract class AbstractNoPathNodeAdapter extends AbstractNode implements NoPathable {

	@Override
	public Set<Path> todo(CommandContext context){
		doEvent(context);
		return null;
	}
	/**
	 * 
	 * @param context
	 */
	protected abstract void doEvent(CommandContext context);
	
}
