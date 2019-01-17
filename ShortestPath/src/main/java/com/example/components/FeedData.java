package com.example.components;

import java.io.File;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.domain.Planet;
import com.example.services.PlanetService;

@Component
public class FeedData implements ApplicationRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedData.class);

	@Autowired
	private PlanetService planetService;	
	

	@Autowired
	private Algorithm algorithm;
	
	public void run(ApplicationArguments args) {
		try {
			loadPlanetsFromCSV();
			loadPlanetsDistanceFromCSV();
			loadPlanetTrafficFromCSVFile();
			//calculateShortestPathFromSource();		
		}catch(Exception e) {
			logger.error("Error in Feed Data", e);
		}
	}
	
	
	private void loadPlanetsFromCSV() throws Exception{
		Resource resource = new ClassPathResource("import/Planets.csv");
		File file = resource.getFile();
		planetService.loadPlanetsFromCSVFile(file);
		
	}
	
	private void loadPlanetsDistanceFromCSV() throws Exception{
		Resource resource = new ClassPathResource("import/DistanceBetweenPlanets.csv");
		File file = resource.getFile();
		planetService.loadPlanetsDistanceFromCSVFile(file);
	}
	
	private void loadPlanetTrafficFromCSVFile() throws Exception{
		Resource resource = new ClassPathResource("import/TrafficBetweenPlanets.csv");
		File file = resource.getFile();
		planetService.loadPlanetTrafficFromCSVFile(file);
	}
	
	private void calculateShortestPathFromSource() throws Exception{
		Planet source = planetService.findByNode("A");
		algorithm.calculateShortestPathFromSource(source);	
	}

}
