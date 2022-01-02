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
import it.univpm.app.ticketmaster.parser.JsonBuilder;

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
	
	
	@GetMapping(value = "/events")
	public JSONObject getEvents(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="cities", defaultValue="") String cities,
						  @RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="genres", defaultValue="") String genres,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, cities, period, segment, genres);
		JsonBuilder jB = new JsonBuilder();
		return jB.build(EventsFilter.getFilteredEvents(filter, EventsFilter.getEvents()));
	}
	
	
	@GetMapping(value = "/stats/states")
	public JSONObject getStatsPerStates(@RequestParam(name="states", defaultValue="") String states,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
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
	
	
	@GetMapping(value = "/stats/cities")
	public JSONObject getStatsPerCities(@RequestParam(name="cities", defaultValue="") String cities,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", cities, period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
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
	
	
	@GetMapping(value = "/stats/segments")
	public JSONObject getStatsPerSegments(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
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
	
	
	@GetMapping(value = "/stats/genres")
	public JSONObject getStatsPerGenres(@RequestParam(name="genres", defaultValue="") String genres,
						   @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", genres);
		JsonBuilder jB = new JsonBuilder();
		
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
	

	@GetMapping(value = "/events/states")
	public JSONObject getEventsForStates(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
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
				 jo.put(state, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	
	@GetMapping(value = "/events/cities")
	public JSONObject getEventsForCities(@RequestParam(name="states", defaultValue="") String states,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl(states, "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
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
				 jo.put(city, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	
	@GetMapping(value = "/events/segments")
	public JSONObject getEventsForCities(@RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, "", "");
		JsonBuilder jB = new JsonBuilder();
		
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
				 jo.put(segment, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	
	@GetMapping(value = "/events/genres")
	public JSONObject getEventsForGenres(@RequestParam(name="segment", defaultValue="") String segment,
						  @RequestParam(name="period", defaultValue="") String period)
	{	
		FilterImpl filter = new FilterImpl("", "", period, segment, "");
		
		JsonBuilder jB = new JsonBuilder();
		
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
				 jo.put(genre, jB.build(events));
			 }
		}
		
		return jo;
	}
	
	
	@GetMapping(value = "/states")
	public Vector<String> getStates()
	{	
		Vector<String> states = EventsFilter.getStates();
		return states;
	}
	
	
	@GetMapping(value = "/cities")
	public Vector<String> getCities()
	{	
		Vector<String> cities = EventsFilter.getCities();
		return cities;
	}

	
	@GetMapping(value = "/segments")
	public Vector<String> getSegments()
	{	
		Vector<String> segments = EventsFilter.getSegments();
		return segments;
	}
	
	
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
			
			av = (double) n / (double) ChronoUnit.DAYS.between(startDate, endDate);
			str = " (media mensile calcolata nel periodo scelto)";
		}

		av = Math.round(av*1000)/1000;
		msg = av +str;
		
		return msg;
		
	}
	
	
	private String min(Vector<Event> events) 
	{
		LocalDate date;
		
		int min = 0;
		int [] arrayContatore = {0,0,0,0,0,0,0,0,0,0,0,0};
		String str = "";
		String msg = "";
		
		for(int i=0; i<events.size();i++)
		{
			date = events.get(i).getLocalDate();
			int month = date.getDayOfMonth();
			arrayContatore[month-1]++;
		}
		
		for (int numero : arrayContatore) 
		    if(numero < min)
		    	min = numero;
		
		int monthMin = 0;
		
		for (int k = 0; k < arrayContatore.length; k++) 
		{
			if(arrayContatore[k] < min)
			{
		    	min = arrayContatore[k];
		    	monthMin = k+1;
			}
		}
		
		switch(monthMin)
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
		
		msg = min + ", raggiunto nel mese di " + str;
		return msg;	
	}
	
	
	private String max(Vector<Event> events) 
	{
		LocalDate date;
		
		int max = 0;
		int [] arrayContatore = {0,0,0,0,0,0,0,0,0,0,0,0};
		String str = "";
		String msg = "";
		
		for(int i=0; i<events.size();i++)
		{
			date = events.get(i).getLocalDate();
			int month = date.getDayOfMonth();
			arrayContatore[month-1]++;
		}
		
		for (int numero : arrayContatore) 
		    if(numero < max)
		    	max = numero;
		
		int monthMax = 0;
		
		for (int k = 0; k < arrayContatore.length; k++) 
		{
			if(arrayContatore[k] < max)
			{
		    	max = arrayContatore[k];
		    	monthMax = k+1;
			}
		}
		
		switch(monthMax)
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
		
		msg = max + ", raggiunto nel mese di " + str;
		return msg;	
	}
}
	
