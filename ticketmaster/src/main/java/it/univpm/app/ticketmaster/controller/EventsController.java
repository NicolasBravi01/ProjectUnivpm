package it.univpm.app.ticketmaster.controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.parser.JsonBuilder;

@RestController
public class EventsController 
{
	Vector<Event> filteredEvents = new Vector<Event>();
	
	
	public Vector<Event> getFilteredEvents() {
		return filteredEvents;
	}
	public void setFilteredEvents(Vector<Event> filteredEvents) {
		this.filteredEvents = filteredEvents;
	}
	
	

	@GetMapping(value = "/events")
	public JSONObject getEvents(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="cities", defaultValue="") String cities,
						  @RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="genres", defaultValue="") String genres,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, cities, period, segment, genres);
		JsonBuilder jB = new JsonBuilder();
		return jB.build(EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents()));
	}
	
	
	@GetMapping(value = "/stats/cities")
	public JSONObject getStats(@RequestParam(name="cities", defaultValue="") String cities,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", cities, period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		JSONObject joInt;
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			 joInt = new JSONObject();
			
			 filter.setCities(city);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
		
				 joInt.put("numero eventi",  events.size());
				 joInt.put("media eventi", 0);	//TODO
				 
				 jo.put(city, joInt);				 
			 }
		}
		
		return jo;
	}
	
	
	@GetMapping(value = "/events/cities")
	public JSONObject getEventsForCities(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(city, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	@GetMapping(value = "/events/states")
	public JSONObject getEventsForStates(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(state, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	
	@GetMapping(value = "/events/segments")
	public JSONObject getEventsForCities(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String segment;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(segment, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	@GetMapping(value = "/events/genres")
	public JSONObject getEventsForGenres(@RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, segment, "");
		JsonBuilder jB = new JsonBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String genre;
		
		for(int i=0; i<EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			
			 filter.setGenres(genre);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(genre, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	
	
	
	@GetMapping(value = "/states")
	public Vector<String> getStates()
	{	
		Vector<String> states = EventsFilter.getStates();
		return states;
	}
	
	
	@GetMapping(value = "/cities")
	public Vector<String> getCities()
	{	
		Vector<String> cities = EventsFilter.getCities();
		return cities;
	}

	
	@GetMapping(value = "/segments")
	public Vector<String> getSegments()
	{	
		Vector<String> segments = EventsFilter.getSegments();
		return segments;
	}
	
	
	@GetMapping(value = "/genres")
	public Vector<String> getGenres()
	{	
		Vector<String> genres = EventsFilter.getGenres();
		return genres;
	}
	
	/*
	 * 	rotte statistiche
	 */
}
