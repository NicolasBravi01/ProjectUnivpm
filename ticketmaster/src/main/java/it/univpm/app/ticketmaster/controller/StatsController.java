package it.univpm.app.ticketmaster.controller;

import java.time.format.DateTimeParseException;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONStats;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

/**
 * Controller delle rotte /stats
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
@RestController
public class StatsController 
{	
		
	/**
	 * Metodo associato alla rotta get /stats.
	 * Restituisce il JSONObject contenente le statistiche generali in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats")
	public JSONObject getStats(@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONStats jS = new JSONStats();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	
			
			response = jS.getJSONObjectAllStats(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/states.
	 * Restituisce il JSONObject contenente le statistiche relative agli stati scelti dall'utente in un certo periodo
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/states")
	public JSONObject getStatsPerStates(@RequestParam(name="states", defaultValue="") String states,
										@RequestParam(name="segment", defaultValue="") String segment,
										@RequestParam(name="genres", defaultValue="") String genres,
										@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONStats jS = new JSONStats();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, "", period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jS.getJSONObjectStatsPerStates(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/cities.
	 * Restituisce il JSONObject contenente le statistiche relative agli stati scelti dall'utente in un certo periodo
	 *  
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/cities")
	public JSONObject getStatsPerCities(@RequestParam(name="states", defaultValue="") String states,
	        							@RequestParam(name="cities", defaultValue="") String cities,
	        							@RequestParam(name="segment", defaultValue="") String segment,
	        							@RequestParam(name="genres", defaultValue="") String genres,
	        							@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONStats jS = new JSONStats();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jS.getJSONObjectStatsPerCities(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
				
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/segments.
	 * Restituisce il JSONObject contenente le statistiche relative ai segmenti scelti dall'utente in un certo periodo
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/segments")
	public JSONObject getStatsPerSegments(@RequestParam(name="states", defaultValue="") String states,
										  @RequestParam(name="cities", defaultValue="") String cities,
										  @RequestParam(name="segment", defaultValue="") String segment,
										  @RequestParam(name="period", defaultValue="") String period)
	{	
		JSONStats jS = new JSONStats();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, "");
			events = EventsFilter.getFilteredEvents(filter);	

			response = jS.getJSONObjectStatsPerSegments(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
	
	
	/**
	 * Metodo associato alla rotta get /stats/genres.
	 * Restituisce il JSONObject contenente le statistiche relative ai generi scelti dall'utente in un certo periodo
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/genres")
	public JSONObject getStatsPerGenres(@RequestParam(name="states", defaultValue="") String states,
	        							@RequestParam(name="cities", defaultValue="") String cities,
	        							@RequestParam(name="segment", defaultValue="") String segment,
	        							@RequestParam(name="genres", defaultValue="") String genres,
	        							@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONStats jS = new JSONStats();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states , cities, period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jS.getJSONObjectStatsPerGenres(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jS.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
		
}
	
