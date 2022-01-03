package it.univpm.app.ticketmaster.controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.parser.JSONBuilder;

/**
 * Controller delle varie rotte
 * 
 * @author sup3r
 */
@RestController
public class EventsController 
{
	Vector<Event> filteredEvents = new Vector<Event>();
	
	public Vector<Event> getFilteredEvents() {
		return filteredEvents;
	}
	public void setFilteredEvents(Vector<Event> filteredEvents) {
		this.filteredEvents = filteredEvents;
	}
	
	/**
	 * Metodo associato alla rotta get /events, 
	 * che è in grado di generare filtri in base ai parametri forniti dall'utente
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param cities Stringa contenente le città di interesse per l'utente
	 * @param segment Stringa contenente i segmenti di interesse per l'utente
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
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
		FilterImpl filter = new FilterImpl(states, cities, period, segment, genres);
		JSONBuilder jB = new JSONBuilder();
		
		JSONObject response = jB.getJSONObjectEvents(EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents()));
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /stats 
	 * che è in grado di generare le statistiche per tutti gli eventi in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return response JSONObject contenente le statistiche degli eventi in un certo periodo
	 */
	@GetMapping(value = "/stats")
	public JSONObject getStats(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(period);
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		JSONObject response = new JSONObject();		
		
		int size = events.size();
				
		//if(size > 0)	//altrimenti genera Errore					 
			//response = jB.getJSONObjectStats(size, average(size, period), min(events), max(events));
		/*
		 * 	else
		 * 		response = getJSONObjectError("Lista eventi vuota");
		 */
		
		response = jB.getJSONObjectAllStats(events, filter);
		
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
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le statistiche degli stati filtrati dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/states")
	public JSONObject getStatsPerStates(@RequestParam(name="states", defaultValue="") String states,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
				
		JSONObject response = jB.getJSONObjectStatsPerStates(filter);
		
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
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le statistiche delle città filtrate dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/cities")
	public JSONObject getStatsPerCities(@RequestParam(name="cities", defaultValue="") String cities,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", cities, period, "", "");
		JSONBuilder jB = new JSONBuilder();
				
		JSONObject response = jB.getJSONObjectStatsPerCities(filter);
		
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /stats/segment, 
	 * che è in grado di generare le statistiche per il segmento scelto dall'utente in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * 
	 * @return response JSONObject contenente le statistiche del segmento scelto dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/segments")
	public JSONObject getStatsPerSegments(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(period);
		JSONBuilder jB = new JSONBuilder();
				
		JSONObject response = jB.getJSONObjectStatsPerSegments(filter);
		
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
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return response JSONObject contenente le statistiche dei generi scelti dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/genres")
	public JSONObject getStatsPerGenres(@RequestParam(name="genres", defaultValue="") String genres,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("" , "", period, "", genres);
		JSONBuilder jB = new JSONBuilder();
		
		JSONObject response = jB.getJSONObjectStatsPerGenres(filter);
		
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /events/states, 
	 * che è restituisce la lista di eventi raggruppati per stati
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
	 */
	@GetMapping(value = "/events/states")
	public JSONObject getEventsPerStates(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(period);
		JSONBuilder jB = new JSONBuilder();

		JSONObject response = jB.getJSONObjectEventsPerStates(filter);
		
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /events/cities, 
	 * che è restituisce la lista di eventi raggruppati per città
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
	 */
	@GetMapping(value = "/events/cities")
	public JSONObject getEventsPerCities(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		JSONObject response = jB.getJSONObjectEventsPerCities(filter);
		
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /events/segments, 
	 * che è restituisce la lista di eventi raggruppati per segmenti
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per segmenti in un certo periodo
	 */
	@GetMapping(value = "/events/segments")
	public JSONObject getEventsPerSegments(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(period);
		JSONBuilder jB = new JSONBuilder();
		
		JSONObject response = jB.getJSONObjectEventsPerSegments(filter);
		
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /events/genres, 
	 * che è restituisce la lista di eventi raggruppati per genres
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return response JSONObject contenente la lista di eventi raggruppati per generi in un certo periodo
	 */
	@GetMapping(value = "/events/genres")
	public JSONObject getEventsPerGenres(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(period);		
		JSONBuilder jB = new JSONBuilder();
		
		JSONObject response = jB.getJSONObjectEventsPerGenres(filter);
		
		return response;
	}
	
	/**
	 * Metodo associato alla rotta get /states, 
	 * che è restituisce la lista degli stati
	 */
	@GetMapping(value = "/states")
	public Vector<String> getStates()
	{	
		Vector<String> states = EventsFilter.getStates();
		return states;
	}
	
	/**
	 * Metodo associato alla rotta get /cities, 
	 * che è restituisce la lista delle città
	 */
	@GetMapping(value = "/cities")
	public Vector<String> getCities()
	{	
		Vector<String> cities = EventsFilter.getCities();
		return cities;
	}

	/**
	 * Metodo associato alla rotta get /segments, 
	 * che è restituisce la lista dei segmenti
	 */
	@GetMapping(value = "/segments")
	public Vector<String> getSegments()
	{	
		Vector<String> segments = EventsFilter.getSegments();
		return segments;
	}
	
	/**
	 * Metodo associato alla rotta get /genres, 
	 * che è restituisce la lista dei generi
	 */
	@GetMapping(value = "/genres")
	public Vector<String> getGenres()
	{	
		Vector<String> genres = EventsFilter.getGenres();
		return genres;
	}
	
	
}
	
