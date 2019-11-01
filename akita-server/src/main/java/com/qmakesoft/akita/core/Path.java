package com.qmakesoft.akita.core;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public final class Path {
	
	String code;
	
	String sourceNodeCode;
	
	String targetNodeCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	private String getValues() {
		return new StringBuilder()
				.append(code)
				.append("_")
				.append(sourceNodeCode)
				.append("_")
				.append(targetNodeCode).toString();
	}
	
	@Override
	public int hashCode() {
		return getValues().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Path)) {
			return false;
		}
		Path path2 = ((Path)obj);
		return  (this.code == path2.code || this.code.equals(path2.code))
				&&(this.sourceNodeCode == path2.sourceNodeCode || this.sourceNodeCode.equals(path2.sourceNodeCode))
				&&(this.targetNodeCode == path2.targetNodeCode || this.targetNodeCode.equals(path2.targetNodeCode));
	}
}
