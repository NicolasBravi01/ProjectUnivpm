package it.univpm.app.ticketmaster.JSONHandler;

import org.json.simple.JSONObject;

/**
 * 
 * TODO
 */
public abstract class JSONBuilder 
{

	/**
	 * Metodo che restituisce un JSONObject contenente un messaggio di errore
	 * 
	 * @param err toString dell'errore
	 * 
	 * @return obj JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObjectError(String err)
	{
		JSONObject obj = new JSONObject();
		obj.put("error", err);
		return obj;
	}
	
}