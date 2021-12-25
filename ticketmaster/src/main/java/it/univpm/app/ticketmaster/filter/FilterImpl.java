package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

public class FilterImpl implements Filter
{
	Vector<String> states;
	Vector<String> cities;
	LocalDate startDate;
	LocalDate endDate;
	String segment;
	Vector<String> genres;
	
		
	public FilterImpl(Vector<String> states, Vector<String> cities, LocalDate startDate, LocalDate endDate, String segment, Vector<String> genres)
	{
		this.states = states;
		this.cities = cities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.segment = segment;
		this.genres = genres;
	}
	
	public FilterImpl(Vector<String> states, Vector<String> cities, String period, String segment, Vector<String> genres)
	{
		this.states = states;
		this.cities = cities;
		loadPeriod(period);
		this.segment = segment;
		this.genres = genres;
	}
	
	
	public FilterImpl(String states, String cities, String period, String segment, String genres)
	{
		this.states = convertToVectorOfStrings(states);
		this.cities = convertToVectorOfStrings(cities);
		loadPeriod(period);
		this.segment = segment;
		this.genres = convertToVectorOfStrings(genres);
	}
	
	public FilterImpl()
	{
		reset();
	}
	
	
	public Vector<String> getStates() {
		return states;
	}
	public void setStates(Vector<String> states) {
		this.states = states;
	}
	public Vector<String> getCities() {
		return cities;
	}
	public void setCities(Vector<String> cities) {
		this.cities = cities;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public void setPeriod(LocalDate startDate, LocalDate endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public Vector<String> getGenres() {
		return genres;
	}
	public void setGenres(Vector<String> genres) {
		this.genres = genres;
	}
	
	
	public void reset()
	{
		this.states = new Vector<String>();
		this.cities = new Vector<String>();
		this.startDate = null;
		this.endDate = null;
		this.segment = null;
		this.genres = new Vector<String>();
	}
	
	
	
	public boolean isIncludedState(String state)
	{
		boolean isIncluded = (this.states.contains(state)) || (this.states == null) || (this.states.size() == 0);
		return isIncluded;
	}	
	
	
	
	public boolean isIncludedCity(String city)
	{
		boolean isIncluded = (this.cities.contains(city) || (this.cities == null) || (this.cities.size() == 0));
		return isIncluded;
	}	

	
	
	public boolean isIncludedDate(LocalDate localDate) 
	{
		boolean isIncluded = true;
		
		if(this.startDate != null)
		{
			if(this.endDate == null)
				isIncluded = localDate.isAfter(this.startDate.minusDays(1));
			else
				isIncluded = localDate.isAfter(this.startDate.minusDays(1)) && localDate.isBefore(this.endDate.plusDays(1));
		}
		
		return isIncluded;
	}

	
	
	public boolean isIncludedSegment(String segment)
	{
		boolean isIncluded = (this.segment == segment) || (this.segment.isEmpty());
		return isIncluded;
	}

	
	
	public boolean isIncludedGenre(String genre)
	{
		boolean isIncluded = (this.genres.contains(genre)) || (this.genres == null) || (this.genres.size() == 0);
		return isIncluded;
	}
	
	
	
	public boolean isIncludedEvent(Event e)
	{
		boolean isIncluded = isIncludedState(e.getState()) && isIncludedCity(e.getCity()) && isIncludedDate(e.getLocalDate())
							&& isIncludedSegment(e.getSegment()) && isIncludedGenre(e.getGenre());		 
		return isIncluded;
	}
	
	
	
	/*public Vector<Event> getFilteredEvents (Vector<Event> eventsToFilter) 
	{
		Vector<Event> filteredEvents = new Vector<Event>();
		Event e;
		
		for(int i=0; i<eventsToFilter.size(); i++)
		{			
			e = eventsToFilter.get(i);
			
			if(this.isIncludedEvent(e))
			{
				filteredEvents.add(e);
			}
		}
		
		return filteredEvents;
	}*/
	
	private Vector<String> convertToVectorOfStrings(String toParse)
	{
		Vector<String> list = new Vector<String>();
		
		if(!toParse.isEmpty())
		{						
			String [] splitString = toParse.split(",");
			
			for(int i = 0; i<splitString.length; i++)
				list.add(splitString[i]);
		}
		
		return list;
	}
	
	
	private void loadPeriod(String period)
	{
		if(period.isEmpty())
		{
			this.startDate = null;
			this.endDate = null;
		}
		else
		{
			this.startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
			this.endDate = LocalDate.parse(period.substring(period.indexOf(','), period.length()));			
		}
	}
		
}
