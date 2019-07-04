package com.qmakesoft.akita.core;

import java.util.HashSet;
import java.util.Set;

/**
 * path管理器
 * @author Jerry.Zhao
 *
 */
public final class PathManager {

	/**
	 * Key为源节点，Value为目标节点
	 */
	Set<Path> paths = new HashSet<Path>();
	
	/**
	 * 查找Node的所有流出路径集合
	 * @param sourceNode
	 * @return
	 */
	public Set<Path> getPaths(AbstractNode sourceNode){
		return null;
	}
	
	/**
	 * 查找Node的所有流入路径集合
	 * @param tragetNode
	 * @return
	 */
	public Set<Path> getPathsByTargetNode(AbstractNode tragetNode){
		return null;
	}
}


