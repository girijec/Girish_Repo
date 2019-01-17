package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>{
	
	Optional<Planet> findByPlanetNode(String node);


}
