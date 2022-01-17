package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;


/**
 * Classe che contiene i metodi per costruire i JSONObject usati nelle rotte /events
 * 
 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
 * @see it.univpm.app.ticketmaster.controller.EventsController
 */
public class JSONEvents extends JSONBuilder
{
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events
	 * 
	 * @param listEvent Lista di eventi 
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEvents(Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		obj.put("list events", events);
		obj.put("number events", events.size());
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/states
	 * 
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param states Lista di stringhe contenenti i nomi degli stati
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerStates(Vector<Event> eventsToFilter, Vector<String> states)
	{		
		JSONObject obj = new JSONObject();
		Filter filter = new Filter();
		Vector<Event> filteredEvents;
		
		String state;
		
		for(int i=0; i < states.size(); i++)
		{
			 state = states.get(i);
			
			 filter.setStates(state);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 if(filteredEvents.size() > 0)
			 {
				 obj.put(state, this.getJSONObjectEvents(filteredEvents));
			 }
		}
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/cities
	 * 
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param cities Lista di stringhe contenenti i nomi delle città
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerCities(Vector<Event> eventsToFilter, Vector<String> cities)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> filteredEvents;
		
		String city;
		
		for(int i=0; i < cities.size(); i++)
		{
			 city = cities.get(i);
			
			 filter.setCities(city);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 if(filteredEvents.size() > 0)
			 {
				 obj.put(city, this.getJSONObjectEvents(filteredEvents));
			 }
		}		
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/segments
	 * 
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param segments Lista di stringhe contenenti i nomi dei segmenti
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerSegments(Vector<Event> eventsToFilter, Vector<String> segments)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> filteredEvents;
		
		String segment;
		
		for(int i=0; i < segments.size(); i++)
		{
			 segment = segments.get(i);
			
			 filter.setSegment(segment);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 if(filteredEvents.size() > 0)
			 {
				 obj.put(segment, this.getJSONObjectEvents(filteredEvents));
			 }
		}
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/genres
	 * 
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param genres Lista di stringhe contenenti i nomi dei generi
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerGenres(Vector<Event> eventsToFilter,  Vector<String> genres)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> filteredEvents;
		
		String genre;
		
		for(int i=0; i < genres.size(); i++)
		{
			 genre = genres.get(i);
			
			 filter.setGenres(genre);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 if(filteredEvents.size() > 0)
			 {
				 obj.put(genre, this.getJSONObjectEvents(filteredEvents));
			 }
		}		
		
		return obj;
	}
	
}
