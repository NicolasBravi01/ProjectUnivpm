package it.univpm.app.ticketmaster.apiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.app.ticketmaster.scanner.ApiKeyReader;


public class ticketmasterConnection 
{
	final static String countryCode = "US";		//Eventi US, United States	
	
	public static JSONObject getJSONEvents() 
	{
		
		String urlApiConnection = "https://app.ticketmaster.com/discovery/v2/events.json?countryCode=" + countryCode + "&apikey=" + ApiKeyReader.getApiKey();	
		JSONObject obj = null;
		
		try
		{
			URLConnection openConnection = new URL(urlApiConnection).openConnection();
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try 
			{
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );

				while ( ( line = buf.readLine() ) != null ) {
				data+= line;
				}
			} 
			finally 
			{
			in.close();
			}
		
			obj = (JSONObject) JSONValue.parseWithException(data);

		} 
		
		catch (IOException | ParseException e) 
		{
		e.printStackTrace();
		} 
		catch (Exception e) 
		{
		e.printStackTrace();
		}			
				
		return obj;	
		
	}
}
