package it.univpm.app.ticketmaster.controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;
import it.univpm.app.ticketmaster.exception.NoEventsException;
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
	 * che è in grado di generare filtri in base ai parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente il segmento di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject strutturato secondo il model e contenente la lista di tutti gli eventi filtrati dall'utente
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
		catch(NoEventsException e)
		{
			response = jB.getJSONObjectError(e.toString()); 
		}	
		
		return response;
	}
	
	
	
	/**
	 * Metodo associato alla rotta get /events/states, 
	 * che restituisce la lista di eventi raggruppati per stati
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
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
			events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEventsPerStates(events);
		}
		catch(NoEventsException e)
		{
			response = jB.getJSONObjectError(e.toString()); 
		}	
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /events/cities, 
	 * che restituisce la lista di eventi raggruppati per città
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
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
			events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");
			
			response = jB.getJSONObjectEventsPerCities(events);
		}
		catch(NoEventsException e)
		{
			response = jB.getJSONObjectError(e.toString()); 
		}	
		
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /events/segments, 
	 * che restituisce la lista di eventi raggruppati per segmenti
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per segmenti in un certo periodo
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
			events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");

			response = jB.getJSONObjectEventsPerSegments(events);
		}
		catch(NoEventsException e)
		{
			response = jB.getJSONObjectError(e.toString()); 
		}	
		
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /events/genres, 
	 * che restituisce la lista di eventi raggruppati per genres
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per generi in un certo periodo
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
			events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());	
			
			if(events.size() == 0)
				throw new NoEventsException("There are not events with your filters");

			response = jB.getJSONObjectEventsPerGenres(events);
		}
		catch(NoEventsException e)
		{
			response = jB.getJSONObjectError(e.toString()); 
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
	 * @return response JSONObject contenente le statistiche degli eventi in un certo periodo
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
		catch(Exception e)//provvisorio
		{
			response = jB.getJSONObjectError(e.toString()); 
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
	 * @return response JSONObject contenente le statistiche degli stati filtrati dall'utente in un certo periodo
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
		catch(Exception e)//provvisorio
		{
			response = jB.getJSONObjectError(e.toString()); 
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
	 * @return response JSONObject contenente le statistiche delle città filtrate dall'utente in un certo periodo
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
		catch(Exception e)//provvisorio
		{
			response = jB.getJSONObjectError(e.toString()); 
		}	
				
		return response;
	}
	
	
	/**
	 * Metodo associato alla rotta get /stats/segment, 
	 * che è in grado di generare le statistiche per il segmento scelto dall'utente in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le statistiche del segmento scelto dall'utente in un certo periodo
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
		catch(Exception e)//provvisorio
		{
			response = jB.getJSONObjectError(e.toString()); 
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
	 * @return response JSONObject contenente le statistiche dei generi scelti dall'utente in un certo periodo
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
		catch(Exception e)//provvisorio
		{
			response = jB.getJSONObjectError(e.toString()); 
		}
		
		return response;
	}
	
	
	
	
	
	
	/**
	 * Metodo associato alla rotta get /list/states, 
	 * che restituisce la lista degli stati
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/list/states")
	public JSONObject getStates()
	{	
		JSONObject obj = new JSONObject();
		Vector<String> states = EventsFilter.getStates();
		int size = states.size();
		
		obj.put("list states", states);
		obj.put("number states", size);
		
		return obj;
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/cities, 
	 * che restituisce la lista delle città
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/list/cities")
	public JSONObject getCities()
	{			
		JSONObject obj = new JSONObject();
		Vector<String> cities = EventsFilter.getCities();
		int size = cities.size();
		
		obj.put("list cities", cities);
		obj.put("number cities", size);
		
		return obj;
	}

	
	/**
	 * Metodo associato alla rotta get /list/segments, 
	 * che restituisce la lista dei segmenti
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/list/segments")
	public JSONObject getSegments()
	{	
		JSONObject obj = new JSONObject();
		Vector<String> segments = EventsFilter.getSegments();
		int size = segments.size();
		
		obj.put("list segments", segments);
		obj.put("number segments", size);
		
		return obj;
	}
	
	
	/**
	 * Metodo associato alla rotta get /list/genres, 
	 * che restituisce la lista dei generi
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/list/genres")
	public JSONObject getGenres()
	{	
		JSONObject obj = new JSONObject();
		Vector<String> genres = EventsFilter.getGenres();
		int size = genres.size();
		
		obj.put("list genres", genres);
		obj.put("number genres", size);
		
		return obj;
	}
	
	
}
	
