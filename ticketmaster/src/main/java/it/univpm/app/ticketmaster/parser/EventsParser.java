package it.univpm.app.ticketmaster.parser;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.model.Event;


public class EventsParser 
{

	public Vector<Event> parse(String json) throws ParseException
	{	
		Vector<Event> listEvents = new Vector<Event>();
		
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
						
			 Event e = new Event(name, url, localTime, locDt, venueName, cityName, stateName, nameSegment, nameGenre);
			 
			 listEvents.add(e);
			 EventsFilter.addEvent(e);//provvisorio
		}
		
		return listEvents;
	}
	
	
	
}

