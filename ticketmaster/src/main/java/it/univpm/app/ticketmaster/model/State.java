package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class State implements ShowEventsStats
{
	private String name;
	private Vector<City> cities;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<City> getCities() {
		return cities;
	}
	public void setCities(Vector<City> cities) {
		this.cities = cities;
	}
	
	
	public State(String name) {
		this.name = name;
	}
	
	
	public City checkExistingCity(String name)
	{
		for(int i=0; i<cities.size(); i++)
		{
			if(cities.get(i).getName()==name)
				return cities.get(i); 
		}
		
		City city = new City(name);
		cities.add(city);
		return city;
	}
	
	
	@Override
	public void showEventsByGenre(String genre) 
	{
		for(int i=0; i<cities.size(); i++)
		{
			for(int j=0; j<cities.get(i).getEvents().size();j++)
			{
				if(cities.get(i).getEvents().get(j).getGenre() == genre)
				{
					System.out.println("Event n°" + j);
					System.out.println(cities.get(i).getEvents().get(j).toString());
				}
			}			
		}		
	}
	
	@Override
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2) 
	{
		for(int i=0; i<cities.size(); i++)
		{
			for(int j=0; j<cities.get(i).getEvents().size();j++)
			{
				LocalDate ld = cities.get(i).getEvents().get(j).getLocalDate();
				if((ld.isAfter(ld1)&&ld.isBefore(ld2))||ld.equals(ld1)||ld.equals(ld2))
				{
					System.out.println("Event n°" + j);
					System.out.println(cities.get(i).getEvents().get(j).toString());
				}
			}			
		}
		
	}
	
	
	@Override
	public int getNumberEvents() 
	{
		int numberEvents = 0;
		int size = cities.size();
		
		for(int i = 0; i<size; i++)
			numberEvents += cities.get(i).getNumberEvents();
		
		return numberEvents;
	}
	
	
	
}
