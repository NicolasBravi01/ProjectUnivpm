package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.util.Vector;

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
		return (this.states.contains(state)) || (this.states == null);
	}	
	
	
	public boolean isIncludedCity(String city)
	{
		return (this.cities.contains(city) || (this.cities == null));
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
		return (this.segment == segment) || (this.segment == null);
	}

	
	public boolean isIncludedGenre(String genre)
	{
		return (this.genres.contains(genre)) || (this.genres == null);
	}
	
}
