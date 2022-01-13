package it.univpm.app.ticketmaster.apiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.scanner.ApiKeyReader;
import it.univpm.app.ticketmaster.JSONHandler.EventsParser;
import it.univpm.app.ticketmaster.exception.ApiConnectionException;


/**
 * Classe che effettua la chiamata all'Api di ticketmaster per ricevere la lista degli eventi su cui lavorare
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
public class TicketmasterConnection 
{
	final static String countryCode = "US";
	final static int size = 200;		
	
	/**
	 * Metodo static che effettua la chiamata all'Api di ticketmaster, ottenendo un json da cui la classe EventsParser estrae
	 * una lista di eventi che vengono inseriti nella classe EventsFilter.
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.EventsParser
	 * @see it.univpm.app.ticketmaster.filter.EventsFilter
	 */
	public static Vector<Event> callEvents() 
	{
		Vector<Event> events = new Vector<Event>();
		
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
			events = eP.parse(data);			
		}
		catch (ApiConnectionException | MalformedURLException e) 
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
			
		return events;
	}
}
