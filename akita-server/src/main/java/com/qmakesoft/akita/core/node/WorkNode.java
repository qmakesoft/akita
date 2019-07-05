package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.CompelteTaskEvent;
import com.qmakesoft.akita.core.AbstractSinglePathNodeAdapter;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.node.participant.AbstractParticipantHandle;
import com.qmakesoft.akita.core.node.participant.ParticipantHandleFactory;
import com.qmakesoft.akita.core.parse.NodeObject;

public class WorkNode extends AbstractSinglePathNodeAdapter implements Participateable{

	public static final String TYPE_NAME = "work";
	
	Boolean rejectAble;
	
	AbstractParticipantHandle participantHandle;
	
	public WorkNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
		participantHandle = ParticipantHandleFactory.factory(nodeObject);
	}

	@Override
	public void doEvent(CommandContext context) {
		context.addEvent(new CompelteTaskEvent(this,context));
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
