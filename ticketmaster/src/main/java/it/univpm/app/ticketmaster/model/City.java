package it.univpm.app.ticketmaster.model;

import java.util.Vector;

public class City 
{
	private String name;
	private static Vector<Event> EventsListPerCity;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public City(String name) {
		this.name = name;
	}
}
