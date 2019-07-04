package com.qmakesoft.akita.core;

import com.qmakesoft.akita.core.participant.AbstractParticipantHandle;

/**
  *  修饰Node是否可以人工参与
 * @author Jerry.Zhao
 *
 */
public interface Participateable {
	
//	List<String> createTask(CommandContext context);
	
	AbstractParticipantHandle getParticipantHandle();

	String getTaskType();
	
}
