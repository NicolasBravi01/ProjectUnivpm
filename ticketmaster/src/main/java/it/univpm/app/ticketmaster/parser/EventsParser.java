package it.univpm.app.ticketmaster.parser;

import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.app.ticketmaster.apiConnection.ticketmasterConnection;
import it.univpm.app.ticketmaster.model.Event;

public class EventsParser 
{
	public void parse() 
	{
		JSONObject jO = ticketmasterConnection.getJSONEvents();
		
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
						LocalDate locDt = null;
						//TODO: conversione di localdate in una variabile LocalDate
						String localTime = (String) start.get("localTime");
			
				JSONArray classifications = (JSONArray) eventoTemp.get("classifications");
				JSONObject classificationsTemp = (JSONObject) classifications.get(0);
					JSONObject segment = (JSONObject) classificationsTemp.get("segment");
						String nameSegment = (String) segment.get("segment");
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
						
			 Event e = new Event(name, url, localTime, locDt, venueName, nameSegment, nameGenre);
			 
			 /*
			  * Se lo stateName dell'evento coincide con quello di uno stato già esistente lo inserisco nella lista
			  * di stati. Altrimenti ne creo uno nuovo.
			  * 
			  * Se il cityName dell'evento coincide con quello di una città già esistente lo inserisco nella lista 
			  * di città di quello stato. Altrimenti ne creo una nuova.
			  */
			  
		}
	}
}

