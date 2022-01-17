package it.univpm.app.ticketmaster.JSONHandler;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.app.ticketmaster.model.Event;

/**
 * Classe che analizza il codice JSON della chiamata alla rotta events dell'api di ticketmaster
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
public class EventsParser 
{
	/**
	 * Metodo che analizza il json della chiamata events e restituisce la lista di tutti gli eventi
	 * 
	 * @param json Fornisce il file JSON (come stringa) che viene analizzato dal metodo
	 * 
	 * @return listEvents Lista di Eventi contenente gli eventi creati, poi restituito
	 * 
	 * @throws ParseException
	 * 
	 * @see it.univpm.app.ticketmaster.apiConnection
	 * @see it.univpm.app.ticketmaster.model
	 */
	public Vector<Event> parse(String json) throws ParseException
	{	
		/**
		 * Vettore di Eventi nel quale vengono inseriti gli eventi creati
		 * a partire dal json e poi viene restituito alla classe TicketmasterConnection
		 */
		Vector<Event> listEvents = new Vector<Event>();
		Event e;
		
		JSONParser parser = new  JSONParser();
		JSONObject jO= (JSONObject) parser.parse(json);
		
		JSONObject embedded1 = (JSONObject) jO.get("_embedded");
		JSONArray events = (JSONArray) embedded1.get("events");
		
		for (int i = 0; i < events.size(); i++)
		{
			JSONObject eventoTemp = (JSONObject) events.get(i);
				String name = (String) eventoTemp.get("name");
				String url = (String) eventoTemp.get("url");
				JSONObject dates = (JSONObject) eventoTemp.get("dates");
					JSONObject start = (JSONObject) dates.get("start");
						String localDate = (String) start.get("localDate");
						LocalDate locDt = LocalDate.parse(localDate);
						String localTime = (String) start.get("localTime");
			
				JSONArray classifications = (JSONArray) eventoTemp.get("classifications");
				JSONObject classificationsTemp = (JSONObject) classifications.get(0);
					JSONObject segment = (JSONObject) classificationsTemp.get("segment");
						String nameSegment = (String) segment.get("name");
					JSONObject genre = (JSONObject) classificationsTemp.get("genre");
						String nameGenre = (String) genre.get("name");
			
				JSONObject embedded2 = (JSONObject) eventoTemp.get("_embedded");
					JSONArray venues = (JSONArray) embedded2.get("venues");
						JSONObject venuesTemp = (JSONObject) venues.get(0);
							String venueName = (String) venuesTemp.get("name");
							JSONObject city = (JSONObject) venuesTemp.get("city");
								String cityName = (String) city.get("name");
							JSONObject state = (JSONObject) venuesTemp.get("state");
								String stateName = (String) state.get("name");
						
			 e = new Event(name, url, localTime, locDt, venueName, cityName, stateName, nameSegment, nameGenre);
			 
			 listEvents.add(e);
		}
		
		return listEvents;
	}
	
	
	
}

