package it.univpm.app.ticketmaster.filter;

import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

public class EventsFilter 
{
	/**
	 * vettore contenente tutti gli eventi presenti nel json
	 */
	static Vector<Event> events = new Vector<Event>();
	
	/**
	 * vettore contenente tutti gli stati presenti nel json
	 */
	static Vector<String> states = new Vector<String>();
	
	/**
	 * vettore contenente tutte le città presenti nel json
	 */
	static Vector<String> cities = new Vector<String>();
	
	/**
	 * vettore contenente tutti i segmenti presenti nel json
	 */
	static Vector<String> segments = new Vector<String>();
	
	/**
	 * vettore contenente tutti i generi presenti nel json
	 */
	static Vector<String> genres = new Vector<String>();
	
	
	public static Vector<Event> getEvents() {
		return events;
	}

	/**
	 * metodo static (che verrà utilizzato nella chiamata all'api) che riempe i vettori sopracitati 
	 * leggendo il vettore di tutti gli eventi che viene passato per parametro
	 * 
	 * @param e Vettore di tutti gli eventi 
	 */
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
	
	/*
	public static void addEvent(Event e) {
		events.add(e);
		addInformation(e);
	}
	*/
	
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
	
	/**
	 * Metodo static che restituisce il vettore di tutti gli eventi dopo che l'utente ha inserito i filtri,
	 * che verrà preso come parametro nelle rotte della classe EventsController
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param eventsToFilter Vettore di eventi da filtrare 
	 * 
	 * @return filteredEvents Vettore di tutti gli eventi filtrati
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 */
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
