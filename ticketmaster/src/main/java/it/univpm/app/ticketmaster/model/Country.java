package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class Country implements ShowEventsStats
{
	private Vector<State> states;
	

	public Country()
	{
		states = new Vector<State>();		
	}
	
	
	public Vector<State> getStates() {
		return states;
	}
	public void setStates(Vector<State> states) {
		this.states = states;
	}
	
						
	public State obtainState(String name)
	{
		State state = null;
		int index = getIndexState(name);
		
		if(index < 0)
		{
			state = new State(name);
			states.add(state);
		}
		else
			state = states.get(index);
		
		return state;
	}
	
	//TOSHOW
	@Override
	public void showEventsByGenre(String genre) 
	{
		for(int i=0; i<states.size(); i++)
		{
			for(int j=0; j<states.get(i).getCities().size(); j++)
			{
				for(int k=0; k<states.get(i).getCities().get(j).getEvents().size();k++)
				{
					if(states.get(i).getCities().get(j).getEvents().get(k).getGenre()==genre)
					{
						System.out.println("Event n°" + k);
						System.out.println(states.get(i).getCities().get(j).getEvents().get(k).toString());
					}
				}
					
			}			
		}	
		
	}

	
	
	@Override
	public void showEventsByPeriod(ChronoLocalDate ld1, ChronoLocalDate ld2) 
	{
		for(int i=0; i<states.size(); i++)
		{
			for(int j=0; j<states.get(i).getCities().size(); j++)
			{
				for(int k=0; k<states.get(i).getCities().get(j).getEvents().size();k++)
				{
					LocalDate ld = states.get(i).getCities().get(j).getEvents().get(k).getLocalDate();
					if((ld.isAfter(ld1)&&ld.isBefore(ld2))||ld.equals(ld1)||ld.equals(ld2))
					{
						System.out.println("Event n°" + k);
						System.out.println(states.get(i).getCities().get(j).getEvents().get(k).toString());
					}
				}					
			}			
		}
		
	}
	
	
	
	@Override
	public int getNumberEvents() 
	{
		int numberEvents = 0;
		int size = states.size();
		
		for(int i = 0; i<size; i++)
			numberEvents += states.get(i).getNumberEvents();
		
		return numberEvents;
	}
	
	
	/*
	 * Ritorna l'id dello stato nel vettore, utilizzare al posto di checkExistingState(String name)
	 */
	public int getIndexState(String name)
	{
		int i = 0;
		boolean found = false;
		
		while(i < states.size() && !found)
		{
			if(states.get(i).getName() == name)
				found = true;
			else
				i++;
		}
		
		if(i == states.size())
			i = -1;
		
		return i;
	}
	
	public boolean stateExist(String name)
	{
		return getIndexState(name) >= 0;
	}
	
	
	
}
