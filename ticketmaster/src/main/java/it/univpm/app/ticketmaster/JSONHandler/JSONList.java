package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;
import org.json.simple.JSONObject;


/**
 * TODO
 */
public class JSONList extends JSONBuilder
{

	
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectElements(Vector<String> elements)
	{
		JSONObject obj = new JSONObject();
		
		obj.put("list elements", elements);
		obj.put("number states", elements.size());
		
		return obj;
	}
	
}
