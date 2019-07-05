package com.qmakesoft.akita.command;

import java.util.Map;

public abstract class AbstractCommand {
	
	protected abstract Object execute();
	
	protected abstract void initCommand(Map<String,Object> message);
}
