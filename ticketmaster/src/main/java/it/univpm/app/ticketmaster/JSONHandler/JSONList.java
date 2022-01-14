package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;
import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.filter.EventsFilter;


/**
 * TODO
 */
public class JSONList extends JSONBuilder
{

	/**
	 * Metodo che restituisce un JSONObject contenente la lista di tutti gli stati e il loro numero
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectStates()
	{
		JSONObject obj = new JSONObject();
		Vector<String> states = EventsFilter.getStates();
		int size = states.size();
		
		obj.put("list states", states);
		obj.put("number states", size);
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce un JSONObject contenente la lista di tutte le città il loro numero
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectCities()
	{
		JSONObject obj = new JSONObject();
		Vector<String> cities = EventsFilter.getCities();
		int size = cities.size();
		
		obj.put("list states", cities);
		obj.put("number states", size);
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce un JSONObject contenente la lista di tutti i segmenti e il loro numero
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectSegments()
	{
		JSONObject obj = new JSONObject();
		Vector<String> segments = EventsFilter.getSegments();
		int size = segments.size();
		
		obj.put("list states", segments);
		obj.put("number states", size);
		
		return obj;
	}
	
	
	/**
	 * Metodo che restituisce un JSONObject contenente la lista di tutti i generi e il loro numero
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectGenres()
	{
		JSONObject obj = new JSONObject();
		Vector<String> genres = EventsFilter.getGenres();
		int size = genres.size();
		
		obj.put("list states", genres);
		obj.put("number states", size);
		
		return obj;
	}
	
}
