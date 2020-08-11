package com.starwars.planets.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starwars.planets.model.Planets;

public interface PlanetsRepository extends MongoRepository<Planets, String> {
	
	List<Planets> findByName(String name);

}
