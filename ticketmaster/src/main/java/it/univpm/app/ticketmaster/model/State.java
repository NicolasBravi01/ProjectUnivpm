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
	
	
	
	public City obtainCity(String name)
	{
		City city = null;
		int index = getIndexCity(name);
		
		if(index < 0)
		{
			city = new City(name);
			cities.add(city);
		}
		else
			city = cities.get(index);
		
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
	
	
	
	public  int getIndexCity(String name)
	{
		int i = 0;
		boolean found = false;
		
		while(i < cities.size() && !found)
		{
			if(cities.get(i).getName() == name)
				found = true;
			else
				i++;
		}
		
		if(i == cities.size())
			i = -1;
		
		return i;
	}
	
	public boolean cityExist(String name)
	{
		return getIndexCity(name) >= 0;
	}
	
	
}
