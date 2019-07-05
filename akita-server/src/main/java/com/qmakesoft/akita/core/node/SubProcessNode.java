package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractSinglePathNodeAdapter;
import com.qmakesoft.akita.core.parse.NodeObject;

public class SubProcessNode extends AbstractSinglePathNodeAdapter {

	public static final String TYPE_NAME = "subProcess";
	
	public SubProcessNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
	}

	@Override
	public void doEvent(CommandContext context) {

	}

}
