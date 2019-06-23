package com.qmakesoft.akita.protocol;

public class AkitaMessageCodeConstant {

	public final static int HEARTBEAT = 0;
	/**
	 * 消息请求
	 */
	public final static int REQUEST_MESSAGE = 10;
	/**
	 * 服务端返回成功
	 */
	public final static int RESPONSE_SUCCESS = 11;
	/**
	 * 服务端返回失败 
	 */
	public final static int RESPONSE_ERROR = 12;
	/**
	 * 请求认证
	 */
	public final static int REQUEST_AUTHENTICATION = 20;
	/**
	 * 认证成功
	 */
	public final static int RESPONSE_AUTHENTICATED = 21;
	/**
	 * 认证失败
	 */
	public final static int RESPONSE_UNAUTHENTICATED = 22;
	
}
