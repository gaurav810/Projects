package com.task.assignment.dto;

public class DistancPlanetsDTO {

	private int id;
	private String sourcePlanet;
	private String destinationPlanet;
	private int distance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSourcePlanet() {
		return sourcePlanet;
	}
	public void setSourcePlanet(String sourcePlanet) {
		this.sourcePlanet = sourcePlanet;
	}
	public String getDestinationPlanet() {
		return destinationPlanet;
	}
	public void setDestinationPlanet(String destinationPlanet) {
		this.destinationPlanet = destinationPlanet;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
