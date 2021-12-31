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
	
	
		
	@GetMapping(value = "/cities")
	public void getCities()
	{	
		/*Lista città = */ EventsFilter.getCities();
		
		//KEVIN, se mi costruisci il JSON lo possiamo inserire nel body della risposta HTTP
	}

	
	@GetMapping(value = "/genres")
	public void getGenres()
	{	
		/*Lista generi = */ EventsFilter.getGenres();
		
		//KEVIN, se mi costruisci il JSON lo possiamo inserire nel body della risposta HTTP
	}
	
	/*
	 * 	rotte statistiche
	 */
}
