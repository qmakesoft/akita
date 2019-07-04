package com.qmakesoft.akita.command;

import java.util.List;

import com.qmakesoft.akita.command.event.Event;

public class CommandContext {
	//一个context只服务于一个command
	//##这里要包含客户端对应的信息，通讯Channel  --这里似乎不用加这个，这个是命令执行完以后做的事情
	//事件管理器  ,  一个命令的执行可能会产生多个事件的发生，每个事件对应着数据的变化，及流程的变化、节点的变化	
	protected List<Event> eventList;
	
	protected String processInstanceId;
	
	protected String processDefinitionCode;
	
	protected String version;
	
	protected String operator;
	
	protected String businessForm;
	
	protected String businessKey;
	
	protected String businessType;
	
	protected String comment;
	
	protected String taskId;
	
	public CommandContext processDefinitionCode(String processDefinitionCode) {
		this.processDefinitionCode = processDefinitionCode;
		return this;
	}

	public CommandContext version(String version) {
		this.version = version;
		return this;
	}

	public CommandContext operator(String operator) {
		this.operator = operator;
		return this;
	}

	public CommandContext businessForm(String businessForm) {
		this.businessForm = businessForm;
		return this;
	}

	public CommandContext businessKey(String businessKey) {
		this.businessKey = businessKey;
		return this;
	}

	public CommandContext businessType(String businessType) {
		this.businessType = businessType;
		return this;
	}

	public CommandContext comment(String comment) {
		this.comment = comment;
		return this;
	}

	public CommandContext processInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
		return this;
	}
	
	public List<Event> getEventList() {
		return eventList;
	}

	public String getProcessDefinitionCode() {
		return processDefinitionCode;
	}

	public String getVersion() {
		return version;
	}

	public String getOperator() {
		return operator;
	}

	public String getBusinessForm() {
		return businessForm;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public String getBusinessType() {
		return businessType;
	}

	public String getComment() {
		return comment;
	}
	
	public void addEvent(Event event) {
		this.eventList.add(event);
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}
	
}
