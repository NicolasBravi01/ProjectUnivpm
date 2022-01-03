package it.univpm.app.ticketmaster.stats;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

public class Stats 
{
	/*
	 * Da usare per il metodo average
	 */
	//static LocalDate firstDate;
	//static LocalDate lastDate;
	
	public double average(int n, String period) 
	{
		double av;		
		
		if(period.equals(""))
		{
			av = (double) n/12;
		}
		else
		{
			LocalDate startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
			LocalDate endDate = LocalDate.parse(period.substring(period.indexOf(',') + 1, period.length()));
			
			av = (double) (30* n) / (double) ChronoUnit.DAYS.between(startDate, endDate);
		}

		av = (double)Math.round(av*100)/100;		
		return av;		
	}
	
	
	public String min(Vector<Event> events) 
	{		
		int [] counter = eventsPerMonth(events);
		
		int monthMin = minValueIndex(counter) + 1;
		int min = counter[monthMin - 1];
		
		String msg = min + ", raggiunto nel mese di " + monthToString(monthMin);
		return msg;
	}
	
	
	public String max(Vector<Event> events) 
	{	
		int [] counter = eventsPerMonth(events);
		
		int monthMax = maxValueIndex(counter) + 1;
		int max = counter[monthMax - 1];
		
		String msg = max + ", raggiunto nel mese di " + monthToString(monthMax);
		return msg;	
	}
	
	
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
	