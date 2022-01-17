package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.stats.Stats;

/**
 * Classe che contiene i metodi per costruire i JSONObject usati nelle rotte /stats
 * 
 * @see package it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
 * @see it.univpm.app.ticketmaster.controller.StatsController
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
public class JSONStats extends JSONBuilder
{

	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param events Lista di eventi
	 * @param states Lista di stati
	 * @param cities Lista di città
	 * @param segments Lista di segmenti
	 * @param genres Lista di generi
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectAllStats(Filter filter, Vector<Event> events, Vector<String> states, Vector<String> cities, Vector<String> segments, Vector<String> genres)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt1 = new JSONObject();
		JSONObject objInt2 = new JSONObject();

		objInt1 = this.getJSONObjectStats(filter, events);
		
		objInt2.put("states", this.getJSONObjectMaxMinPerStates(filter, events, states));
		objInt2.put("cities", this.getJSONObjectMaxMinPerCities(filter, events, cities));
		objInt2.put("segments", this.getJSONObjectMaxMinPerSegments(filter, events, segments));
		objInt2.put("genres", this.getJSONObjectMaxMinPerGenres(filter, events, genres));
		
		obj.put("general", objInt1);
		obj.put("perspectives", objInt2);
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/states
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param states Lista di stati
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerStates(Filter filter, Vector<Event> eventsToFilter, Vector<String> states)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> filteredEvents;
		String state;
		
		for(int i=0; i<states.size(); i++)
		{
			 state = states.get(i);
			
			 filter.setStates(state);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 int size = filteredEvents.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(filter, filteredEvents);	
				 obj.put(state, objInt);	
			 }
		}
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/cities
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param cities Lista di città
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerCities(Filter filter, Vector<Event> eventsToFilter, Vector<String> cities)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> filteredEvents;
		String city;
		
		for(int i=0; i<cities.size(); i++)
		{
			 city = cities.get(i);
			
			 filter.setCities(city);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 int size = filteredEvents.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(filter, filteredEvents);
				 obj.put(city, objInt);
			 }
		}
		
		return obj;
	}
		
	
	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/segments
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param segments Lista di segmenti
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerSegments(Filter filter, Vector<Event> eventsToFilter, Vector<String> segments)
	{		
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> filteredEvents;
		String segment;
		
		for(int i=0; i<segments.size(); i++)
		{
			 segment = segments.get(i);
			
			 filter.setSegment(segment);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
			 int size = filteredEvents.size();
				
			 if(size > 0)
			 {						 
				 objInt = getJSONObjectStats(filter, filteredEvents);
				 obj.put(segment, objInt);
			 }
		}
		
		return obj;
	}
	

	/**
	 * Metodo che restituisce il JSONObject associato alla rotta /stats/genres
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param eventsToFilter Lista di eventi da filtrare
	 * @param genres Lista di generi
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.controller.*
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStatsPerGenres(Filter filter, Vector<Event> eventsToFilter, Vector<String> genres)
	{			
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Vector<Event> filteredEvents;
		String genre;
		
		for(int i = 0; i < genres.size(); i++)
		{
			 genre = genres.get(i);
			 objInt = new JSONObject();
			
			 filter.setGenres(genre);
			 filteredEvents = filter.getFilteredEvents(eventsToFilter);
			 
			 int size = filteredEvents.size();
		
			 if(size > 0)
			 {			
				 objInt = getJSONObjectStats(filter, filteredEvents);
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
	 * @param events Lista di eventi
	 * @param stati Lista di stati
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * @see it.univpm.app.ticketmaster.stats
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerStates(Filter filter, Vector<Event> events, Vector<String> states)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerStates(events, states);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(states, counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(states, counter, minIndex, filter.getPeriod());				
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
	 * @param events Lista di eventi
	 * @param cities Lista di città
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * @see it.univpm.app.ticketmaster.stats
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerCities(Filter filter, Vector<Event> events, Vector<String> cities)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerCities(events, cities);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(cities, counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(cities, counter, minIndex, filter.getPeriod());				
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
	 * @param events Lista di eventi
	 * @param segments Lista di segmenti
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * @see it.univpm.app.ticketmaster.stats
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerSegments(Filter filter, Vector<Event> events, Vector<String> segments)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerSegments(events, segments);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(segments, counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(segments, counter, minIndex, filter.getPeriod());				
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
	 * @param events Lista di eventi
	 * @param genres Lista di generi
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * @see it.univpm.app.ticketmaster.stats
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMinPerGenres(Filter filter, Vector<Event> events, Vector<String> genres)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;		
		
		Stats stats = new Stats();		
		
		int [] counter = stats.getArrayStatsPerGenres(events, genres);
		
		int maxIndex = stats.maxValueIndex(counter);
		int minIndex = stats.minValueIndex(counter);		
		
		objInt = getJSONObjectMaxMin(genres, counter, maxIndex, filter.getPeriod());
		obj.put("max", objInt);
		
		objInt = getJSONObjectMaxMin(genres, counter, minIndex, filter.getPeriod());				
		obj.put("min", objInt);
		
		return obj;
	}
	
	
	
	/**
	 * Metodo che restituisce un JSONObject comune a tutte le rotte
	 * che si occupano delle statistiche
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param events Lista di eventi 
	 * 
	 * @see it.univpm.app.ticketmaster.filter
	 * @see it.univpm.app.ticketmaster.stats
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
	 * @param elements Lista di elementi su cui lavorare
	 * @param counter Array in cui sono stati inseriti il numero di eventi
	 * @param index Indice dell'array
	 * @param period Periodo scelto dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.stats
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	private JSONObject getJSONObjectMaxMin(Vector<String> elements, int [] counter, int index, String period)
	{
		JSONObject obj = new JSONObject();
		Stats stats = new Stats();

		obj.put("name", elements.get(index));
		obj.put("number events", counter[index]);
		obj.put("average", stats.average(counter[index], period));
		
		return obj;
	}
	
	
}
