package it.univpm.app.ticketmaster.parser;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.stats.Stats;

/**
 * Classe che si occupa di costruire i JSONObject restituiti dal controller
 * 
 * @author sup3r
 */
public class JSONBuilder 
{
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events
	 * 
	 * @param listEvent Vettore di eventi 
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectEvents(Vector<Event> listEvent)
	{
		JSONObject obj = new JSONObject();
		obj.put("listEvent", listEvent);
		obj.put("elements", listEvent.size());
		return obj;
	}
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/states
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectEventsPerStates(FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();

		Vector<Event> events;
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 obj.put(state, this.getJSONObjectEvents(events));
			 }
		}
		
		return obj;
	}
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/cities
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectEventsPerCities(FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();

		Vector<Event> events;
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 obj.put(city, this.getJSONObjectEvents(events));
			 }
		}		
		
		return obj;
	}
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/segments
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectEventsPerSegments(FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();

		Vector<Event> events;
		String segment;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 obj.put(segment, this.getJSONObjectEvents(events));
			 }
		}
		
		return obj;
	}
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /events/genres
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectEventsPerGenres(FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();

		Vector<Event> events;
		String genre;
		
		for(int i=0; i<EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			
			 filter.setGenres(genre);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 obj.put(genre, this.getJSONObjectEvents(events));
			 }
		}		
		
		return obj;
	}
	
	/*public JSONObject getJSONObjectStats(int n, double average, String min, String max)
	{
		JSONObject obj = new JSONObject();
		obj.put("numero eventi", n);
		obj.put("media eventi", average);
		obj.put("minimo eventi mensili", min);
		obj.put("massimo eventi mensili", max);
		
		return obj;
	}*/
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectStats(Vector<Event> events, FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		Stats stats = new Stats();
		
		int size = events.size();
		
		obj.put("numero eventi", size);
		obj.put("media eventi", stats.average(size, filter.getPeriod()));
		obj.put("minimo eventi mensili", stats.min(events));
		obj.put("massimo eventi mensili", stats.max(events));

		//obj.put("stato con più eventi", stats.min(events));
		//obj.put("città con più eventi", stats.min(events));
		//obj.put("segmento con più eventi", stats.min(events));
		//obj.put("genere con più eventi", stats.min(events));
		
		return obj;
	}
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/states
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectStatsPerStates(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> events;
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 int size = events.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(events, filter);	
				 obj.put(state, objInt);	
			 }
		}
		
		return obj;
	}
	
	public void maxMinPerStates(FilterImpl filter)
	{
		Vector<Event> events;
		String state;
		
		Stats stats = new Stats();		
		
		int [] counter = new int[EventsFilter.getStates().size()];
		
		for(int i = 0; i < counter.length ; i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 counter[i] = events.size();			
		}
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);
		stats.average(maxIndex, filter.getPeriod());
		stats.average(minIndex, filter.getPeriod());
		
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/cities
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectStatsPerCities(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> events;
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 int size = events.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(events, filter);
				 obj.put(city, objInt);
			 }
		}
		
		return obj;
	}
		
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/segments
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectStatsPerSegments(FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> events;
		String segment;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 int size = events.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(events, filter);
				 obj.put(segment, objInt);
			 }
		}
		
		return obj;
	}
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/genres
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	public JSONObject getJSONObjectStatsPerGenres(FilterImpl filter)
	{			
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> events;
		String genre;
		
		for(int i = 0; i < EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			 objInt = new JSONObject();
			
			 filter.setGenres(genre);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
			 
			 int size = events.size();
		
			 if(size > 0)
			 {						 
				 obj.put(genre, objInt);	
			 }
		}

		return obj;
	}
	
	
	public JSONObject getJSONObjectError(Error err)
	{
		JSONObject obj = new JSONObject();
		obj.put("cause", err.getCause());
		obj.put("message", err.getMessage());
		return obj;
	}
	
	
	public JSONObject getJSONObjectError(String err)
	{
		JSONObject obj = new JSONObject();
		obj.put("error", err);
		return obj;
	}
	
	
}