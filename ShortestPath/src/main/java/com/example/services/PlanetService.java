package com.example.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.domain.AdjacentPlanet;
import com.example.domain.Planet;
import com.example.repositories.AdjacentPlanetRepository;
import com.example.repositories.PlanetRepository;
import com.example.utils.CSVLoader;
import com.example.utils.DistanceCSVHeader;
import com.example.utils.PlanetCSVHeader;
import com.example.utils.RestException;
import com.example.utils.TrafficCSVHeader;

@Service
public class PlanetService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanetService.class);

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private AdjacentPlanetRepository adjacentPlanetRepository;
	
	public Planet savePlanet(Planet planet) {
		
		return planetRepository.save(planet);
	}
	
	public List<Planet> savePlanet(Iterable<Planet> entites) {
		
		return planetRepository.save(entites);
	}
	

	public AdjacentPlanet saveAdjPlanet(AdjacentPlanet adjPlanet) {
		return adjacentPlanetRepository.save(adjPlanet);
	}
	
	public Planet findByNode(String node) {
		Planet planet = planetRepository.findByPlanetNode(node)
				.orElseThrow(() -> new RestException("Planet not found in the system", HttpStatus.NOT_FOUND));
				
		return planet;
	}

	public List<Planet> findAllPlanet() {
		List<Planet> planets = planetRepository.findAll();
				
		return planets;
	}


	public void delete(Planet planet) {
		planetRepository.delete(planet);
	}

	
	public void loadPlanetsFromCSVFile(File file) throws Exception {

		List<PlanetCSVHeader> planets = CSVLoader.loadObjectList(PlanetCSVHeader.class, file);
		List<Planet> planetList = new ArrayList<Planet>();
		if (planets != null && !planets.isEmpty()) {
			planets.forEach(p -> {
				Planet planet = new Planet();
				planet.setPlanetNode(p.getPlanetNode());
				planet.setPlanetName(p.getPlanetName());
				planetList.add(planet);
			});
			logger.info("Total planet found :{}", planetList.size());
			savePlanet(planetList);

		}

	}
	
	
	public void loadPlanetsDistanceFromCSVFile(File file) throws Exception {
		
		List<DistanceCSVHeader> distanceBtwPlanetList = CSVLoader.loadObjectList(DistanceCSVHeader.class, file);
		
		distanceBtwPlanetList.forEach(d -> {
			
			AdjacentPlanet ajPlanet = new AdjacentPlanet();
			ajPlanet.setRouteId(d.getRouteId());
			ajPlanet.setEdgeWeight(d.getDistance());
			ajPlanet.setPlanetDestinationNode(d.getPlanetDestination());
			
			Planet planetOrigin = null;
			try {
				planetOrigin = findByNode(d.getPlanetOrigin());
				ajPlanet.setPlanetOrigin(planetOrigin);
				saveAdjPlanet(ajPlanet);
			}catch(RestException e) {
				logger.error("Origin planet {} not found", d.getPlanetOrigin());
			}
			
		});
	}
	
	public void loadPlanetTrafficFromCSVFile(File file) throws Exception {

		List<TrafficCSVHeader> trafficBtwPlanetList = CSVLoader.loadObjectList(TrafficCSVHeader.class, file);
		
		trafficBtwPlanetList.forEach(d -> {			
			AdjacentPlanet ajPlanet = adjacentPlanetRepository.findOne(d.getRouteId());
			ajPlanet.setTraffic(d.getTraffic());
			saveAdjPlanet(ajPlanet);
			
		});
	}
	

	
}
