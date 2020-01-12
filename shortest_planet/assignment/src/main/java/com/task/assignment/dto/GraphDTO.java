package com.task.assignment.dto;

public class GraphDTO {

	private String sourceNode;
	private String destinatoinNode;
	private int shortestDistance;
	private String shortestPath;
	
	
	public String getSourceNode() {
		return sourceNode;
	}
	public void setSourceNode(String sourceNode) {
		this.sourceNode = sourceNode;
	}
	public String getDestinatoinNode() {
		return destinatoinNode;
	}
	public void setDestinatoinNode(String destinatoinNode) {
		this.destinatoinNode = destinatoinNode;
	}
	public int getShortestDistance() {
		return shortestDistance;
	}
	public void setShortestDistance(int shortestDistance) {
		this.shortestDistance = shortestDistance;
	}
	public String getShortestPath() {
		return shortestPath;
	}
	public void setShortestPath(String shortestPath) {
		this.shortestPath = shortestPath;
	}
	
	
	
}
