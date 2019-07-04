package com.qmakesoft.akita.model;

public class PathHistory {
	
	String processInstanceId;
	
	String sourceNodeCode;
	
	String targetNodeCode;
	
	String pathCode;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getSourceNodeCode() {
		return sourceNodeCode;
	}

	public void setSourceNodeCode(String sourceNodeCode) {
		this.sourceNodeCode = sourceNodeCode;
	}

	public String getTargetNodeCode() {
		return targetNodeCode;
	}

	public void setTargetNodeCode(String targetNodeCode) {
		this.targetNodeCode = targetNodeCode;
	}

	public String getPathCode() {
		return pathCode;
	}

	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}
	
}
