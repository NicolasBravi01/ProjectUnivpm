package it.univpm.app.ticketmaster.model;

import java.util.Vector;

public class City 
{
	private String name;
	private Vector<Event> eventsListPerCity;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Event> getEventsListPerCity() {
		return eventsListPerCity;
	}
	public void setEventsListPerCity(Vector<Event> eventsListPerCity) {
		this.eventsListPerCity = eventsListPerCity;
	}
	
	
	public City(String name) {
		this.name = name;
	}
	
}
