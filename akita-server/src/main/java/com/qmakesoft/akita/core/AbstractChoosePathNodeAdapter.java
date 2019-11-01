package com.qmakesoft.akita.core;

import java.util.HashSet;
import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public abstract class AbstractChoosePathNodeAdapter extends AbstractNode {

	@Override
	public Set<Path> todo(CommandContext context){
		Path path = doEvent(context);
		Set<Path> paths = new HashSet<>();
		paths.add(path);
		return paths;
	}
	
	protected abstract Path doEvent(CommandContext context);

}
