package it.univpm.app.ticketmaster.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONList;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;

/**
 * Controller delle rotte /list
 * 
 * @see it.univpm.app.ticketmaster.controller.Controller
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
@RestController
public class ListController extends Controller
{	

	/**
	 * Metodo associato alla rotta get /list/states.
	 * Restituisce la lista degli stati
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONList
	 */
	@GetMapping(value = "/list/states")
	public JSONObject getStates()
	{	
		JSONList jL = new JSONList();	
		JSONObject response;
		
		try
		{
			if(ticketmasterService.getStates().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectElements(ticketmasterService.getStates());
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
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONList
	 */
	@GetMapping(value = "/list/cities")
	public JSONObject getCities()
	{			
		JSONList jL = new JSONList();	
		JSONObject response;
		
		try
		{
			if(ticketmasterService.getCities().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectElements(ticketmasterService.getCities());
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
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONList
	 */
	@GetMapping(value = "/list/segments")
	public JSONObject getSegments()
	{	
		JSONList jL = new JSONList();
		JSONObject response;
		
		try
		{
			if(ticketmasterService.getSegments().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectElements(ticketmasterService.getSegments());
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
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONList
	 */
	@GetMapping(value = "/list/genres")
	public JSONObject getGenres()
	{	
		JSONList jL = new JSONList();
		JSONObject response;
		
		try
		{
			if(ticketmasterService.getGenres().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			response = jL.getJSONObjectElements(ticketmasterService.getGenres());
		}
		catch(ApiConnectionException e)
		{
			response = jL.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
}
	
