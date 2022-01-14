package it.univpm.app.ticketmaster.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONList;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;
import it.univpm.app.ticketmaster.filter.EventsFilter;

/**
 * Controller delle rotte /list
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
@RestController
public class ListController 
{	
	
	/**
	 * Metodo associato alla rotta get /list/states.
	 * Restituisce la lista degli stati
	 */
	@GetMapping(value = "/list/states")
	public JSONObject getStates()
	{	
		JSONList jL = new JSONList();	
		JSONObject response;
		
		try
		{
			if(EventsFilter.getStates().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectStates();
		}
		catch(ApiConnectionException e)
		{
			response = jL.getJSONObjectError(e.getMessage()); 
		}
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/cities.
	 * Restituisce la lista delle città
	 */
	@GetMapping(value = "/list/cities")
	public JSONObject getCities()
	{			
		JSONList jL = new JSONList();	
		JSONObject response;
		
		try
		{
			if(EventsFilter.getCities().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectCities();
		}
		catch(ApiConnectionException e)
		{
			response = jL.getJSONObjectError(e.getMessage()); 
		}
		return response;
	}

	
	/**
	 * Metodo associato alla rotta get /list/segments.
	 * Restituisce la lista dei segmenti
	 */
	@GetMapping(value = "/list/segments")
	public JSONObject getSegments()
	{	
		JSONList jL = new JSONList();
		JSONObject response;
		
		try
		{
			if(EventsFilter.getSegments().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectSegments();
		}
		catch(ApiConnectionException e)
		{
			response = jL.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/genres.
	 * Restituisce la lista dei generi
	 */
	@GetMapping(value = "/list/genres")
	public JSONObject getGenres()
	{	
		JSONList jL = new JSONList();
		JSONObject response;
		
		try
		{
			if(EventsFilter.getGenres().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectGenres();
		}
		catch(ApiConnectionException e)
		{
			response = jL.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
}
	
