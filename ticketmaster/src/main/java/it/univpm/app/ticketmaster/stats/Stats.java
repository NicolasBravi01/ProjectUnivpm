package it.univpm.app.ticketmaster.stats;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

/**
 * Classe che si occupa del calcolo delle statistiche, che verranno in seguito passate alla classe JSONBuilder,
 * per poter produrre i vari jsonobject corrispondenti alle rotte del controller
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
public class Stats 
{	
	/**
	 * Metodo che calcola la media mensile degli eventi, in base al periodo scelto dall'utente
	 * 
	 * @param n Numero di eventi
	 * @param period Stringa contenente il periodo scelto dall'utente
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return av Media mensile degli eventi
	 */
	public double average(int n, String period) 
	{
		double av;	
		
		LocalDate startDate;
		LocalDate endDate;
		
		if(period.equals(""))
		{
			startDate = EventsFilter.getFirstDate();
			endDate = EventsFilter.getLastDate();
		}
		else
		{
			startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
			endDate = LocalDate.parse(period.substring(period.indexOf(',') + 1, period.length()));
		}

		av = (30 * n) / (double) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
		av = Math.floor(av*100)/100;
		
		return av;		
	}
	
	/**
	 * Metodo che stampa un messaggio che rivela il numero minimo di eventi e il mese in cui questo viene raggiunto
	 * 
	 * @param events Lista di eventi tra i quali si cerca di calcolare il minimo
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return msg Stringa contenente il messaggio
	 */
	public String min(Vector<Event> events) 
	{		
		int [] counter = eventsPerMonth(events);
		
		int monthMin = minValueIndex(counter) + 1;
		int min = counter[monthMin - 1];
		
		String msg = min + ", in " + monthToString(monthMin);
		return msg;
	}
	
	
	/**
	 * Metodo che stampa un messaggio che rivela il numero massimo di eventi e il mese in cui questo viene raggiunto
	 * 
	 * @param events Lista di eventi tra i quali si cerca di calcolare il massimo
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return msg Stringa contenente il messaggio
	 */
	public String max(Vector<Event> events) 
	{	
		int [] counter = eventsPerMonth(events);
		
		int monthMax = maxValueIndex(counter) + 1;
		int max = counter[monthMax - 1];
		
		String msg = max + ", in " + monthToString(monthMax);
		return msg;	
	}
	
	
	/**
	 * Metodo che converte un numero compreso tra 1 e 12 nella corrispettiva stringa del mese
	 * 
	 * @param month Numero del mese corrispondente 
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return str Stringa che rivela il mese a cui è associato il numero month
	 */
	public String monthToString(int month)
	{
		String str = "";
		
		switch(month)
		{
			case 1:
				str = "January";
				break;
			case 2:
				str = "February";
				break;
			case 3:
				str = "March";
				break;
			case 4:
				str = "April";
				break;
			case 5:
				str = "May";
				break;
			case 6:
				str = "June";
				break;
			case 7:
			    str = "July";
			    break;
			case 8:
			    str = "August";
			    break;
			case 9:
				str = "September";
				break;
			case 10:
				str = "October";
				break;
			case 11:
				str = "November";
				break;
			case 12:
				str = "December";
				break;
		}
		
		return str;
	}
	
	
	/**
	 * Metodo che crea un array contenente il numero di eventi accaduti in ogni mese
	 * 
	 * @param events Lista di eventi 
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return counter Array che immagazzina il numero di eventi accaduti in ogni mese
	 */
	private int [] eventsPerMonth(Vector<Event> events)
	{
		int [] counter = {0,0,0,0,0,0,0,0,0,0,0,0};
		LocalDate date;
		
		for(int i=0; i<events.size();i++)
		{
			date = events.get(i).getLocalDate();
			int month = date.getMonthValue();
			counter[month-1]++;
		}
		
		return counter;
	}
	
	
	/**
	 * Metodo che restituisce l'indice dell'array a cui corrisponde il valore massimo
	 * 
	 * @param vet Array di numeri
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return maxIndex Indice dell'array a cui corrisponde il valore massimo
	 */
	public int maxValueIndex(int [] vet)
	{
		int max = 0, maxIndex = 0;
		
		for (int i = 0; i < vet.length; i++) 
		{
			if(vet[i] > max)
			{
		    	max = vet[i];
		    	maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	/**
	 * Metodo che restituisce l'indice dell'array a cui corrisponde il valore minimo
	 * 
	 * @param vet Array di numeri
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return minIndex Indice dell'array a cui corrisponde il valore minimo
	 */
	public int minValueIndex(int [] vet)
	{
		int max = vet[0], minIndex = 0;
		
		for (int i = 0; i < vet.length; i++) 
		{
			if(vet[i] < max)
			{
		    	max = vet[i];
		    	minIndex = i;
			}
		}
		
		return minIndex;
	}
	
	
	/**
	 * Metodo che restituisce un array di interi contenente il numero di eventi per ogni stato,
	 * che verrà utilizzato nel metodo getJSONObjectMaxMinPerStates della classe JSONBuilder
	 * 
	 * @param events Lista di eventi
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return counter Array di interi
	 */
	public int [] getArrayStatsPerStates(Vector<Event> events)
	{
		Vector<Event> eventsApp;
		String state;
		Filter filter = new Filter();
		
		int [] counter = new int[EventsFilter.getStates().size()];
		
		for(int i = 0; i < counter.length; i++)
		{
			 state = EventsFilter.getStates().get(i);
			
			 filter.setStates(state);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 counter[i] = eventsApp.size();			
		}
		
		return counter;	
	}
	
	
	/**
	 * Metodo che restituisce un array di interi contenente il numero di eventi per ogni città,
	 * che verrà utilizzato nel metodo getJSONObjectMaxMinPerCities della classe JSONBuilder
	 * 
	 * @param events Lista di eventi
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return counter Array di interi
	 */
	public int [] getArrayStatsPerCities(Vector<Event> events)
	{
		Vector<Event> eventsApp;
		String city;
		Filter filter = new Filter();
		
		int [] counter = new int[EventsFilter.getCities().size()];
		
		for(int i = 0; i < counter.length; i++)
		{
			 city = EventsFilter.getCities().get(i);
			
			 filter.setCities(city);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 counter[i] = eventsApp.size();			
		}
		
		return counter;			
	}
	
	
	/**
	 * Metodo che restituisce un array di interi contenente il numero di eventi per ogni segmento,
	 * che verrà utilizzato nel metodo getJSONObjectMaxMinPerSegment della classe JSONBuilder
	 * 
	 * @param events Lista di eventi
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return counter Array di interi
	 */
	public int [] getArrayStatsPerSegments(Vector<Event> events)
	{
		Vector<Event> eventsApp;
		String segment;
		Filter filter = new Filter();
				
		int [] counter = new int[EventsFilter.getSegments().size()];
		
		for(int i = 0; i < counter.length; i++)
		{
			 segment = EventsFilter.getSegments().get(i);
			
			 filter.setSegment(segment);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 counter[i] = eventsApp.size();			
		}
		
		return counter;	
	}
	
	
	/**
	 * Metodo che restituisce un array di interi contenente il numero di eventi per ogni genere,
	 * che verrà utilizzato nel metodo getJSONObjectMaxMinPerGenres della classe JSONBuilder
	 * 
	 * @param events Lista di eventi
	 * 
	 * @see it.univpm.app.ticketmaster.JSONHandler.JSONBuilder
	 * 
	 * @return counter Array di interi
	 */
	public int [] getArrayStatsPerGenres(Vector<Event> events)
	{
		Vector<Event> eventsApp;
		String genre;
		Filter filter = new Filter();
				
		int [] counter = new int[EventsFilter.getGenres().size()];
		
		for(int i = 0; i < counter.length; i++)
		{
			 genre = EventsFilter.getGenres().get(i);
			
			 filter.setGenres(genre);
			 eventsApp = EventsFilter.getFilteredEvents(filter, events);
		
			 counter[i] = eventsApp.size();			
		}
		
		return counter;
	}
	
}
	