package com.qmakesoft.akita.model;

public class ProcessInstance {

	String processInstanceId;
	
	String processDefinitionCode;
	
	String version;
	
	String creator;
	
	String businessForm;
	
	String businessKey;
	
	String businessType;
	
	String comment;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionCode() {
		return processDefinitionCode;
	}

	public void setProcessDefinitionCode(String processDefinitionCode) {
		this.processDefinitionCode = processDefinitionCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getBusinessForm() {
		return businessForm;
	}

	public void setBusinessForm(String businessForm) {
		this.businessForm = businessForm;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
