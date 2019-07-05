package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractChoosePathNodeAdapter;
import com.qmakesoft.akita.core.HasMultiplePath;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.core.node.participant.AbstractParticipantHandle;
import com.qmakesoft.akita.core.node.participant.ParticipantHandleFactory;
import com.qmakesoft.akita.core.parse.NodeObject;

public class CountersignNode extends AbstractChoosePathNodeAdapter implements Participateable , HasMultiplePath {

	public static final String TYPE_NAME = "countersign";
	
	AbstractParticipantHandle participantHandle;
	
	Boolean order;
	
	public CountersignNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
		this.order = nodeObject.getOrder();
		participantHandle = ParticipantHandleFactory.factory(nodeObject);
	}

	@Override
	protected Path doEvent(CommandContext context) {
		return null;
	}

	@Override
	public AbstractParticipantHandle getParticipantHandle() {
		return participantHandle;
	}

	@Override
	public String getTaskType() {
		return TYPE_NAME;
	}
	
}
