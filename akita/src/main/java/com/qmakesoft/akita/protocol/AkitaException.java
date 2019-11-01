package com.qmakesoft.akita.protocol;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public class AkitaException extends RuntimeException{

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
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
