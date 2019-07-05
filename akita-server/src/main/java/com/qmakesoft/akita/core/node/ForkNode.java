package com.qmakesoft.akita.core.node;

import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractMultiplePathNodeAdapter;
import com.qmakesoft.akita.core.HasMultiplePath;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.core.parse.NodeObject;

public class ForkNode extends AbstractMultiplePathNodeAdapter implements HasMultiplePath {

	public static final String TYPE_NAME = "fork";
	
	public ForkNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
	}

	@Override
	public Set<Path> doEvent(CommandContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
