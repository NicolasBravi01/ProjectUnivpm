package it.univpm.app.ticketmaster.apiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.app.ticketmaster.scanner.ApiKeyReader;


public class ticketmasterConnection 
{
	final static String countryCode = "US";		//Eventi US, United States	
	
	public static JSONObject getJSONEvents() {
		
		String urlApiConnection = "https://app.ticketmaster.com/discovery/v2/events.json?countryCode=" + countryCode + "&apikey=" + ApiKeyReader.getApiKey();	
		JSONObject obj = null;
		
		try 
		{			
			URL url = new URL(urlApiConnection);			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					
			//json e' una stringa con all'interno tutto il file JSON degli eventi, va convertito in jsonObject e poi messo in lista
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));			
			String json = input.readLine();		
			
			JSONParser parser = new JSONParser();  		
			obj = (JSONObject) parser.parse(json);
			
		} 
		catch (ParseException e) {			
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
				
		
		return obj;	
	}
	
	
	
	

}
