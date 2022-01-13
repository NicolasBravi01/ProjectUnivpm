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
import it.univpm.app.ticketmaster.exception.NoEventsException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

/**
 * Controller delle rotte /events
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
@RestController
public class EventsController 
{	
	
	/**
	 * Metodo associato alla rotta get /events. 
	 * Restituisce il JSONObject contenente la lista di eventi filtrati secondo i parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events")
	public JSONObject getEvents(@RequestParam(name="states", defaultValue="") String states,
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
			
			if(events.isEmpty())
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEvents(events);
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/states.
	 * Restituisce il JSONObject contenente la lista di eventi raggruppati per stati filtrati secondo
	 * i parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/states")
	public JSONObject getEventsPerStates(@RequestParam(name="states", defaultValue="") String states,
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
			
			if(events.isEmpty())
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEventsPerStates(events);
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/cities.
	 * Restituisce il JSONObject contenente la lista di eventi raggruppati per città filtrati secondo
	 * i parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente	 
	 * * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/cities")
	public JSONObject getEventsPerCities(@RequestParam(name="states", defaultValue="") String states,
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
			
			if(events.isEmpty())
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEventsPerCities(events);
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/segments.
	 * Restituisce il JSONObject contenente la lista di eventi raggruppati per segmenti filtrati secondo
	 * i parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente	
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/segments")
	public JSONObject getEventsPerSegments(@RequestParam(name="states", defaultValue="") String states,
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
			
			filter = new Filter(states ,cities ,period ,segment , "");
			events = EventsFilter.getFilteredEvents(filter);	
			
			if(events.isEmpty())
				throw new NoEventsException("There are not events with your filters");

			response = jB.getJSONObjectEventsPerSegments(events);
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/genres.
	 * Restituisce il JSONObject contenente la lista di eventi raggruppati per generi filtrati secondo
	 * i parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/genres")
	public JSONObject getEventsPerGenres(@RequestParam(name="states", defaultValue="") String states,
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
			
			if(events.isEmpty())
				throw new NoEventsException("There are not events with your filters");

			response = jB.getJSONObjectEventsPerGenres(events);
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	
