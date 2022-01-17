package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;



/**
 * TODO 
 *
 */
public class JSONEvents extends JSONBuilder
{
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events
	 * 
	 * @param listEvent Lista di eventi 
	 * 
	 * @see it.univpm.app.ticketmaster.controller.*
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
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.*
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerStates(Vector<Event> events, Vector<String> states)
	{		
		JSONObject obj = new JSONObject();
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String state;
		
		for(int i=0; i < states.size(); i++)
		{
			 state = states.get(i);
			
			 filter.setStates(state);
			 eventsApp = filter.getFilteredEvents(events);
		
			 if(eventsApp.size() > 0)
			 {
				 obj.put(state, this.getJSONObjectEvents(eventsApp));
			 }
		}
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/cities
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.*
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerCities(Vector<Event> events, Vector<String> cities)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String city;
		
		for(int i=0; i < cities.size(); i++)
		{
			 city = cities.get(i);
			
			 filter.setCities(city);
			 eventsApp = filter.getFilteredEvents(events);
		
			 if(eventsApp.size() > 0)
			 {
				 obj.put(city, this.getJSONObjectEvents(eventsApp));
			 }
		}		
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/segments
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.*
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerSegments(Vector<Event> events, Vector<String> segments)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String segment;
		
		for(int i=0; i < segments.size(); i++)
		{
			 segment = segments.get(i);
			
			 filter.setSegment(segment);
			 eventsApp = filter.getFilteredEvents(events);
		
			 if(eventsApp.size() > 0)
			 {
				 obj.put(segment, this.getJSONObjectEvents(eventsApp));
			 }
		}
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/genres
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.*
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerGenres(Vector<Event> events,  Vector<String> genres)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String genre;
		
		for(int i=0; i < genres.size(); i++)
		{
			 genre = genres.get(i);
			
			 filter.setGenres(genre);
			 eventsApp = filter.getFilteredEvents(events);
		
			 if(eventsApp.size() > 0)
			 {
				 obj.put(genre, this.getJSONObjectEvents(eventsApp));
			 }
		}		
		
		return obj;
	}
	
}
