package com.example.components;

import java.math.BigDecimal;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.AdjacentPlanet;
import com.example.domain.Planet;
import com.example.services.PlanetService;

@Service
public class Algorithm {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanetService.class);

	private static List<Planet> settledPlanets = new ArrayList<>();
	
	private static Set<Planet> unsettledPlanets = new HashSet<>();
	
	private static BigDecimal    lightYearConstant = new BigDecimal ("9460730472580800"); 
	private static BigDecimal    passengerTravelSpeed = new BigDecimal ("7500000000000");   

	@Autowired
	private PlanetService planetService;

	public List<Planet> calculateShortestPathFromSource(Planet source) {
		logger.info("Preparing the graph");
		if (settledPlanets.isEmpty()) {
			logger.info("Graph is empty");

			source.setDistance(0D);
			unsettledPlanets.add(source);

			while (unsettledPlanets.size() != 0) {
				Planet currentPlanet = getLowestDistancePlanet(unsettledPlanets);
				unsettledPlanets.remove(currentPlanet);
				for (AdjacentPlanet adjPlanetInfo : currentPlanet.getAdjacentPlanets()) {
					Planet adjacentPlanet = planetService.findByNode(adjPlanetInfo.getPlanetDestinationNode());
					Float edgeWeigh = Float.parseFloat(adjPlanetInfo.getEdgeWeight());
					Float traffic = Float.parseFloat(adjPlanetInfo.getTraffic());

					if (!settledPlanets.contains(adjacentPlanet)) {
						CalculateMinimumDistance(adjacentPlanet, edgeWeigh, traffic, currentPlanet);
						unsettledPlanets.add(adjacentPlanet);
					}
				}
				settledPlanets.add(currentPlanet);
			}
			
			settledPlanets.sort((o1, o2)->o1.getPlanetNode().compareTo(o2.getPlanetNode()));

		}
		return settledPlanets;
	}

	private void CalculateMinimumDistance(Planet evaluationPlanet, Float edgeWeigh, Float traffic, Planet sourcePlanet) {
		double sourceDistance = sourcePlanet.getDistance();
		double sourceTotalTrafic = sourcePlanet.getTotalTraffic();

		double distance = sourceDistance + edgeWeigh;
		double totalTrafic = sourceTotalTrafic + traffic;
		double distanceWithTraffic = distance - totalTrafic;
		
		BigDecimal distanceInMeeter =  lightYearConstant.multiply(new BigDecimal(distance));
		BigDecimal  timeTakenInSec = distanceInMeeter.divide(passengerTravelSpeed);
		BigDecimal  sec = timeTakenInSec.remainder(new BigDecimal ("60"));
		BigDecimal  min = timeTakenInSec.divide(new BigDecimal ("60")).remainder(new BigDecimal ("60"));
		BigDecimal  hours = timeTakenInSec.divide(new BigDecimal ("60")).divide(new BigDecimal ("60"));
		
		StringBuilder sbTimeTaken_WithOutTraffic = new StringBuilder(); 
		sbTimeTaken_WithOutTraffic.append(hours.setScale(0,BigDecimal.ROUND_DOWN).toString()).append(" Hours: ");
		sbTimeTaken_WithOutTraffic.append(min.setScale(0,BigDecimal.ROUND_DOWN).toString()).append(" Minutes: ");
		sbTimeTaken_WithOutTraffic.append(sec.setScale(0,BigDecimal.ROUND_DOWN).toString()).append(" Seconds");
		
		distanceInMeeter =  lightYearConstant.multiply(new BigDecimal(totalTrafic));
		timeTakenInSec = distanceInMeeter.divide(passengerTravelSpeed);
		sec = timeTakenInSec.remainder(new BigDecimal ("60"));
		min = timeTakenInSec.divide(new BigDecimal ("60")).remainder(new BigDecimal ("60"));
		hours = timeTakenInSec.divide(new BigDecimal ("60")).divide(new BigDecimal ("60"));
		
		
		StringBuilder sbTimeTaken_WithTraffic = new StringBuilder(); 
		sbTimeTaken_WithTraffic.append(hours.setScale(0,BigDecimal.ROUND_DOWN).toString()).append(" Hours: ");
		sbTimeTaken_WithTraffic.append(min.setScale(0,BigDecimal.ROUND_DOWN).toString()).append(" Minutes: ");
		sbTimeTaken_WithTraffic.append(sec.setScale(0,BigDecimal.ROUND_DOWN).toString()).append(" Seconds");
		
		

		
		if (distance < evaluationPlanet.getDistance()) {			
			evaluationPlanet.setDistance(distance);
			evaluationPlanet.setTotalTraffic(totalTrafic);
			evaluationPlanet.setDistanceWithTraffic(distanceWithTraffic);
			evaluationPlanet.setTimeTakenWithTraffic(sbTimeTaken_WithTraffic.toString());
			evaluationPlanet.setTimeTakenWithOutTraffic(sbTimeTaken_WithOutTraffic.toString());
			
			LinkedList<Planet> shortestPath = new LinkedList<>(sourcePlanet.getShortestPath());
			shortestPath.add(sourcePlanet);
			evaluationPlanet.setShortestPath(shortestPath);
		}
	}

	private Planet getLowestDistancePlanet(Set<Planet> unsettledPlanets) {
		Planet lowestDistancePlanet = null;
		double lowestDistance = Double.MAX_VALUE;
		for (Planet planet : unsettledPlanets) {
			double planetDistance = planet.getDistance();
			if (planetDistance < lowestDistance) {
				lowestDistance = planetDistance;
				lowestDistancePlanet = planet;
			}
		}
		return lowestDistancePlanet;
	}
	
	private List<Planet> saveGraph(List<Planet> planets){
		
		return planetService.savePlanet(planets);
	}

	public static List<Planet> getSettledPlanets() {
		return settledPlanets;
	}
	
	public Planet findSettledPlanet(Planet p_Planet) {
		if(settledPlanets == null || settledPlanets.isEmpty()) {
			Planet source = planetService.findByNode("A");
			settledPlanets = calculateShortestPathFromSource(source);		
		}
		for(Planet planet :settledPlanets) {
			if(planet.equals(p_Planet)) {
				return planet;
			}
		}
		return null;
	}
	

}
