package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class City implements ShowEventsStats
{
	private String name;
	private Vector<Event> eventsListPerCity;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Event> getEventsListPerCity() {
		return eventsListPerCity;
	}
	public void setEventsListPerCity(Vector<Event> eventsListPerCity) {
		this.eventsListPerCity = eventsListPerCity;
	}
	
	
	public City(String name) {
		this.name = name;
	}
	
	
	@Override
	public void showEventsByGenre(String genre) 
	{
		for(int i=0; i<eventsListPerCity.size(); i++)
		{
				if(eventsListPerCity.get(i).getGenre()==genre)
				{
					System.out.println("Event n°" + i);
					eventsListPerCity.get(i).toString();
				}
		}		
	}
	
	@Override
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2) 
	{
		for(int i=0; i<eventsListPerCity.size(); i++)
		{
				LocalDate ld = eventsListPerCity.get(i).getLocalDate();
				if((ld.isAfter(ld1)&&ld.isBefore(ld2))||ld.equals(ld1)||ld.equals(ld2))
				{
					System.out.println("Event n°" + i);
					eventsListPerCity.get(i).toString();
				}
		}	
		
	}
	
	@Override
	public int showNumberEvents() 
	{
		return eventsListPerCity.size();
	}
	
}
