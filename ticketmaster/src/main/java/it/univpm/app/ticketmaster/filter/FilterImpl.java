package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;

public class FilterImpl implements Filter
{
	String state;
	String city;
	LocalDate startDate;
	LocalDate endDate;
	String segment;
	String genre;
	
		
	public FilterImpl(String state, String city, LocalDate startDate, LocalDate endDate, String segment, String genre)
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
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
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
		return (this.state == state) || (this.state == null);
	}	
	
	
	public boolean isIncludedCity(String city)
	{
		return (this.city == city) || (this.city == null);
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
