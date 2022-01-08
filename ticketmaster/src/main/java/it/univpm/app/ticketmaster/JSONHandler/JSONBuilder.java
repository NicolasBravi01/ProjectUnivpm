package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
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
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerStates(Vector<Event> events)
	{		
		JSONObject obj = new JSONObject();
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
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
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerCities(Vector<Event> events)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 if(events.size() > 0)
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
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerSegments(Vector<Event> events)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String segment;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
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
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectEventsPerGenres(Vector<Event> events)
	{		
		JSONObject obj = new JSONObject();	
		Filter filter = new Filter();
		Vector<Event> eventsApp;
		
		String genre;
		
		for(int i=0; i<EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			
			 filter.setGenres(genre);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 if(eventsApp.size() > 0)
			 {
				 obj.put(genre, this.getJSONObjectEvents(eventsApp));
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectAllStats(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt1 = new JSONObject();
		JSONObject objInt2 = new JSONObject();

		objInt1 = this.getJSONObjectStats(filter, events);
		
		objInt2.put("states", this.getJSONObjectMaxMinPerStates(filter, events));
		objInt2.put("cities", this.getJSONObjectMaxMinPerCities(filter, events));
		objInt2.put("segments", this.getJSONObjectMaxMinPerSegments(filter, events));
		objInt2.put("genres", this.getJSONObjectMaxMinPerGenres(filter, events));
		
		obj.put("general", objInt1);
		obj.put("perspectives", objInt2);
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/states
	 * 
	 * @param events Vettore di eventi
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerStates(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> eventsApp;
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 int size = eventsApp.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(filter, eventsApp);	
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerCities(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> eventsApp;
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 int size = eventsApp.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(filter, eventsApp);
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerSegments(Filter filter, Vector<Event> events)
	{		
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> eventsApp;
		String segment;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 int size = eventsApp.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(filter, eventsApp);
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerGenres(Filter filter, Vector<Event> events)
	{			
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> eventsApp;
		String genre;
		
		for(int i = 0; i < EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			 objInt = new JSONObject();
			
			 filter.setGenres(genre);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
			 
			 int size = eventsApp.size();
		
			 if(size > 0)
			 {			
				 objInt = getJSONObjectStats(filter, eventsApp);
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerStates(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerStates(filter, events);
		
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerCities(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerCities(filter, events);
		
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerSegments(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerSegments(filter, events);
		
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerGenres(Filter filter, Vector<Event> events)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerGenres(filter, events);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(EventsFilter.getGenres(), counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(EventsFilter.getGenres(), counter, minIndex, filter.getPeriod());				
		obj.put("min", objInt);
		
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
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.stats.Stats
	 * @see it.univpm.app.ticketmaster.controller.EventsController
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectStats(Filter filter, Vector<Event> events)
	{		
		JSONObject obj = new JSONObject();
		
		Stats stats = new Stats();		
		int size = events.size();
		
		obj.put("number events", size);
		obj.put("monthly average", stats.average(size, filter.getPeriod()));
		obj.put("min events in a month", stats.min(events));
		obj.put("max events in a month", stats.max(events));		
		
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