package it.univpm.app.ticketmaster.parser;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.model.Event;

public class JSONBuilder 
{
	public JSONObject getJSONObjectEvents(Vector<Event> listEvent)
	{
		JSONObject obj = new JSONObject();
		obj.put("listEvent", listEvent);
		obj.put("elements", listEvent.size());
		return obj;
	}
	
	//getJsonEventsCities /events/cities
	
	//getJSONObjectStatsCities /stats/cities
	
	public JSONObject getJSONObjectStats(int n, String average, String min, String max)
	{
		JSONObject obj = new JSONObject();
		obj.put("numero eventi", n);
		obj.put("media eventi", average);
		obj.put("minimo eventi mensili", min);
		obj.put("massimo eventi mensili", max);
		
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
