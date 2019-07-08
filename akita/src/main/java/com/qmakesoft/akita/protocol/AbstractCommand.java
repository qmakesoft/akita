package com.qmakesoft.akita.protocol;

public abstract class AbstractCommand<T> {
	
	public abstract Object execute(T params);

	public abstract int code();
}
