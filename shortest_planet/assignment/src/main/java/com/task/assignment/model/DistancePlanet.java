package com.task.assignment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="DISTANCE_PLANET")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DistancePlanet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="SOURCE")
	private Planet sourcePlanet;
	
	@ManyToOne
	@JoinColumn(name="DESTINATIOIN")
	private Planet destinationPlanet;
	
	@Column(name="DISTANCE")
	private int distance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Planet getSourcePlanet() {
		return sourcePlanet;
	}

	public void setSourcePlanet(Planet sourcePlanet) {
		this.sourcePlanet = sourcePlanet;
	}

	public Planet getDestinationPlanet() {
		return destinationPlanet;
	}

	public void setDestinationPlanet(Planet destinationPlanet) {
		this.destinationPlanet = destinationPlanet;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
