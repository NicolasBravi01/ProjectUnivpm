package it.univpm.app.ticketmaster.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;
import it.univpm.app.ticketmaster.filter.EventsFilter;

/**
 * Controller delle rotte /list
 * 
 * @author sup3r
 */
@RestController
public class ListController 
{	
	
	/**
	 * Metodo associato alla rotta get /list/states, 
	 * che restituisce la lista degli stati
	 */
	@GetMapping(value = "/list/states")
	public JSONObject getStates()
	{	
		JSONBuilder jB = new JSONBuilder();	
		JSONObject response;
		
		try
		{
			if(EventsFilter.getStates().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jB.getJSONObjectStates();
		}
		catch(ApiConnectionException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/cities, 
	 * che restituisce la lista delle città
	 */
	@GetMapping(value = "/list/cities")
	public JSONObject getCities()
	{			
		JSONBuilder jB = new JSONBuilder();	
		JSONObject response;
		
		try
		{
			if(EventsFilter.getCities().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jB.getJSONObjectCities();
		}
		catch(ApiConnectionException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		return response;
	}

	
	/**
	 * Metodo associato alla rotta get /list/segments, 
	 * che restituisce la lista dei segmenti
	 */
	@GetMapping(value = "/list/segments")
	public JSONObject getSegments()
	{	
		JSONBuilder jB = new JSONBuilder();
		JSONObject response;
		
		try
		{
			if(EventsFilter.getSegments().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jB.getJSONObjectSegments();
		}
		catch(ApiConnectionException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/genres, 
	 * che restituisce la lista dei generi
	 */
	@GetMapping(value = "/list/genres")
	public JSONObject getGenres()
	{	
		JSONBuilder jB = new JSONBuilder();
		JSONObject response;
		
		try
		{
			if(EventsFilter.getGenres().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jB.getJSONObjectGenres();
		}
		catch(ApiConnectionException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
}
	
