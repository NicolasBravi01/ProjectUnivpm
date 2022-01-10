package it.univpm.app.ticketmaster.controller;

import java.time.format.DateTimeParseException;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.NoEventsException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

/**
 * Controller delle varie rotte
 * 
 * @author sup3r
 */
@RestController
public class EventsController 
{	
	
	/**
	 * Metodo associato alla rotta get /events, 
	 * che restituisce il JSONObject contenente la lista di eventi filtrati secondo i parametri forniti dall'utente
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
			filter = new Filter(states, cities, period, segment, genres);
			events = EventsFilter.getFilteredEvents(filter);	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEvents(events);
		}
		catch(NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/states, 
	 * che restituisce il JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/states")
	public JSONObject getEventsPerStates(@RequestParam(name="period", defaultValue="") String period)
	{			
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;		
		
		try
		{
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEventsPerStates(events);
		}
		catch(NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/cities, 
	 * che restituisce il JSONObject contenente la lista di eventi raggruppati per città in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/cities")
	public JSONObject getEventsPerCities(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;		
		
		try
		{
			filter = new Filter(states, "", period, "", "");
			events = EventsFilter.getFilteredEvents(filter);	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEventsPerCities(events);
		}
		catch(NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/segments, 
	 * che restituisce la lista di eventi raggruppati per segmenti in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/segments")
	public JSONObject getEventsPerSegments(@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;		
		
		try
		{
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");

			response = jB.getJSONObjectEventsPerSegments(events);
		}
		catch(NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /events/genres, 
	 * che restituisce la lista di eventi raggruppati per generi in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/events/genres")
	public JSONObject getEventsPerGenres(@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;		
		
		try
		{
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");

			response = jB.getJSONObjectEventsPerGenres(events);
		}
		catch(NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	
			
			response = jB.getJSONObjectAllStats(filter, events);
		}
		catch(DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			filter = new Filter(states, "", period, "", "");
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerStates(filter, events);
		}
		catch(DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/cities")
	public JSONObject getStatsPerCities(@RequestParam(name="cities", defaultValue="") String cities,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			filter = new Filter("", cities, period, "", "");
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerCities(filter, events);
		}
		catch(DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/segments")
	public JSONObject getStatsPerSegments(@RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			filter = new Filter(period);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerSegments(filter, events);
		}
		catch(DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente le informazioni attese oppure un messaggio di errore
	 */
	@GetMapping(value = "/stats/genres")
	public JSONObject getStatsPerGenres(@RequestParam(name="genres", defaultValue="") String genres,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		JSONBuilder jB = new JSONBuilder();		
		Filter filter;
		Vector<Event> events;
		
		JSONObject response;	
		
		try
		{
			filter = new Filter("" , "", period, "", genres);
			events = EventsFilter.getFilteredEvents(filter);	

			response = jB.getJSONObjectStatsPerGenres(filter, events);
		}
		catch(DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
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
	 * Metodo associato alla rotta get /list/states, 
	 * che restituisce la lista degli stati
	 */
	@GetMapping(value = "/list/states")
	public JSONObject getStates()
	{	
		JSONBuilder jB = new JSONBuilder();	
		return jB.getJSONObjectStates();
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/cities, 
	 * che restituisce la lista delle città
	 */
	@GetMapping(value = "/list/cities")
	public JSONObject getCities()
	{			
		JSONBuilder jB = new JSONBuilder();	
		return jB.getJSONObjectCities();
	}

	
	/**
	 * Metodo associato alla rotta get /list/segments, 
	 * che restituisce la lista dei segmenti
	 */
	@GetMapping(value = "/list/segments")
	public JSONObject getSegments()
	{	
		JSONBuilder jB = new JSONBuilder();	
		return jB.getJSONObjectSegments();
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/genres, 
	 * che restituisce la lista dei generi
	 */
	@GetMapping(value = "/list/genres")
	public JSONObject getGenres()
	{	
		JSONBuilder jB = new JSONBuilder();	
		return jB.getJSONObjectGenres();
	}
	
	
}
	
