package com.qmakesoft.akita.command.event;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.cache.SimpleCache;
import com.qmakesoft.akita.core.AbstractNode;
import com.qmakesoft.akita.model.Task;

public class NewTaskEvent extends AbstractEvent{

	List<String> participantList;
	
	String taskType;
	
	public NewTaskEvent(AbstractNode node, CommandContext context, List<String> participantList,String taskType) {
		super(node,context);
		this.participantList = participantList; 
	}
	
	@Override
	public void doCache() {
		int index = 0;
		for(String participant : participantList) {
			Task task = new Task();
			task.setCreateDate(new Date());
			task.setNo(index++);
			task.setNodeCode(targetNode.getCode());
			task.setParticipant(participant);
			task.setProcessInstanceId(context.getProcessInstanceId());
			task.setTaskId(UUID.randomUUID().toString());
			task.setTaskParentId(null);
			task.setTaskType(taskType);
			SimpleCache.putObjectInProcessInstance(context.getProcessInstanceId(), task);
			SimpleCache.putTaskInParticipant(participant,task);
		}
	}

	@Override
	public void doDatabase() {
		
	}

}
