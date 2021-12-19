package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;

import it.univpm.app.ticketmaster.model.City;
import it.univpm.app.ticketmaster.model.State;

public class FilterImpl implements Filter
{
	State state;
	City city;
	LocalDate startDate;
	LocalDate endDate;
	String segment;
	String genre;
	
		
	public FilterImpl(State state, City city, LocalDate startDate, LocalDate endDate, String segment, String genre)
	{
		this.state = state;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.segment = segment;
		this.genre = genre;
	}
	
	public FilterImpl()
	{
		reset();
	}
	
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
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
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	public void reset()
	{
		this.state = null;
		this.city = null;
		this.startDate = null;
		this.endDate = null;
		this.segment = null;
		this.genre = null;
	}
	
	
	
	public boolean isIncludedState(String state)
	{
		return this.state.getName().equals(state) || (this.state == null);
	}	
	
	public boolean isIncludedState(State state)
	{
		return this.state.equals(state) || (this.state == null);
	}
	
	
	
	public boolean isIncludedCity(String city)
	{
		return this.city.getName().equals(city) || (this.city == null);
	}	
	
	public boolean isIncludedCity(City city)
	{
		return this.city.equals(city) || (this.city == null);
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
		return (this.segment == segment) || (segment == null);
	}


	public boolean isIncludedGenre(String genre)
	{
		return (this.genre == genre) || (segment == null);
	}
		
	
	
}
