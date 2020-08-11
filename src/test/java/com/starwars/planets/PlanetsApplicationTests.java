package com.starwars.planets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.starwars.planets.model.Planets;
import com.starwars.planets.resources.PlanetsResource;
import com.starwars.planets.service.PlanetsService;

@WebMvcTest(controllers = PlanetsResource.class)
class PlanetsApplicationTests {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private PlanetsService planetsService;
	
	@Test
	void findById() throws Exception {
		Planets mockPlanet = new Planets(
				"Tatooine",
				"arid",
				"desert",
				1
			);
		
		when(planetsService.findById(mockPlanet.getId())).thenReturn(mockPlanet);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockPlanetJSON = ow.writeValueAsString(mockPlanet);
		
		mock.perform(get("/planets/" + mockPlanet.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200));
	}
	
	@Test
	void findAll() throws Exception {
		Planets planet = new Planets(
				"Tatooine",
				"arid",
				"desert",
				1
			);
		
		List<Planets> mockPlanet = Arrays.asList(planet);
		
		when(planetsService.findAll()).thenReturn(mockPlanet);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockPlanetJSON = ow.writeValueAsString(mockPlanet);
		
		mock.perform(get("/planets").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is(200))
		.andExpect(content().json(mockPlanetJSON));
	}

	@Test
	void createPlanet() throws Exception {
		Planets mockPlanet = new Planets(
					"Tatooine",
					"arid",
					"desert",
					1
				);
		
		when(planetsService.save(any(Planets.class))).thenReturn(mockPlanet);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String mockPlanetJSON = ow.writeValueAsString(mockPlanet);
		
		mock.perform(post("/planets")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(mockPlanetJSON))
		.andExpect(status().isCreated())
		.andExpect(content().json(mockPlanetJSON));
		
	}
	
	@Test
	void deletePlanet() throws Exception {
		mock.perform(delete("/planets" + "/{id}", new String("5f2b50ed9cd77d13f801f22c"))).andExpect(status().is(204));
	}

}
