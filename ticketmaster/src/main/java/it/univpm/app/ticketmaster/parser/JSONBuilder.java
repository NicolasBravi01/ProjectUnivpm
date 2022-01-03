package it.univpm.app.ticketmaster.parser;

import java.util.Vector;

import org.json.simple.JSONArray;
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
	@SuppressWarnings("unchecked")
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
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectAllStats(Vector<Event> events, FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt = new JSONObject();
		
		obj = this.getJSONObjectStats(events, filter);
		
		objInt.put("states", this.getJSONObjectMaxMinPerStates(filter));
		objInt.put("cities", this.getJSONObjectMaxMinPerCities(filter));
		objInt.put("segments", this.getJSONObjectMaxMinPerSegments(filter));
		objInt.put("genres", this.getJSONObjectMaxMinPerGenres(filter));
		
		obj.put("Respect", objInt);
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/states
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/cities
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
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
	
	
	/**
	 * Metodo che viene utilizzato nel metodo getJSONObjectAllStats per restituire un JSONObject contenente 
	 * lo stato con il maggior numero di eventi e 
	 * lo stato con il minor numero di eventi
	 * tra quelli filtrati dall'utente
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerStates(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerStates(filter);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(EventsFilter.getStates(), counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(EventsFilter.getStates(), counter, minIndex, filter.getPeriod());				
		obj.put("min", objInt);
		
		return obj;
	}
	
	
	/**
	 * Metodo che viene utilizzato nel metodo getJSONObjectAllStats per restituire un JSONObject contenente 
	 * la città con il maggior numero di eventi e 
	 * la città con il minor numero di eventi
	 * tra quelli filtrati dall'utente
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerCities(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerCities(filter);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(EventsFilter.getCities(), counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(EventsFilter.getCities(), counter, minIndex, filter.getPeriod());				
		obj.put("min", objInt);
		
		return obj;
	}
	
	
	/**
	 * Metodo che viene utilizzato nel metodo getJSONObjectAllStats per restituire un JSONObject contenente 
	 * il segmento con il maggior numero di eventi e 
	 * il segmento con il minor numero di eventi
	 * tra quelli filtrati dall'utente
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerSegments(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerSegments(filter);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(EventsFilter.getSegments(), counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(EventsFilter.getSegments(), counter, minIndex, filter.getPeriod());				
		obj.put("min", objInt);
		
		return obj;
	}
	
	
	/**
	 * Metodo che viene utilizzato nel metodo getJSONObjectAllStats per restituire un JSONObject contenente 
	 * il genere con il maggior numero di eventi e 
	 * il genere con il minor numero di eventi
	 * tra quelli filtrati dall'utente
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerGenres(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerGenres(filter);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(EventsFilter.getGenres(), counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(EventsFilter.getGenres(), counter, minIndex, filter.getPeriod());				
		obj.put("min", objInt);
		
		return obj;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectError(Error err)
	{
		JSONObject obj = new JSONObject();
		obj.put("cause", err.getCause());
		obj.put("message", err.getMessage());
		return obj;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectError(String err)
	{
		JSONObject obj = new JSONObject();
		obj.put("error", err);
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce un JSONObject comune a tutte le rotte
	 * che si occupano delle statistiche
	 * 
	 * @param events Vettore di eventi 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.FilterImpl
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectStats(Vector<Event> events, FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();
		
		Stats stats = new Stats();		
		int size = events.size();
		
		obj.put("numero eventi", size);
		obj.put("media eventi", stats.average(size, filter.getPeriod()));
		obj.put("minimo eventi mensili", stats.min(events));
		obj.put("massimo eventi mensili", stats.max(events));		
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce un JSONObject contenente i nomi degli elementi con il maggior numero di eventi
	 * e con il minor numero di eventi, il numero di eventi che li caratterizza e la loro media mensile
	 * 
	 * @param elements
	 * @param counter
	 * @param index
	 * @param period Periodo scelto dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMin(Vector<String> elements, int [] counter, int index, String period)
	{
		JSONObject obj = new JSONObject();
		Stats stats = new Stats();

		obj.put("name", elements.get(index));
		obj.put("n events", counter[index]);
		obj.put("average", stats.average(counter[index], period));
		
		return obj;
	}
	
}