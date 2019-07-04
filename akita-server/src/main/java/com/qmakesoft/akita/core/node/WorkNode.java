package com.qmakesoft.akita.core.node;

import java.util.List;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.CompelteTaskEvent;
import com.qmakesoft.akita.command.event.NewTaskEvent;
import com.qmakesoft.akita.core.AbstractSinglePathNodeAdapter;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.participant.AbstractParticipantHandle;

public class WorkNode extends AbstractSinglePathNodeAdapter implements Participateable{

	AbstractParticipantHandle participantHandle;
	
	@Override
	public void doEvent(CommandContext context) {
		context.addEvent(new CompelteTaskEvent(this,context));
	}

	@Override
	public List<String> createTask(CommandContext context) {
		List<String> participantList = participantHandle.findParticipantList(context.getProcessInstanceId());
		context.addEvent(new NewTaskEvent(this,context,participantList));
		return participantList;
	}
	
}
