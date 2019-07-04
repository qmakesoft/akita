package com.qmakesoft.akita.core.node;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractChoosePathNodeAdapter;
import com.qmakesoft.akita.core.HasMultiplePath;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.Path;
import com.qmakesoft.akita.core.participant.AbstractParticipantHandle;

public class CountersignNode extends AbstractChoosePathNodeAdapter implements Participateable , HasMultiplePath {

	AbstractParticipantHandle participantHandle;
	
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
		return "Countersign";
	}

	
}
