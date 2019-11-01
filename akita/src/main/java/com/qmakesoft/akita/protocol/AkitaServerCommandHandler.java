package com.qmakesoft.akita.protocol;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

/**
  * 服务端接收到自定义协议的请求处理类
 * @author Jerry.Zhao
 *
 */
public class AkitaServerCommandHandler {

	@Autowired
	AkitaServerCommandAware akitaServerCommandAware;
	
	@SuppressWarnings("unchecked")
	public final <T> Object execute(Integer code , String message) {
		AbstractCommand<T> command = (AbstractCommand<T>) akitaServerCommandAware.commandMap.get(code);
		Type type = ((ParameterizedType)command.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		T t = JSON.parseObject(message, type);
		Object result = execute(command, t);
		return result;
	}

	public <T> Object execute(AbstractCommand<T> command, T t) {
		return command.execute(t);
	}
	
}
