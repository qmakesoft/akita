package com.qmakesoft.akita.protocol;

public class AkitaException extends Exception{

	private static final long serialVersionUID = 1L;

	Integer code ;
	
	String message;
	
	public AkitaException(Integer code ,String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
}
