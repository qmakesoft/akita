package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractChoosePathNodeAdapter;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.core.parse.NodeObject;

public class ProgramNode extends AbstractChoosePathNodeAdapter {

	public static final String TYPE_NAME = "program";
	
	public ProgramNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
	}
	
	@Override
	protected Path doEvent(CommandContext context) {
		return null;
	}

}
