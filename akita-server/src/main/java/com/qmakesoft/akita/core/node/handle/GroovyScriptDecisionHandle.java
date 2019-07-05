package com.qmakesoft.akita.core.node.handle;

import com.alibaba.fastjson.JSON;
import com.qmakesoft.akita.command.CommandContext;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyScriptDecisionHandle implements DecisionHandle{
	
	public final static String TYPE_NAME = "groovy";
	
	String groovyScript;
	
	public static final String BUSINESS_FORM = "businessForm"; 
	
	public GroovyScriptDecisionHandle(String groovyScript) {
		this.groovyScript = groovyScript;
	}
	
	@Override
	public String decision(CommandContext context) {
		Binding binding = new Binding();
		GroovyShell shell = new GroovyShell(binding);
		binding.setProperty(BUSINESS_FORM, JSON.parseObject(context.getBusinessForm()));
		Script script = shell.parse(groovyScript);
		Object scriptResult = script.run();
		if(!(scriptResult instanceof String)) {
			throw new RuntimeException("判断脚本必须返回路径代码");
		}
		return (String)scriptResult;
	}
}
