package it.univpm.app.ticketmaster.model;

import java.util.Vector;

public class State 
{
	private String name;
	private String stateCode;
	private static Vector<City> cityList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	
	public State(String name, String stateCode) {
		this.name = name;
		this.stateCode = stateCode;
	}
}
