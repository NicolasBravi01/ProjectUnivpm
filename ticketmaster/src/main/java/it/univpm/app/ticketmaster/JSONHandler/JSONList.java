package it.univpm.app.ticketmaster.JSONHandler;

import java.util.Vector;
import org.json.simple.JSONObject;


/**
 * Classe che contiene i metodi per costruire i JSONObject usati nelle rotte /list
 * 
 * @see package it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
 * @see it.univpm.app.ticketmaster.controller.ListController
 */
public class JSONList extends JSONBuilder
{
	/**
	 * Metodo che restituisce il JSONObject relativo alla rotta corrispondente all'elemeento passato come parametro
	 * 
	 * @param elements Lista di stringhe
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectElements(Vector<String> elements)
	{
		JSONObject obj = new JSONObject();
		
		obj.put("list elements", elements);
		obj.put("number elements", elements.size());
		
		return obj;
	}
	
}
