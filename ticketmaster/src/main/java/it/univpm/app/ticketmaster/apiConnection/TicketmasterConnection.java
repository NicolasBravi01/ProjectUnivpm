package it.univpm.app.ticketmaster.apiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.scanner.ApiKeyReader;
import it.univpm.app.ticketmaster.JSONHandler.EventsParser;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;


/**
 * Classe contenente la chiamata alla rotta events
 * 
 * @author sup3r
 */
public class TicketmasterConnection 
{
	final static String countryCode = "US";
	final static int size = 200;		
	
	/**
	 * Metodo static che effettua la chiamata alla rotta events e passa il json al metodo parse della classe EventsParser,
	 * che restituirà un vettore di eventi, il quale a sua volta verrà passato al metodo setEvents della classe EventsFilter, 
	 * che si occuperà di creare le liste contenenti tutti gli stati, le città, i segmenti e i generi che sono comparsi nel json.
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.EventsParser
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 */
	public static void callEvents() 
	{
		try
		{
			String queryString = "?countryCode=" + countryCode + "&size=" + size + "&apikey=" + ApiKeyReader.readApiKey();
			String urlApiConnection = "https://app.ticketmaster.com/discovery/v2/events.json" + queryString;
			
			URLConnection openConnection = new URL(urlApiConnection).openConnection();
			
			InputStream input = openConnection.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader(input));
			
			String data = "";
			String line = "";

			while ((line = buf.readLine()) != null)
				data += line;
			
			EventsParser eP = new EventsParser();	
			EventsFilter.setEvents(eP.parse(data));
		}

		catch (ApiConnectionException e) 
		{
			System.out.println(e.toString());
		}
		catch (MalformedURLException e) 
		{
			System.out.println(e.toString());
		}
		catch (IOException e) 
		{
			System.out.println(e.toString());
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}						
				
	}
}
