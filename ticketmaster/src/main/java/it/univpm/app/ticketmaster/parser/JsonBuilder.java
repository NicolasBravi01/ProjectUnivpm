package it.univpm.app.ticketmaster.parser;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.model.Event;

public class JsonBuilder 
{
	public JSONObject build(Vector<Event> listEvent)
	{
		JSONObject obj = new JSONObject();
		obj.put("listEvent", listEvent);
		obj.put("elements", listEvent.size());
		return obj;
	}
	
	
	public JSONObject getJSONObjectStats(int n, double average)
	{
		JSONObject obj = new JSONObject();
		obj.put("numero eventi", n);
		obj.put("media eventi", average);		
		return obj;
	}
	
	public JSONObject getJSONObjectError(Error err)
	{
		JSONObject obj = new JSONObject();
		obj.put("cause", err.getCause());
		obj.put("message", err.getMessage());
		return obj;
	}
}
