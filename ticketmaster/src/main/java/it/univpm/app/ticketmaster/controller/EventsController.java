package it.univpm.app.ticketmaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.apiConnection.ticketmasterConnection;

@RestController
public class EventsController 
{

	@GetMapping(value = "/events")
	public void getEvents(@RequestParam(name="name", defaultValue="Prova") String event) 
	{	
		System.out.println(event);
		ticketmasterConnection.getJSONEvents();
	}

}
