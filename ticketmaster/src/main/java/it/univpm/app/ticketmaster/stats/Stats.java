package it.univpm.app.ticketmaster.stats;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

/**
 * Classe che si occupa del calcolo delle statistiche, che verranno in seguito passate alla classe JSONBuilder,
 * per poter produrre i vari jsonobject corrispondenti alle rotte del controller
 * 
 * @author sup3r
 * @author nicol
 */
public class Stats 
{
	/*
	 * Da usare per il metodo average
	 */
	static LocalDate firstDate;
	static LocalDate lastDate;
	
	
	public static LocalDate getFirstDate() {
		return firstDate;
	}

	public static void setFirstDate(LocalDate firstDate) {
		Stats.firstDate = firstDate;
	}

	public static LocalDate getLastDate() {
		return lastDate;
	}

	public static void setLastDate(LocalDate lastDate) {
		Stats.lastDate = lastDate;
	}

	/**
	 * Metodo che calcola la media mensile degli eventi, in base al periodo scelto dall'utente
	 * 
	 * @param n Numero di eventi
	 * @param period Stringa contenente il periodo scelto dall'utente
	 * 
	 * @return av Media mensile degli eventi
	 */
	public double average(int n, String period) 
	{
		/*
		 * if((this.firstDate == null) || (this.lastDate == null))
		 * 		Vuol dire che la lista degli eventi è vuota, gestire errore 
		 */
		
		double av;	
		
		LocalDate startDate;
		LocalDate endDate;
		
		if(period.equals(""))
		{
			startDate = firstDate;
			endDate = lastDate;
		}
		else
		{
			startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
			endDate = LocalDate.parse(period.substring(period.indexOf(',') + 1, period.length()));
		}

		av = (30 * n) / (double) ChronoUnit.DAYS.between(startDate, endDate);
		av = Math.floor(av*100)/100;
		
		return av;		
	}
	
	/**
	 * Metodo che restituisce il minimo
	 * 
	 * @param events Vettore di eventi tra i quali si cerca di calcolare il minimo
	 * 
	 * @return msg Stringa che rivela il minimo e il mese in cui questo viene raggiunto
	 * 
	 */
	public String min(Vector<Event> events) 
	{		
		int [] counter = eventsPerMonth(events);
		
		int monthMin = minValueIndex(counter) + 1;
		int min = counter[monthMin - 1];
		
		String msg = min + ", raggiunto nel mese di " + monthToString(monthMin);
		return msg;
	}
	
	/**
	 * Metodo che restituisce il massimo
	 * 
	 * @param events Vettore di eventi tra i quali si cerca di calcolare il massimo
	 * 
	 * @return msg Stringa che rivela il massimo e il mese in cui questo viene raggiunto
	 * 
	 */
	public String max(Vector<Event> events) 
	{	
		int [] counter = eventsPerMonth(events);
		
		int monthMax = maxValueIndex(counter) + 1;
		int max = counter[monthMax - 1];
		
		String msg = max + ", raggiunto nel mese di " + monthToString(monthMax);
		return msg;	
	}
	
	/**
	 * Metodo che converte un numero compreso tra 1 e 12 nella stringa del mese
	 * 
	 * @param month Numero del mese corrispondente 
	 * 
	 * @return str Stringa che rivela il mese a cui è associato il numero month
	 */
	public String monthToString(int month)
	{
		String str = "";
		
		switch(month)
		{
			case 1:
				str = "Gennaio";
				break;
			case 2:
				str = "Febbraio";
				break;
			case 3:
				str = "Marzo";
				break;
			case 4:
				str = "Aprile";
				break;
			case 5:
				str = "Maggio";
				break;
			case 6:
				str = "Giugno";
				break;
			case 7:
			    str = "Luglio";
			    break;
			case 8:
			    str = "Agosto";
			    break;
			case 9:
				str = "Settembre";
				break;
			case 10:
				str = "Ottobre";
				break;
			case 11:
				str = "Novembre";
				break;
			case 12:
				str = "Dicembre";
				break;
		}
		
		return str;
	}
	
	/**
	 * Metodo che crea un array contenente il numero di eventi accaduti in ogni mese
	 * 
	 * @param events Vettore di eventi 
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
	 * @return maxIndex Indice dell'array a cui corrisponde il valore massimo
	 */
	private int maxValueIndex(int [] vet)
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
	 * @return minIndex Indice dell'array a cui corrisponde il valore minimo
	 */
	private int minValueIndex(int [] vet)
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
}
	