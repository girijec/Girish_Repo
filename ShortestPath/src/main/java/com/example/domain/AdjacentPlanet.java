package com.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AdjacentPlanet {

	@Id
	@Column(name = "route_id", nullable = false)
	private Long routeId;

	@Column(name = "edge_weight ")
	private String edgeWeight;

	@Column(name = "traffic")
	private String traffic;

	@Column(name = "planet_destination")
	private String planetDestinationNode;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "planetId")
	private Planet planetOrigin;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}	

	public String getEdgeWeight() {
		return edgeWeight;
	}

	public void setEdgeWeight(String edgeWeight) {
		this.edgeWeight = edgeWeight;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getPlanetDestinationNode() {
		return planetDestinationNode;
	}

	public void setPlanetDestinationNode(String planetDestinationNode) {
		this.planetDestinationNode = planetDestinationNode;
	}

	public Planet getPlanetOrigin() {
		return planetOrigin;
	}

	public void setPlanetOrigin(Planet planetOrigin) {
		this.planetOrigin = planetOrigin;
	}

}

