package it.univpm.app.ticketmaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.apiConnection.ticketmasterConnection;
import it.univpm.app.ticketmaster.filter.FilterImpl;

@RestController
public class EventsController 
{

	@GetMapping(value = "/events")
	public void getEvents(@RequestParam(name="state", defaultValue="") String state,
						  @RequestParam(name="city", defaultValue="") String city,
						  @RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="genre", defaultValue="") String genre,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		System.out.println("heu");
		ticketmasterConnection.getJSONEvents();
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
