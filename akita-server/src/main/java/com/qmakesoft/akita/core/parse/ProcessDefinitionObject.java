package com.qmakesoft.akita.core.parse;

import java.util.List;

import com.qmakesoft.akita.core.Path;

public class ProcessDefinitionObject {
	
	List<NodeObject> nodes;

	List<Path> paths;

	public List<NodeObject> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeObject> nodes) {
		this.nodes = nodes;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

}
