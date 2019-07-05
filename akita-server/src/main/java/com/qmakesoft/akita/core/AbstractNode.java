package com.qmakesoft.akita.core;

import java.util.List;
import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.NewTaskEvent;
import com.qmakesoft.akita.command.event.PathEvent;

public abstract class AbstractNode {
	
	protected String code;
	
	public boolean launch(CommandContext context){

		Set<Path> pathList = todo(context);
		
		if(this instanceof Endable) {
			return true;
		}
		
		if(this instanceof NoPathable) {
			return false;
		}
		
		boolean end = false;
		
		for(Path path : pathList) {
			context.addEvent(new PathEvent(this,context,path));
			ProcessDefinition processDefinition = ProcessDefinitionManager.getProcessDefinition(context.getProcessDefinitionCode());
			AbstractNode nextNode = processDefinition.nodeManager().getNode(path.getTargetNodeCode());
			if( nextNode instanceof Participateable) {
				List<String> participantList = ((Participateable)nextNode).getParticipantHandle()
						.findParticipantList(context.getProcessInstanceId());
				context.addEvent(new NewTaskEvent(this,context,participantList,((Participateable)nextNode).getTaskType()));
				//TODO 做创建人自动跳转、委派、重复参与跳过等的逻辑判断
			}else {
				end = end ? end : nextNode.launch(context);
			}
		}
		
		return end;
	}
	
	protected abstract Set<Path> todo(CommandContext context);

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
