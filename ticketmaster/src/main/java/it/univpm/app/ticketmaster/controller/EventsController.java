package it.univpm.app.ticketmaster.controller;

import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.apiConnection.ticketmasterConnection;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;

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
	public void getEvents(@RequestParam(name="state", defaultValue="") String state,
						  @RequestParam(name="city", defaultValue="") String city,
						  @RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="genre", defaultValue="") String genre,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		ticketmasterConnection.getJSONEvents();
		
		FilterImpl filter = new FilterImpl(state, city, period, segment, genre);
		filteredEvents = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
		//KEVIN, se mi costruisci il JSON di risposta con gli eventi in *filterdEvents* lo possiamo inserire nel body della risposta HTTP
	}
	

	@PostMapping(value = "/events")
	public void getEvents(@RequestBody FilterImpl body)
	{	
		System.out.println("heiii");
		ticketmasterConnection.getJSONEvents();
	}
	
	
	/*@PostMapping(value = "/events")
	public void getEvents(@RequestBody String body)
	{	
		System.out.println("heiii");
		ticketmasterConnection.getJSONEvents();
	}*/
	
}
