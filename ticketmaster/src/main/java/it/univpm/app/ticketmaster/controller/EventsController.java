package it.univpm.app.ticketmaster.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
	 * Metodo associato alla rotta get /stats/states, 
	 * che è in grado di generare le statistiche per gli stati scelti dall'utente in un certo periodo
	 * 
	 * @param states Stringa contenente gli stati di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return jo JSONObject contenente le statistiche degli stati filtrati dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/states")
	public JSONObject getStatsPerStates(@RequestParam(name="states", defaultValue="") String states,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		JSONObject joInt;
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			 joInt = new JSONObject();
			
			 filter.setStates(state);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 int size = events.size();
				
			 if(size > 0)
			 {						 
				 joInt = jB.getJSONObjectStats(size, average(size, period), min(events), max(events));	
				 jo.put(state, joInt);	
			 }
		}
		
		return jo;
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
	 * 
	 * @return jo JSONObject contenente le statistiche delle città filtrate dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/cities")
	public JSONObject getStatsPerCities(@RequestParam(name="cities", defaultValue="") String cities,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", cities, period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		JSONObject joInt;
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			 joInt = new JSONObject();
			
			 filter.setCities(city);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 int size = events.size();
				
			 if(size > 0)
			 {						 
				 joInt = jB.getJSONObjectStats(size, average(size, period), min(events), max(events));	
				 jo.put(city, joInt);
			 }
		}
		
		return jo;
	}
	
	/**
	 * Metodo associato alla rotta get /stats/segment, 
	 * che è in grado di generare le statistiche per il segmento scelto dall'utente in un certo periodo
	 * 
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return jo JSONObject contenente le statistiche del segmento scelto dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/segment")
	public JSONObject getStatsPerSegments(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		JSONObject joInt;
		String seg;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 seg = EventsFilter.getSegments().get(i);
			 joInt = new JSONObject();
			
			 filter.setSegment(seg);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 int size = events.size();
				
			 if(size > 0)
			 {						 
				 joInt = jB.getJSONObjectStats(size, average(size, period), min(events), max(events));	
				 jo.put(seg, joInt);
			 }
		}
		
		return jo;
	}
	
	/**
	 * Metodo associato alla rotta get /stats/genres, 
	 * che è in grado di generare le statistiche per i generi scelti dall'utente in un certo periodo
	 * 
	 * @param genres Stringa contenente i generi di interesse per l'utente
	 * @param period Stringa contenente il periodo di interesse per l'utente
	 * 
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 * @see it.univpm.app.ticketmaster.parser.JSONBuilder
	 * 
	 * @return jo JSONObject contenente le statistiche dei generi scelti dall'utente in un certo periodo
	 */
	@GetMapping(value = "/stats/genres")
	public JSONObject getStatsPerGenres(@RequestParam(name="genres", defaultValue="") String genres,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", genres);
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		JSONObject joInt;
		String genre;
		
		for(int i = 0; i < EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			 joInt = new JSONObject();
			
			 filter.setGenres(genre);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
			 
			 int size = events.size();
		
			 if(size > 0)
			 {						 
				 joInt = jB.getJSONObjectStats(size, average(size, period), min(events), max(events));
				 jo.put(genre, joInt);	
			 }
		}
		
		return jo;
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
	 * @return jo JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
	 */
	@GetMapping(value = "/events/states")
	public JSONObject getEventsForStates(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String state;
		
		for(int i=0; i<EventsFilter.getStates().size(); i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(state, jB.getJSONObjectEvents(events));
			 }
		}
		
		return jo;
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
	 * @return jo JSONObject contenente la lista di eventi raggruppati per stati in un certo periodo
	 */
	@GetMapping(value = "/events/cities")
	public JSONObject getEventsForCities(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String city;
		
		for(int i=0; i<EventsFilter.getCities().size(); i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(city, jB.getJSONObjectEvents(events));
			 }
		}
		
		return jo;
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
	 * @return jo JSONObject contenente la lista di eventi raggruppati per segmenti in un certo periodo
	 */
	@GetMapping(value = "/events/segments")
	public JSONObject getEventsForCities(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String segment;
		
		for(int i=0; i<EventsFilter.getSegments().size(); i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(segment, jB.getJSONObjectEvents(events));
			 }
		}
		
		return jo;
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
	 * @return jo JSONObject contenente la lista di eventi raggruppati per generi in un certo periodo
	 */
	@GetMapping(value = "/events/genres")
	public JSONObject getEventsForGenres(@RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, segment, "");
		
		JSONBuilder jB = new JSONBuilder();
		
		Vector<Event> events;
		JSONObject jo = new JSONObject();
		String genre;
		
		for(int i=0; i<EventsFilter.getGenres().size(); i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			
			 filter.setGenres(genre);
			 events = EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents());
		
			 if(events.size()>0)
			 {
				 jo.put(genre, jB.getJSONObjectEvents(events));
			 }
		}
		
		return jo;
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
	
	
	private String average(int n, String period) 
	{
		double av;
		String str = "";
		String msg = "";
		
		if(period.equals(""))
		{
			av = (double) n/12;
		    str = " (media mensile, calcolata nell'arco di un intero anno)";
		}
		else
		{
			LocalDate startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
			LocalDate endDate = LocalDate.parse(period.substring(period.indexOf(',') + 1, period.length()));
			
			av = (double) (30* n) / (double) ChronoUnit.DAYS.between(startDate, endDate);
			str = " (media mensile calcolata nel periodo scelto)";
		}

		av = (double)Math.round(av*100)/100;
		msg = av +str;
		
		return msg;
		
	}
	
	
	private String min(Vector<Event> events) 
	{
		LocalDate date;
		
		int min = 0;
		int [] counter = {0,0,0,0,0,0,0,0,0,0,0,0};
		String msg = "";
		
		for(int i=0; i<events.size();i++)
		{
			date = events.get(i).getLocalDate();
			int month = date.getMonthValue();
			counter[month-1]++;
		}
	
		int monthMin = 0;
		
		for (int k = 0; k < counter.length; k++) 
		{
			if(counter[k] <= min)
			{
		    	min = counter[k];
		    	monthMin = k+1;
			}
		}

		msg = min + ", raggiunto nel mese di " + monthToString(monthMin);
		return msg;	
	}
	
	
	private String max(Vector<Event> events) 
	{
		LocalDate date;
		
		int max = 0;
		
		int [] counter = {0,0,0,0,0,0,0,0,0,0,0,0};
		String msg = "";
		
		for(int i=0; i<events.size();i++)
		{
			date = events.get(i).getLocalDate();
			int month = date.getMonthValue();
			counter[month-1]++;
		}
		
		int monthMax = 0;
		
		for (int k = 0; k < counter.length; k++) 
		{
			if(counter[k] > max)
			{
		    	max = counter[k];
		    	monthMax = k+1;
			}
		}
		
		msg = max + ", raggiunto nel mese di " + monthToString(monthMax);
		return msg;	
	}
	
	
	public String monthToString(int month)
	{
		String str = "";
		
		switch(month)
		{
			case 1:
				str = "Gennaio";
				break;
			case 2:
				str = "Febbraio";
				break;
			case 3:
				str = "Marzo";
				break;
			case 4:
				str = "Aprile";
				break;
			case 5:
				str = "Maggio";
				break;
			case 6:
				str = "Giugno";
				break;
			case 7:
			    str = "Luglio";
			    break;
			case 8:
			    str = "Agosto";
			    break;
			case 9:
				str = "Settembre";
				break;
			case 10:
				str = "Ottobre";
				break;
			case 11:
				str = "Novembre";
				break;
			case 12:
				str = "Dicembre";
				break;
		}
		
		return str;
	}
	
}
	
