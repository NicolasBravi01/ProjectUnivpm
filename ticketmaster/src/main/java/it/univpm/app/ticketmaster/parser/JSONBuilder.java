package it.univpm.app.ticketmaster.parser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.stats.Stats;

public class JSONBuilder 
{
	public JSONObject getJSONObjectEvents(Vector<Event> listEvent)
	{
		JSONObject obj = new JSONObject();
		obj.put("listEvent", listEvent);
		obj.put("elements", listEvent.size());
		return obj;
	}
	
	
	
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
	
	public JSONObject getJSONObjectStats(Vector<Event> events, FilterImpl filter)
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
	
	
	
	
	
	public JSONObject getJSONObjectStatsPerStates(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Stats stats = new Stats();

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
				 //objInt = getJSONObjectStats(size, stats.average(size, filter.getPeriod()), stats.min(events), stats.max(events));	
				 objInt = getJSONObjectStats(events, filter);	
				 obj.put(state, objInt);	
			 }
		}
		
		return obj;
	}
	
	
	
	
	public JSONObject getJSONObjectStatsPerCities(FilterImpl filter)
	{
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Stats stats = new Stats();

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
				 //objInt = getJSONObjectStats(size, stats.average(size, filter.getPeriod()), stats.min(events), stats.max(events));	
				 objInt = getJSONObjectStats(events, filter);
				 obj.put(city, objInt);
			 }
		}
		
		return obj;
	}
	
	
	
	
	public JSONObject getJSONObjectStatsPerSegments(FilterImpl filter)
	{		
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Stats stats = new Stats();

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
				 //objInt = getJSONObjectStats(size, stats.average(size, filter.getPeriod()), stats.min(events), stats.max(events));	
				 objInt = getJSONObjectStats(events, filter);
				 obj.put(segment, objInt);
			 }
		}
		
		return obj;
	}
	
	
	
	
	
	public JSONObject getJSONObjectStatsPerGenres(FilterImpl filter)
	{			
		JSONObject obj = new JSONObject();
		JSONObject objInt;
		
		Stats stats = new Stats();

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
				 //objInt = getJSONObjectStats(size, stats.average(size, filter.getPeriod()), stats.min(events), stats.max(events));	
				 objInt = getJSONObjectStats(events, filter);	
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