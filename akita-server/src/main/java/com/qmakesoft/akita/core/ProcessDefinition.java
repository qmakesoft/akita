package com.qmakesoft.akita.core;

public class ProcessDefinition {
	
	PathManager	pathManager = new PathManager();
	
	NodeManager nodeManager = new NodeManager();

	public PathManager pathManager() {
		return pathManager;
	}

	public NodeManager nodeManager() {
		return nodeManager;
	}
	
}
