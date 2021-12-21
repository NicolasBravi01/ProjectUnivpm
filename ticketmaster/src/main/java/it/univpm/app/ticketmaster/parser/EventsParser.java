package it.univpm.app.ticketmaster.parser;

import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.app.ticketmaster.model.City;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.model.State;
import it.univpm.app.ticketmaster.model.Country;

public class EventsParser 
{
	Country USA;	//TOSHOW
	
	public EventsParser()
	{
		this.USA = new Country();		
	}
	
	//TOSHOW
	public void parse(String json) throws ParseException
	{		
		JSONParser parser = new JSONParser();			
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
						LocalDate locDt = LocalDate.parse((CharSequence) localDate);
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
			  * Se lo stateName dell'evento non coincide con quello di uno stato già esistente,
			  * ne creo uno nuovo e lo aggiungo alla lista di stati degli USA.
			  */
			 State s = USA.obtainState(stateName); 
			 
			 /*
			  * Se il cityName dell'evento non coincide con quello di una città già esistente,
			  * ne creo una nuova e la aggiungo alla lista di città di quello stato.
			  */
			 City c = s.obtainCity(cityName);
			 
			 /*
			  * Aggiungo l'evento alla lista di eventi della città.
			  */
	
			 c.getEvents().add(e);
		}
		
		System.out.println(USA.toString());//Di prova
	}
}

