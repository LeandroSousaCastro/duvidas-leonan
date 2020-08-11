package com.starwars.planets.service.interfaces;

import java.util.List;
import java.util.Map;

import com.starwars.planets.model.Planets;

public interface PlanetsServiceInterface {

	public List<Planets> findAll();
	
	public Planets findById(String id);
	
	public List<Planets> findByName(String name);
	
	public Planets save(Planets planet);
	
	public void deleteById(String id);
	
	public Map<String, Object> getAllPaged(int pageNo, int limit, String sortBy);
}
