package com.qmakesoft.akita.command.event;

import java.util.Date;
import java.util.UUID;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.cache.SimpleCache;
import com.qmakesoft.akita.core.AbstractNode;
import com.qmakesoft.akita.model.NodeHistory;
import com.qmakesoft.akita.model.Task;

public class NodeHistoryEvent extends AbstractEvent{

	public NodeHistoryEvent(AbstractNode node,CommandContext context) {
		super(node,context);
	}

	@Override
	public void doCache() {
		NodeHistory nodeHistory = new NodeHistory();
		nodeHistory.setComment(context.getComment());
		nodeHistory.setHistoryId(UUID.randomUUID().toString());
		nodeHistory.setNodeCode(targetNode.getCode());
		nodeHistory.setOperateDate(new Date());
		Task task = SimpleCache.getTaskInParticipant(context.getOperator(),context.getTaskId());
		if(task != null) {
			nodeHistory.setOperateType(task.getTaskType());
			nodeHistory.setParticipant(task.getParticipant());
			nodeHistory.setTaskDate(task.getCreateDate());
		}
		nodeHistory.setOperator(context.getOperator());
		nodeHistory.setProcessInstanceId(context.getProcessInstanceId());
		SimpleCache.putObjectInProcessInstance(context.getProcessInstanceId(), nodeHistory);
	}

	@Override
	public void doDatabase() {
		
	}
	
}
