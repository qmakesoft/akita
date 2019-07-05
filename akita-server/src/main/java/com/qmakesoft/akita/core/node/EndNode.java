package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractNoPathNodeAdapter;
import com.qmakesoft.akita.core.Endable;
import com.qmakesoft.akita.core.parse.NodeObject;

public class EndNode extends AbstractNoPathNodeAdapter implements Endable {

	public static final String TYPE_NAME = "end";
	
	public EndNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
	}

	@Override
	public void doEvent(CommandContext context) {
		
	}
	
}
