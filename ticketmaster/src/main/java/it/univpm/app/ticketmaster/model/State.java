package it.univpm.app.ticketmaster.model;

import java.util.Vector;

public class State implements ShowEventsStats
{
	private String name;
	private Vector<City> cities = new Vector<City>();
	
	
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
	
	
	public State(String name) 
	{
		this.name = name;
	}		
	
	
	public City obtainCity(String name)
	{
		City city = null;
		int index = indexOfCity(name);
		
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
	public int getNumberEvents() 
	{
		int numberEvents = 0;
		int size = cities.size();
		
		for(int i = 0; i<size; i++)
			numberEvents += cities.get(i).getNumberEvents();
		
		return numberEvents;
	}
	
	
	
	public int indexOfCity(String name)
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
		return indexOfCity(name) >= 0;
	}
	
	
	public boolean equals(State state)
	{
		return this.name == state.getName();
	}
	
	
}
