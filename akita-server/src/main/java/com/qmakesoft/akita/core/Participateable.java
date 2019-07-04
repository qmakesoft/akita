package com.qmakesoft.akita.core;

import java.util.List;

import com.qmakesoft.akita.command.CommandContext;

/**
  *  修饰Node是否可以人工参与
 * @author Jerry.Zhao
 *
 */
public interface Participateable {
	
	List<String> createTask(CommandContext context);

}
