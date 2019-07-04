package com.qmakesoft.akita.command.event.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.qmakesoft.akita.model.Task;

public class SimpleCache extends LinkedHashMap<String, List<Object>>{

	private static final long serialVersionUID = 1L;
	
	private static SimpleCache instance = new SimpleCache();

	public static SimpleCache getInstance() {
		return instance;
	}
	
	public static Task getTaskInParticipant(String participant,String taskId) {
		List<Object> list = instance.get(participant);
		if(list != null && !list.isEmpty()) {
			for(Object object : list) {
				if(object instanceof Task) {
					Task task = (Task)object;
					if(taskId.equals(task.getTaskId())) {
						return task;
					}
				}
			}
		}
		return null;
	}
	
	public static void putTaskInParticipant(String participant,Task task) {
		List<Object> list = instance.get(participant);
		if(list == null) {
			list = new ArrayList<Object>();
			instance.put(participant, list);
		}
		list.add(task);
	}
	
	public static void removeTaskInParticipant(String participant , String taskId) {
		List<Object> list = instance.get(participant);
		Iterator<Object> iterator = list.iterator();
		while(iterator.hasNext()) {
			Object object = iterator.next();
			if(object instanceof Task) {
				Task task = (Task)object;
				if(taskId.equals(task.getTaskId())) {
					iterator.remove();
				}
			}
		}
	}
	
	public static void putObjectInProcessInstance(String processInstanceId,Object obj) {
		List<Object> list = instance.get(processInstanceId);
		if(list == null) {
			list = new ArrayList<Object>();
			instance.put(processInstanceId, list);
		}
		list.add(obj);
	}
	
	public static void removeTaskInProcessInstance(String processInstanceId,String taskId) {
		List<Object> list = instance.get(processInstanceId);
		Iterator<Object> iterator = list.iterator();
		while(iterator.hasNext()) {
			Object object = iterator.next();
			if(object instanceof Task) {
				Task task = (Task)object;
				if(taskId.equals(task.getTaskId())) {
					iterator.remove();
				}
			}
		}
	}
	
}
