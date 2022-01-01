package it.univpm.app.ticketmaster.filter;

import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

public class EventsFilter 
{
	static Vector<Event> events = new Vector<Event>();
	static Vector<String> states = new Vector<String>();
	static Vector<String> cities = new Vector<String>();
	static Vector<String> segments = new Vector<String>();
	static Vector<String> genres = new Vector<String>();
	
	
	public static Vector<Event> getEvents() {
		return events;
	}

	public static void setEvents(Vector<Event> e) {
		events = e;
		
		for(int i = 0; i<events.size(); i++)
			addInformation(events.get(i));
	}
	
	public static Vector<String> getStates() {
		return states;
	}

	public static Vector<String> getCities() {
		return cities;
	}

	public static Vector<String> getSegments() {
		return segments;
	}

	public static Vector<String> getGenres() {
		return genres;
	}
	
	
	
	public static void addEvent(Event e) {
		events.add(e);
		addInformation(e);
	}
	
	private static void addInformation(Event e) {
		addState(e.getState());
		addCity(e.getCity());
		addSegment(e.getSegment());
		addGenre(e.getGenre());
	}
		

	private static void addState(String state) {
		if(!states.contains(state))
			states.add(state);
	}
	
	private static void addCity(String city) {
		if(!cities.contains(city))
			cities.add(city);
	}
	
	private static void addSegment(String segment) {
		if(!segments.contains(segment))
			segments.add(segment);
	}
	
	private static void addGenre(String genre) {
		if(!genres.contains(genre))
			genres.add(genre);
	}
	
	
	
	
	public static Vector<Event> getFilteredEvents (FilterImpl filter, Vector<Event> eventsToFilter) 
	{
		Vector<Event> filteredEvents = new Vector<Event>();
	
		
		for(int i=0; i<eventsToFilter.size(); i++)
		{			
			Event e = eventsToFilter.get(i);
			
			if(filter.isIncludedEvent(e))
			{
				filteredEvents.add(e);
			}
		}
		
		return filteredEvents;
	}
	
	
}
