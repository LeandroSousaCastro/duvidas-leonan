package com.starwars.planets.model;

public class Movies {

	private String link;
	
	public Movies() {
		super();
	}

	public Movies(String link) {
		super();
		this.setLink(link);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
