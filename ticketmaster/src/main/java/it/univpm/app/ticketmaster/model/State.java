package it.univpm.app.ticketmaster.model;

import java.util.Objects;
import java.util.Vector;

public class State 
{
	private String name;
	private Vector<City> citiesList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<City> getCitiesList() {
		return citiesList;
	}
	public void setCitiesList(Vector<City> citiesList) {
		this.citiesList = citiesList;
	}
	
	
	public State(String name) {
		this.name = name;
	}
	
	
	public City checkExistingCity(String name)
	{
		for(int i=0; i<citiesList.size(); i++)
		{
			if(citiesList.get(i).getName()==name)
				return citiesList.get(i); 
		}
		
		City city = new City(name);
		citiesList.add(city);
		return city;
	}
	
}
