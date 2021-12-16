package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class Usa implements ShowEventsStats
{
	private static Vector<State> statesList;
	

	public static Vector<State> getStatesList() {
		return statesList;
	}
	public void setStatesList(Vector<State> statesList) {
		Usa.statesList = statesList;
	}
	
	
	public static State checkExistingState(String name)
	{
		for(int i=0; i<statesList.size(); i++)
		{
			if(statesList.get(i).getName()==name)
				return statesList.get(i); 
		}
		
		State state = new State(name);
		statesList.add(state);
		return state;
	}
	
	
	@Override
	public void showEventsByGenre(String genre) 
	{
		for(int i=0; i<statesList.size(); i++)
		{
			for(int j=0; j<statesList.get(i).getCitiesList().size(); j++)
			{
				for(int k=0; k<statesList.get(i).getCitiesList().get(j).getEventsListPerCity().size();k++)
				{
					if(statesList.get(i).getCitiesList().get(j).getEventsListPerCity().get(k).getGenre()==genre)
					{
						System.out.println("Event n°" + k);
						statesList.get(i).getCitiesList().get(j).getEventsListPerCity().get(k).toString();
					}
				}
					
			}			
		}	
		
	}
	
	@Override
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2) 
	{
		for(int i=0; i<statesList.size(); i++)
		{
			for(int j=0; j<statesList.get(i).getCitiesList().size(); j++)
			{
				for(int k=0; k<statesList.get(i).getCitiesList().get(j).getEventsListPerCity().size();k++)
				{
					LocalDate ld = statesList.get(i).getCitiesList().get(j).getEventsListPerCity().get(k).getLocalDate();
					if((ld.isAfter(ld1)&&ld.isBefore(ld2))||ld.equals(ld1)||ld.equals(ld2))
					{
						System.out.println("Event n°" + k);
						statesList.get(i).getCitiesList().get(j).getEventsListPerCity().get(k).toString();
					}
				}
					
			}			
		}
		
	}
	
	@Override
	public int showNumberEvents() 
	{
		return statesList.size();
	}
}
