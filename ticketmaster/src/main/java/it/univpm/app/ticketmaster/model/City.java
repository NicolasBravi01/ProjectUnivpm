package it.univpm.app.ticketmaster.model;

import java.util.Vector;

public class City implements ShowEventsStats
{
	private String name;
	private Vector<Event> events = new Vector<Event>(); 
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Event> getEvents() {
		return events;
	}
	public void setEvents(Vector<Event> events) {
		this.events = events;
	}
	
	
	public City(String name) {
		this.name = name;
	}
	
			
	@Override
	public int getNumberEvents() 
	{
		return events.size();
	}
	
	public boolean equals(City city)
	{
		return this.name == city.getName();
	}
	
}
