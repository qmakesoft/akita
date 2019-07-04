package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractNoPathNodeAdapter;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.participant.AbstractParticipantHandle;

public class NoticeNode extends AbstractNoPathNodeAdapter implements Participateable{

	AbstractParticipantHandle participantHandle;
	
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
