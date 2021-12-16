package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class State implements ShowEventsStats
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
	
	
	@Override
	public void showEventsByGenre(String genre) 
	{
		for(int i=0; i<citiesList.size(); i++)
		{
			for(int j=0; j<citiesList.get(i).getEventsListPerCity().size();j++)
			{
				if(citiesList.get(i).getEventsListPerCity().get(j).getGenre()==genre)
				{
					System.out.println("Event n°" + j);
					citiesList.get(i).getEventsListPerCity().get(j).toString();
				}
			}			
		}		
	}
	
	@Override
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2) 
	{
		for(int i=0; i<citiesList.size(); i++)
		{
			for(int j=0; j<citiesList.get(i).getEventsListPerCity().size();j++)
			{
				LocalDate ld = citiesList.get(i).getEventsListPerCity().get(j).getLocalDate();
				if((ld.isAfter(ld1)&&ld.isBefore(ld2))||ld.equals(ld1)||ld.equals(ld2))
				{
					System.out.println("Event n°" + j);
					citiesList.get(i).getEventsListPerCity().get(j).toString();
				}
			}			
		}
		
	}
	
	@Override
	public int showNumberEvents() 
	{
		return citiesList.size();
	}
	
	
	
}
