package com.starwars.planets.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planets {

	@Id
	private String id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Climate is mandatory")
	private String climate;
	
	@NotBlank(message = "Terrain is mandatory")
	private String terrain;
	
	private Integer qtyAppearMovies;
	
	private List<Movies> movies;

	public Planets() {
		super();
	}

	public Planets(String name, String climate, String terrain, Integer qtyAppearMovies) {
		super();
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.qtyAppearMovies = qtyAppearMovies;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getQtyAppearMovies() {
		return qtyAppearMovies;
	}

	public void setQtyAppearMovies(Integer qtyAppearMovies) {
		this.qtyAppearMovies = qtyAppearMovies;
	}

	public List<Movies> getMovies() {
		return movies;
	}

	public void setMovies(List<Movies> movies) {
		this.movies = movies;
	}

}
