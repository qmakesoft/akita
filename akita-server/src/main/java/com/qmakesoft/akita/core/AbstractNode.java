package com.qmakesoft.akita.core;

import java.util.List;
import java.util.Set;

import com.qmakesoft.akita.command.CommandContext;
import com.qmakesoft.akita.command.event.NewTaskEvent;
import com.qmakesoft.akita.command.event.PathEvent;

/**
 * 
 * @author Jerry.Zhao
 *
 */
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
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	protected abstract Set<Path> todo(CommandContext context);

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public int hashCode() {
		return code.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof AbstractNode)) {
			return false;
		}
		
		AbstractNode node2 = ((AbstractNode)obj);
		return code.equals(node2.code);
	}
	
}
