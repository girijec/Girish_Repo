package com.example.controllers;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.components.Algorithm;
import com.example.domain.Planet;
import com.example.services.PlanetService;


@RestController
@RequestMapping(value = "/api/planet")
public class PlanetController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanetController.class);

	@Autowired
	private PlanetService planetService;	
	
	
	@Autowired
	private Algorithm algorithm;	
	
	

	// -------------------Retrieve All Planet-------------

	@GetMapping
	public ResponseEntity<List<Planet>> getAll() {
		logger.info("Get All Planet ");
		List<Planet> list = planetService.findAllPlanet();
		return new ResponseEntity<List<Planet>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/node/{node}")
	public ResponseEntity<Planet> getByNode(@PathVariable String node) {
		logger.info("Get Planet by node {} ", node);
		Planet planet = planetService.findByNode(node);
		return new ResponseEntity<Planet>(planet, HttpStatus.OK);
	}
	
	@GetMapping(value="/graph")
	public ResponseEntity<List<Planet>> getGraph() {
		logger.info("Get Settled Planet ");
		Planet source = planetService.findByNode("A");

		List<Planet> planets = algorithm.calculateShortestPathFromSource(source);
		logger.info("Total Settled Planet = "+planets.size());
		return new ResponseEntity<List<Planet>>(planets, HttpStatus.OK);
	}
	
	@GetMapping(value="/preparegraph")
	public ResponseEntity<List<Planet>> getGraphSave() {
		logger.info("Get Settled Planet ");
		Planet source = planetService.findByNode("A");

		List<Planet> planets = algorithm.calculateShortestPathFromSource(source);
		planetService.savePlanet(planets);
		return new ResponseEntity<List<Planet>>(planets, HttpStatus.OK);
	}

}
