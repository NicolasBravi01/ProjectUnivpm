package it.univpm.app.ticketmaster.filter;

import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

public class EventsFilter 
{
	static Vector<Event> events = new Vector<Event>();
	
	
	public static Vector<Event> getEvents() {
		return events;
	}

	public static void setEvents(Vector<Event> e) {
		events = e;
	}
	
	
	
	
	public static Vector<Event> getFilteredEvents (FilterImpl filter, Vector<Event> eventsToFilter) 
	{
		Vector<Event> filteredEvents = new Vector<Event>();
	
		
		for(int i=0; i<eventsToFilter.size(); i++)
		{			
			Event e = eventsToFilter.get(i);
			
			if(filter.isIncludedEvent(e))
			{
				filteredEvents.add(e);
			}
		}
		
		return filteredEvents;
	}
	
	
	
}
