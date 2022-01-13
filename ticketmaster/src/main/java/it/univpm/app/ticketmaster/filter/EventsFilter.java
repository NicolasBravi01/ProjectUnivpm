package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.util.Vector;

import it.univpm.app.ticketmaster.model.Event;

/**
 * Classe che si occupa del filtraggio degli eventi
 * 
 * @author sup3r
 */
public class EventsFilter 
{
	/**
	 * Lista contenente tutti gli eventi
	 */
	static Vector<Event> events = new Vector<Event>();
	
	/**
	 * Lista contenente tutti gli stati
	 */
	static Vector<String> states = new Vector<String>();
	
	/**
	 * Lista contenente tutte le città
	 */
	static Vector<String> cities = new Vector<String>();
	
	/**
	 * Lista contenente tutti i segmenti
	 */
	static Vector<String> segments = new Vector<String>();
	
	/**
	 * Lista contenente tutti i generi
	 */
	static Vector<String> genres = new Vector<String>();
	
	/**
	 * Data del primo evento in ordine cronologico
	 * 
	 * @see package it.univpm.app.ticketmaster.stats.Stats;
	 */
	static LocalDate firstDate;
	
	/**
	 * Data dell'ultimo evento in ordine cronologico 
	 * 
	 * @see package it.univpm.app.ticketmaster.stats.Stats;
	 */
	static LocalDate lastDate;
	
	
	/**
	 * Getter della variabile events
	 * 
	 * @return events Lista di tutti gli eventi
	 */
	public static Vector<Event> getEvents() {
		return events;
	}
	/**
	 * Metodo static (che verrà utilizzato nella chiamata all'api) che riempe i vettori sopracitati 
	 * leggendo il vettore di tutti gli eventi che viene passato per parametro
	 * 
	 * @param e Vettore di tutti gli eventi 
	 */
	public static void setEvents(Vector<Event> e) {
		events = e;
		
		for(int i = 0; i<events.size(); i++)
			addInformation(events.get(i));
	}
	
	
	/**
	 * Getter della variabile states
	 * 
	 * @return states Lista di tutti gli stati
	 */
	public static Vector<String> getStates() {
		return states;
	}
	
	/**
	 * Getter della variabile cities
	 * 
	 * @return cities Lista di tutte le città
	 */
	public static Vector<String> getCities() {
		return cities;
	}
	
	/**
	 * Getter della variabile segments
	 * 
	 * @return segments Lista di tutti i segmenti
	 */
	public static Vector<String> getSegments() {
		return segments;
	}
	
	/**
	 * Getter della variabile genres
	 * 
	 * @return genres Lista di tutti i generi
	 */
	public static Vector<String> getGenres() {
		return genres;
	}
	
	
	/**
	 * Getter della variabile firstDate
	 * 
	 * @return firstDate Data del primo evento in ordine cronologico
	 */
	public static LocalDate getFirstDate() {
		return firstDate;
	}
	/**
	 * Setter della variabile firstDate
	 */
	public static void setFirstDate(LocalDate date) {
		firstDate = date;
	}
	
	
	/**
	 * Getter della variabile lastDate
	 * 
	 * @return lastDate Data dell'ultimo evento in ordine cronologico
	 */
	public static LocalDate getLastDate() {
		return lastDate;
	}
	/**
	 * Setter della variabile lastDate
	 */
	public static void setLastDate(LocalDate date) {
		lastDate = date;
	}
	
	
	/**
	 * Metodo che aggiunge le informazioni contenute nell'evento ai vettori sopracitati
	 * 
	 * @param e Evento 
	 */
	private static void addInformation(Event e) {
		addState(e.getState());
		addCity(e.getCity());
		addSegment(e.getSegment());
		addGenre(e.getGenre());
		
		updatePeriodStats(e.getLocalDate());
	}
	
	
	/**
	 * Metodo che aggiunge uno stato alla lista di tutti gli stati se non è presente
	 * 
	 * @param state Stringa contenente il nome dello stato
	 */
	private static void addState(String state) {
		if(!states.contains(state))
			states.add(state);
	}
	
	/**
	 * Metodo che aggiunge una città alla lista di tutte le città se non è presente
	 * 
	 * @param city Stringa contenente il nome della città
	 */
	private static void addCity(String city) {
		if(!cities.contains(city))
			cities.add(city);
	}
	
	/**
	 * Metodo che aggiunge un segmento alla lista di tutti i segmenti se non è presente
	 * 
	 * @param segment Stringa contenente il nome del segmento
	 */
	private static void addSegment(String segment) {
		if(!segments.contains(segment))
			segments.add(segment);
	}
	
	/**
	 * Metodo che aggiunge un genere alla lista di tutti i generi se non è presente
	 * 
	 * @param genre Stringa contenente il nome del genere
	 */
	private static void addGenre(String genre) {
		if(!genres.contains(genre))
			genres.add(genre);
	}
	
	/**
	 * Metodo che assegna i valori ai parametri firstDate e lastDate
	 * 
	 * @param date Data dell'evento
	 */
	private static void updatePeriodStats(LocalDate date)
	{		
		if((firstDate == null))
		{
			firstDate = date;
			lastDate = date;
		}
		else
		{
			if(date.isBefore(firstDate))
			{
				firstDate = date;
			}
			else if(date.isAfter(lastDate))
			{
				lastDate = date;
			}
		}		
	}
	
	
	/**
	 * Metodo static che restituisce la lista degli eventi filtrati, secondo i parametri immessi dall'utente,
	 * a partire da una lista di eventi passata come parametro
	 * 
	 * @param filter Filtro inserito dall'utente
	 * @param eventsToFilter Lista di eventi da filtrare 
	 * 
	 * @return filteredEvents Lista di tutti gli eventi filtrati
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.controller
	 */
	public static Vector<Event> getFilteredEvents (Filter filter, Vector<Event> eventsToFilter) 
	{
		Vector<Event> filteredEvents = new Vector<Event>();
		Event e;
	
		for(int i=0; i<eventsToFilter.size(); i++)
		{			
			e = eventsToFilter.get(i);
			
			if(filter.isIncludedEvent(e))
			{
				filteredEvents.add(e);
			}
		}
		
		return filteredEvents;
	}
	
	/**
	 * Metodo static che restituisce la lista degli eventi filtrati, secondo i parametri immessi dall'utente,
	 * a partire dalla lista degli eventi
	 * 
	 * @param filter Filtro inserito dall'utente
	 * 
	 * @return filteredEvents Lista di tutti gli eventi filtrati
	 * 
	 * @see it.univpm.app.ticketmaster.filter.Filter
	 * @see it.univpm.app.ticketmaster.controller
	 */
	public static Vector<Event> getFilteredEvents (Filter filter) 
	{
		return getFilteredEvents(filter, events);
	}
	
	
	
}
