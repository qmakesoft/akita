package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractSinglePathNodeAdapter;
import com.qmakesoft.akita.core.Waitable;
import com.qmakesoft.akita.core.parse.NodeObject;

public class JoinNode extends AbstractSinglePathNodeAdapter implements Waitable {

	public static final String TYPE_NAME = "join";
	
	public JoinNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
	}
	
	@Override
	public void doEvent(CommandContext context) {
		// TODO Auto-generated method stub
	}

}
