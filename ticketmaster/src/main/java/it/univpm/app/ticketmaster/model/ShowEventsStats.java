package it.univpm.app.ticketmaster.model;

import java.time.chrono.ChronoLocalDate;

public interface ShowEventsStats 
{
	void showEventsByGenre(String genre);
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2);
	public int getNumberEvents();
	
}
