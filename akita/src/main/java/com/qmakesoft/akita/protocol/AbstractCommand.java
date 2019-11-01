package com.qmakesoft.akita.protocol;

/**
 * 
 * @author Jerry.Zhao
 *
 * @param <T>
 */
public abstract class AbstractCommand<T> {
	
	public abstract Object execute(T params);
	
	public abstract int code();
	
}
