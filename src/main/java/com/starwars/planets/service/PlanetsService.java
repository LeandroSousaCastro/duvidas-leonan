package com.starwars.planets.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.starwars.planets.model.Planets;
import com.starwars.planets.repositories.PlanetsRepository;
import com.starwars.planets.service.interfaces.PlanetsServiceInterface;

@Service
public class PlanetsService implements PlanetsServiceInterface {
	
	@Autowired
	private PlanetsRepository planetsRepository;

	@Override
	public List<Planets> findAll() {
		return this.planetsRepository.findAll();
	}

	@Override
	public Planets findById(String id) {
		return this.planetsRepository
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Planet not found"));
	}

	@Override
	public Planets save(Planets planet) {
		return this.planetsRepository.save(planet);
	}

	@Override
	public List<Planets> findByName(String name) {
		return this.planetsRepository.findByName(name);
	}

	@Override
	public void deleteById(String id) {
		this.planetsRepository.deleteById(id);
	}

	@Override
	public Map<String, Object> getAllPaged(int pageNo, int limit, String sortBy) {
		Map<String, Object> response = new HashMap<String, Object>();
		Sort sort = Sort.by(sortBy);
		Pageable page = PageRequest.of(pageNo, limit, sort);
		Page<Planets> planetsPage = planetsRepository.findAll(page);
		response.put("data", planetsPage.getContent());
		response.put("Total Elements", planetsPage.getTotalElements());
		response.put("Total Pages", planetsPage.getTotalPages());
		response.put("Current Page", planetsPage.getNumber());
		return response;
	}
	
}
