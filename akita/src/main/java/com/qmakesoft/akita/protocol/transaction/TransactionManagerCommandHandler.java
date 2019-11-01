package com.qmakesoft.akita.protocol.transaction;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallback;
//import org.springframework.transaction.support.TransactionTemplate;

import com.qmakesoft.akita.protocol.AbstractCommand;
import com.qmakesoft.akita.protocol.AkitaServerCommandHandler;

/**
 * 继承AkitaServerCommandHandler，
 * 将命令执行产生的数据库更新， 通过事务管理 ，如果命令执行抛出Exception则回滚。
 * 如果Command实现了AkitaTransaction接口则交由事务管理 ，否则则不管理
 * @author Jerry.Zhao
 */
//@Component
public class TransactionManagerCommandHandler extends AkitaServerCommandHandler{
//
//	@Autowired
//	DataSourceTransactionManager dataSourceTransactionManager;
//	
//	@Override
//	public <T> Object execute(AbstractCommand<T> command, T t) {
//		Object result = null;
//		if(command instanceof AkitaTransaction) {
//			TransactionTemplate tt = new TransactionTemplate(dataSourceTransactionManager);
//			result = tt.execute(new TransactionCallback<Object>() {
//				@Override
//				public Object doInTransaction(TransactionStatus status) {
//					return executeCommand(command, t);
//				}
//			});
//		}else {
//			result = executeCommand(command, t);
//		}
//		return result;
//	}
//
//	//执行命令
//	private <T> Object executeCommand(AbstractCommand<T> command, T t) {
//		try {
//			Object result = command.execute(t);
//			return result;
//		}catch (RuntimeException e) {
//			throw e;
//		}
//	}
}
