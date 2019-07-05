package com.qmakesoft.akita.core;

import com.qmakesoft.akita.core.node.participant.AbstractParticipantHandle;

/**
  *  修饰Node是否可以人工参与
 * @author Jerry.Zhao
 *
 */
public interface Participateable {
	
	AbstractParticipantHandle getParticipantHandle();

	String getTaskType();

}
