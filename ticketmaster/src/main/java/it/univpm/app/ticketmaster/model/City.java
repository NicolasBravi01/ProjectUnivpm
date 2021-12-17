package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class City implements ShowEventsStats
{
	private String name;
	private Vector<Event> events;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Event> getEvents() {
		return events;
	}
	public void setEvents(Vector<Event> events) {
		this.events = events;
	}
	
	
	public City(String name) {
		this.name = name;
	}
	
	
	@Override
	public void showEventsByGenre(String genre) 
	{
		for(int i=0; i<events.size(); i++)
		{
				if(events.get(i).getGenre()==genre)
				{
					System.out.println("Event n°" + i);
					events.get(i).toString();
				}
		}		
	}
	
	@Override
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2) 
	{
		for(int i=0; i<events.size(); i++)
		{
				LocalDate ld = events.get(i).getLocalDate();
				if((ld.isAfter(ld1)&&ld.isBefore(ld2))||ld.equals(ld1)||ld.equals(ld2))
				{
					System.out.println("Event n°" + i);
					events.get(i).toString();
				}
		}	
		
	}
	
	@Override
	public int getNumberEvents() 
	{
		return events.size();
	}
	
}
