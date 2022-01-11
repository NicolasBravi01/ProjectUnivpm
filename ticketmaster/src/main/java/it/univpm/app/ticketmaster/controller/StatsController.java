package it.univpm.app.ticketmaster.controller;

import java.time.format.DateTimeParseException;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;
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
 */
@RestController
public class StatsController 
{	
		
	/**
	 * Metodo associato alla rotta get /stats 
	 * che è in grado di generare le statistiche per tutti gli eventi in un certo periodo
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
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	
			
			response = jB.getJSONObjectAllStats(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/states, 
	 * che è in grado di generare le statistiche per gli stati scelti dall'utente in un certo periodo
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
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, "", period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerStates(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/cities, 
	 * che è in grado di generare le statistiche per le città scelte dall'utente in un certo periodo
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
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerCities(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
				
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/segment, 
	 * che è in grado di generare le statistiche per tutti i segmenti in un certo periodo
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
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, "");
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerSegments(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
	
	
	/**
	 * Metodo associato alla rotta get /stats/genres, 
	 * che è in grado di generare le statistiche per i generi scelti dall'utente in un certo periodo
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
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			if(EventsFilter.getEvents().isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states , cities, period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerGenres(filter, events);
		}
		catch(ApiConnectionException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jB.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
		
}
	
