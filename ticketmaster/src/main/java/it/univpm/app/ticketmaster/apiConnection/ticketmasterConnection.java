package it.univpm.app.ticketmaster.apiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.parser.EventsParser;
import it.univpm.app.ticketmaster.scanner.ApiKeyReader;


public class ticketmasterConnection 
{
	final static String countryCode = "US";		//Eventi US, United States
	final static int size = 50;		//Eventi US, United States
	
	
	public static void getJSONEvents() 
	{
		String queryString = "?countryCode=" + countryCode + "&size=" + size + "&apikey=" + ApiKeyReader.getApiKey();
		String urlApiConnection = "https://app.ticketmaster.com/discovery/v2/events.json" + queryString;

		try
		{
			URLConnection openConnection = new URL(urlApiConnection).openConnection();
			InputStream input = openConnection.getInputStream();

			String data = "";
			String line = "";
			
			
			BufferedReader buf = new BufferedReader(new InputStreamReader(input));

			while ((line = buf.readLine()) != null)
				data += line;
			
			EventsParser eP = new EventsParser();			
			eP.parse(data);
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}						
				
	}
}
