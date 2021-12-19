package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;

import it.univpm.app.ticketmaster.model.City;
import it.univpm.app.ticketmaster.model.State;

public interface Filter 
{
	public boolean isIncludedState(String state);
	public boolean isIncludedState(State state);
	public boolean isIncludedCity(String city);	
	public boolean isIncludedCity(City city);
	public boolean isIncludedDate(LocalDate localDate);
	public boolean isIncludedSegment(String segment);
	public boolean isIncludedGenre(String genre);	
	
}
