package it.univpm.app.ticketmaster.model;

import java.util.Vector;

public class Usa 
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
}
