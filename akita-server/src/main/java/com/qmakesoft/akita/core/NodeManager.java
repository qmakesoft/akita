package com.qmakesoft.akita.core;

import java.util.HashSet;
import java.util.Set;

public final class NodeManager {
	
	Set<AbstractNode> nodes = new HashSet<AbstractNode>();
	
	public AbstractNode startNode(){
		return null;
	}
	
	public AbstractNode getNode(String nodeCode) {
		return null;
	}
	
	public void addNode(AbstractNode node) {
		nodes.add(node);
	}
}
