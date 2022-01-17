package it.univpm.app.ticketmaster.controller;

import java.time.format.DateTimeParseException;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.app.ticketmaster.JSONHandler.JSONEvents;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.NoEventsException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

/**
 * Controller delle rotte /events
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
@RestController
public class EventsController extends Controller
{	
	
	Vector<Event> eventsToFilter = this.ticketmasterService.getEvents();

	
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
		JSONEvents jE = new JSONEvents();		
		Filter filter;
		Vector<Event> filteredEvents;
		
		JSONObject response;		
		
		try
		{
			if(this.eventsToFilter.isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, genres);
			filteredEvents = filter.getFilteredEvents(this.eventsToFilter);	
			
			if(filteredEvents.isEmpty())
				throw new NoEventsException("There are not events with your filters");
			
			response = jE.getJSONObjectEvents(filteredEvents);
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jE.getJSONObjectError(e.getMessage());
		}
		catch(Exception e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
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
		JSONEvents jE = new JSONEvents();		
		Filter filter;
		Vector<Event> filteredEvents;
		
		JSONObject response;		
		
		try
		{
			if(this.eventsToFilter.isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, "", period, segment, genres);
			filteredEvents = filter.getFilteredEvents(this.eventsToFilter);	
			
			if(filteredEvents.isEmpty())
				throw new NoEventsException("There are not events with your filters");
			
			response = jE.getJSONObjectEventsPerStates(filteredEvents, ticketmasterService.getStates());
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
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
		JSONEvents jE = new JSONEvents();		
		Filter filter;
		Vector<Event> filteredEvents;
		
		JSONObject response;		
		
		try
		{
			if(this.eventsToFilter.isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, genres);
			filteredEvents = filter.getFilteredEvents(this.eventsToFilter);	
			
			if(filteredEvents.isEmpty())
				throw new NoEventsException("There are not events with your filters");
			
			response = jE.getJSONObjectEventsPerCities(filteredEvents, ticketmasterService.getCities());
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
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
		JSONEvents jE = new JSONEvents();		
		Filter filter;
		Vector<Event> filteredEvents;
		
		JSONObject response;		
		
		try
		{
			if(this.eventsToFilter.isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states ,cities ,period ,segment , "");
			filteredEvents = filter.getFilteredEvents(this.eventsToFilter);	
			
			if(filteredEvents.isEmpty())
				throw new NoEventsException("There are not events with your filters");

			response = jE.getJSONObjectEventsPerSegments(filteredEvents, ticketmasterService.getSegments());
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
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
		JSONEvents jE = new JSONEvents();		
		Filter filter;
		Vector<Event> filteredEvents;
		
		JSONObject response;		
		
		try
		{
			if(this.eventsToFilter.isEmpty())
				throw new ApiConnectionException("Failed Api Connection, try again");
			
			filter = new Filter(states, cities, period, segment, genres);
			filteredEvents = filter.getFilteredEvents(this.eventsToFilter);	
			
			if(filteredEvents.isEmpty())
				throw new NoEventsException("There are not events with your filters");

			response = jE.getJSONObjectEventsPerGenres(filteredEvents, ticketmasterService.getGenres());
		}
		catch(ApiConnectionException | NoEventsException | DateTimeParseException | NullDateException | IncorrectOrderOfDatesException e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
		}
		catch(Exception e)
		{
			response = jE.getJSONObjectError(e.getMessage()); 
		}
		
		return response;
	}
	
}
	
