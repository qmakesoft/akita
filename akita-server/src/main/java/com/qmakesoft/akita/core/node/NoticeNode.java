package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractNoPathNodeAdapter;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.node.participant.AbstractParticipantHandle;
import com.qmakesoft.akita.core.parse.NodeObject;

public class NoticeNode extends AbstractNoPathNodeAdapter implements Participateable{

	public static final String TYPE_NAME = "notice";
	
	AbstractParticipantHandle participantHandle;
	
	public NoticeNode(NodeObject nodeObject) {
		this.code = nodeObject.getCode();
	}

	@Override
	public void doEvent(CommandContext context) {
		
	}

	@Override
	public AbstractParticipantHandle getParticipantHandle() {
		return participantHandle;
	}

	@Override
	public String getTaskType() {
		return "Notice";
	}

}
