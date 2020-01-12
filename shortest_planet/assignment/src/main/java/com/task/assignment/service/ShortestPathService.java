package com.task.assignment.service;

import java.util.List;

import com.task.assignment.dto.GraphDTO;
import com.task.assignment.model.Graph;
import com.task.assignment.model.Node;

public interface ShortestPathService {

	public Graph calculateShortestPathFromSource(Graph graph, Node source);
	
	public List<GraphDTO> shortestPathDetails(String sourceNodeName);
}
