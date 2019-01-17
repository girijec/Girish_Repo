package com.example.domain;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.example.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Planet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "planet_id", nullable = false)
	private Long planetId;

	@Column(name = "planet_node", nullable = false)
	private String planetNode;

	@Column(name = "planet_name", nullable = false)
	private String planetName;

	@Transient
	private Double distance = Double.MAX_VALUE; // Only for carry the distance to UI, not using the separate POJO for now

	@Transient
	private Double totalTraffic = 0D; // Only for carry the traffic to UI, not using the separate POJO for now

	@Transient
	private Double distanceWithTraffic = 0D; // Only for carry the traffic to UI, not using the separate POJO for now,
											// distanceWithTraffic = distance - totalTraffic
	
	@Transient
	private String timeWithTraffic;
	
	@Transient
	private String timeWithOutTraffic;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "planetOrigin", cascade = CascadeType.ALL)
	private List<AdjacentPlanet> adjacentPlanets;

	@Transient
	private LinkedList<Planet> shortestPath = new LinkedList<>();

	
	
	

	public Long getPlanetId() {
		return planetId;
	}

	public void setPlanetId(Long planetId) {
		this.planetId = planetId;
	}

	public String getPlanetNode() {
		return planetNode;
	}

	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public Double getDistance() {
		return Utils.formatFloatValue(distance);
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getTotalTraffic() {
		return Utils.formatFloatValue(totalTraffic); 
	}

	public void setTotalTraffic(Double totalTraffic) {
		this.totalTraffic = totalTraffic;
	}

	public Double getDistanceWithTraffic() {
		return Utils.formatFloatValue(distanceWithTraffic); 
	}

	public void setDistanceWithTraffic(Double distanceWithTraffic) {
		this.distanceWithTraffic = distanceWithTraffic;
	}
	

	public String getTimeWithTraffic() {
		return timeWithTraffic;
	}

	public void setTimeWithTraffic(String timeWithTraffic) {
		this.timeWithTraffic = timeWithTraffic;
	}

	public String getTimeWithOutTraffic() {
		return timeWithOutTraffic;
	}

	public void setTimeWithOutTraffic(String timeWithOutTraffic) {
		this.timeWithOutTraffic = timeWithOutTraffic;
	}

	public List<AdjacentPlanet> getAdjacentPlanets() {
		return adjacentPlanets;
	}

	public void setAdjacentPlanets(List<AdjacentPlanet> adjacentPlanets) {
		this.adjacentPlanets = adjacentPlanets;
	}

	public LinkedList<Planet> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(LinkedList<Planet> shortestPath) {
		this.shortestPath = shortestPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planetId == null) ? 0 : planetId.hashCode());
		result = prime * result + ((planetName == null) ? 0 : planetName.hashCode());
		result = prime * result + ((planetNode == null) ? 0 : planetNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (planetId == null) {
			if (other.planetId != null)
				return false;
		} else if (!planetId.equals(other.planetId))
			return false;
		if (planetName == null) {
			if (other.planetName != null)
				return false;
		} else if (!planetName.equals(other.planetName))
			return false;
		if (planetNode == null) {
			if (other.planetNode != null)
				return false;
		} else if (!planetNode.equals(other.planetNode))
			return false;
		return true;
	}


}
