package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;

public interface Filter 
{
	public boolean isIncludedState(String state);
	public boolean isIncludedCity(String city);	
	public boolean isIncludedDate(LocalDate localDate);
	public boolean isIncludedSegment(String segment);
	public boolean isIncludedGenre(String genre);	
}
