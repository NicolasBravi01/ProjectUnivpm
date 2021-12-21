package it.univpm.app.ticketmaster.model;

import java.util.Vector;

import it.univpm.app.ticketmaster.filter.FilterImpl;

public class Country implements ShowEventsStats
{
	private Vector<State> states = new Vector<State>();
		
	
	public Vector<State> getStates() {
		return states;
	}
	public void setStates(Vector<State> states) {
		this.states = states;
	}
	
						
	public State obtainState(String name)
	{
		State state = null;
		int index = indexOfState(name);
		
		if(index < 0)
		{
			state = new State(name);
			states.add(state);
		}
		else
			state = states.get(index);
		
		return state;
	}
	
	
	
	 
	public void showEvents(FilterImpl filter) 
	{
		for(int i=0; i<states.size(); i++)
		{
			if(filter.isIncludedState(states.get(i)))	//true ==> stato compreso nel filtro
			{				
				
				for(int j=0; j<states.get(i).getCities().size(); j++)
				{
					//if(filter.isIncludedCity(states.get(i).getCities().get(j)))	//true ==> citta' compresa nel filtro
										
						
					for(int k=0; k<states.get(i).getCities().get(j).getEvents().size(); k++)
					{
						Event event = states.get(i).getCities().get(j).getEvents().get(k);
						if(filter.isIncludedDate(event.getLocalDate()) && filter.isIncludedSegment(event.getSegment()) && filter.isIncludedGenre(event.getGenre()))
						{
							System.out.println("Event n°" + k);
							System.out.println(event.toString());
						}
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
	public int indexOfState(String name)
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
		return indexOfState(name) >= 0;
	}

		
	
}
