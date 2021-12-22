package it.univpm.app.ticketmaster.filter;

import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

public class EventsFilter 
{
	
	public Vector<Event> getListFilteredEvents (FilterImpl filter, Vector<Event> eventsListToFilter) 
	{
		Vector<Event> filteredListEvents = new Vector<Event>();
		Vector<Event> list = new Vector<Event>();
		
		boolean belongsToState;
		boolean belongsToCity;
		boolean belongsToPeriod;
		boolean belongToSegment;
		boolean belongsToGenre;
		
		boolean areThereFilters;
		
		for(int i=0; i<eventsListToFilter.size(); i++)
		{
			belongsToState = filter.isIncludedState(eventsListToFilter.get(i).getState());
			belongsToCity = filter.isIncludedCity(eventsListToFilter.get(i).getCity());
			belongsToPeriod = filter.isIncludedDate(eventsListToFilter.get(i).getLocalDate());
			belongToSegment = filter.isIncludedSegment(eventsListToFilter.get(i).getSegment());
			belongsToGenre = filter.isIncludedGenre(eventsListToFilter.get(i).getGenre());
			
			areThereFilters = belongsToState && belongsToCity && belongsToPeriod && belongToSegment && belongsToGenre;
			
			if(areThereFilters)
			{
				filteredListEvents.add(eventsListToFilter.get(i));
				list = filteredListEvents;
			}
			else
				list = eventsListToFilter;
		}
		
		return list;
	}
}
