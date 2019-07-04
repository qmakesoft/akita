package com.qmakesoft.akita.command.event;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.cache.SimpleCache;
import com.qmakesoft.akita.core.AbstractNode;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.model.PathHistory;

public class PathEvent extends Event {

	Path path;
	
	public PathEvent(AbstractNode node, CommandContext context,Path path) {
		super(node, context);
		this.path = path;
	}

	@Override
	public void doCache() {
		PathHistory pathHistory = new PathHistory();
		pathHistory.setPathCode(path.getCode());
		pathHistory.setProcessInstanceId(context.getProcessInstanceId());
		pathHistory.setSourceNodeCode(path.getSourceNodeCode());
		pathHistory.setTargetNodeCode(path.getTargetNodeCode());
		SimpleCache.putObjectInProcessInstance(context.getProcessInstanceId(), pathHistory);
	}
	
	@Override
	public void doDatabase() {

	}
	
}
