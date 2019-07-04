package com.qmakesoft.akita.core.node;

import java.util.List;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.core.AbstractChoosePathNodeAdapter;
import com.qmakesoft.akita.core.HasMultiplePath;
import com.qmakesoft.akita.core.Participateable;
import com.qmakesoft.akita.core.Path;

public class CountersignNode extends AbstractChoosePathNodeAdapter implements Participateable , HasMultiplePath {

	@Override
	protected Path doEvent(CommandContext context) {
		return null;
	}

	@Override
	public List<String> createTask(CommandContext contex) {
		return null;
	}

	
}
