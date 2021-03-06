package com.starwars.planets.resources;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.planets.model.Planets;
import com.starwars.planets.service.PlanetsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API REST - Star Wars Movie Planets")
@RestController
@RequestMapping("/planets")
public class PlanetsResource {
	
	@Autowired
	private PlanetsService planetsService;
	
	@ApiOperation(value = "Find all planets")
	@GetMapping(produces = "application/json")
	public List<Planets> findAll() {
		return this.planetsService.findAll();		
	}
	
	@ApiOperation(value = "Find planet by id")
	@GetMapping("/{id}")
	public Planets findById(@PathVariable String id) {		
		return this.planetsService.findById(id);
	}

	@ApiOperation(value = "Find planet by name")
	@GetMapping("/name/{name}")
	public List<Planets> findByName(@PathVariable String name) {
		return this.planetsService.findByName(name);
	}
	
	@ApiOperation(value = "Find all paged planets")
	@GetMapping("/page")
	public Map<String, Object> getAllPaged(
				@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
				@RequestParam(name = "limit", defaultValue = "5") int limit,
				@RequestParam(name = "sortBy", defaultValue = "id") String sortBy
			) {
		return this.planetsService.getAllPaged(pageNo, limit, sortBy);
	}
	
	@ApiOperation(value = "Create planet")
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Validated @RequestBody Planets planet, Errors errors) {
		
		if (!errors.hasErrors()) {
			Planets planetCreated = this.planetsService.save(planet);
			return new ResponseEntity<Planets>(planetCreated, HttpStatus.CREATED);			
		}
		
		return this.responseError(errors);
	}
	
	@ApiOperation(value = "Update planet")
	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody Planets planet, Errors errors) {
		
		if (!errors.hasErrors()) {
			Planets planetUpdated = this.planetsService.save(planet);
			return new ResponseEntity<Planets>(planetUpdated, HttpStatus.OK);			
		}
		
		return this.responseError(errors);
	}
	
	@ApiOperation(value = "Remove planet by id")	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		this.planetsService.deleteById(id);
	}
	
	private ResponseEntity<?> responseError(Errors errors) {
		return ResponseEntity
					.badRequest()
					.body(
						errors.getAllErrors()
							.stream()
							.map(msg -> msg.getDefaultMessage())
							.collect(Collectors.joining(","))
						);
	}

}
