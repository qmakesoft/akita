package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractChoosePathNodeAdapter;
import com.qmakesoft.akita.core.HasMultiplePath;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.core.ProcessDefinitionManager;
import com.qmakesoft.akita.core.node.handle.DecisionHandle;
import com.qmakesoft.akita.core.node.handle.DecisionHandleFactory;
import com.qmakesoft.akita.core.parse.NodeObject;

public class DecisionNode extends AbstractChoosePathNodeAdapter implements HasMultiplePath {

	public static final String TYPE_NAME = "decision";
	
	public DecisionHandle decisionHandle;
	
	public DecisionNode(NodeObject nodeObject) {
		decisionHandle = DecisionHandleFactory.factory(nodeObject);
	}

	@Override
	public Path doEvent(CommandContext context) {
		String pathCode = decisionHandle.decision(context);
		return ProcessDefinitionManager.getProcessDefinition(context.getProcessDefinitionCode()).pathManager().getPath(pathCode);
	}

}
