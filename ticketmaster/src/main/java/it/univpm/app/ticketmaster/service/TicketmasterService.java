package it.univpm.app.ticketmaster.service;

import java.time.LocalDate;
import java.util.Vector;

import it.univpm.app.ticketmaster.exception.InvalidNameException;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;


/**
 * Classe che smista le informazioni ricevute dalla chiamata all'Api di Ticketmaster
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
public class TicketmasterService 
{
	
	/**
	 * Lista contenente tutti gli eventi
	 */
	Vector<Event> events = new Vector<Event>();
	
	/**
	 * Lista contenente tutti gli stati
	 */
	Vector<String> states = new Vector<String>();
	
	/**
	 * Lista contenente tutte le città
	 */
	Vector<String> cities = new Vector<String>();
	
	/**
	 * Lista contenente tutti i segmenti
	 */
	Vector<String> segments = new Vector<String>();
	
	/**
	 * Lista contenente tutti i generi
	 */
	Vector<String> genres = new Vector<String>();
	
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
	 * Costruttore della classe TicketmasterService che carica le informazioni lette dalla lista di eventi
	 * all'interno delle liste degli stati, delle città, dei segmenti e dei generi
	 * 
	 * @param events Lista di tutti gli eventi
	 */
	public TicketmasterService(Vector<Event> events)
	{
		this.events = events;
		loadData();
	}
	
	
	
	/**
	 * Getter della variabile events
	 * 
	 * @return events Lista di tutti gli eventi
	 */
	public Vector<Event> getEvents() {
		return events;
	}
	/**
	 * Metodo static (che verrà utilizzato nella chiamata all'api) che riempe i vettori sopracitati 
	 * leggendo il vettore di tutti gli eventi che viene passato per parametro
	 * 
	 * @param e Vettore di tutti gli eventi 
	 */
	public void setEvents(Vector<Event> e) {
		events = e;
		
		for(int i = 0; i<events.size(); i++)
			addInformation(events.get(i));
	}
	
	
	/**
	 * Getter della variabile states
	 * 
	 * @return states Lista di tutti gli stati
	 */
	public Vector<String> getStates() {
		return states;
	}
	
	/**
	 * Getter della variabile cities
	 * 
	 * @return cities Lista di tutte le città
	 */
	public Vector<String> getCities() {
		return cities;
	}
	
	/**
	 * Getter della variabile segments
	 * 
	 * @return segments Lista di tutti i segmenti
	 */
	public Vector<String> getSegments() {
		return segments;
	}
	
	/**
	 * Getter della variabile genres
	 * 
	 * @return genres Lista di tutti i generi
	 */
	public Vector<String> getGenres() {
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
	 * Metodo che carica le informazioni lette dall'evento
	 */
	private void loadData()
	{
		for(int i = 0; i < this.events.size(); i++)
			addInformation(this.events.get(i));
	}
	
	
	/**
	 * Metodo che aggiunge le informazioni contenute nell'evento ai vettori sopracitati
	 * e confronta la data con firstDate e endDate
	 * 
	 * @param e Evento 
	 */
	private void addInformation(Event e) {
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
	private void addState(String state) {
		if(!states.contains(state))
			states.add(state);
	}
	
	/**
	 * Metodo che aggiunge una città alla lista di tutte le città se non è presente
	 * 
	 * @param city Stringa contenente il nome della città
	 */
	private void addCity(String city) {
		if(!cities.contains(city))
			cities.add(city);
	}
	
	/**
	 * Metodo che aggiunge un segmento alla lista di tutti i segmenti se non è presente
	 * 
	 * @param segment Stringa contenente il nome del segmento
	 */
	private void addSegment(String segment) {
		if(!segments.contains(segment))
			segments.add(segment);
	}
	
	/**
	 * Metodo che aggiunge un genere alla lista di tutti i generi se non è presente
	 * 
	 * @param genre Stringa contenente il nome del genere
	 */
	private void addGenre(String genre) {
		if(!genres.contains(genre))
			genres.add(genre);
	}
	
	/**
	 * Metodo che confronta la data dell'evento con firstDate e endDate
	 * 
	 * @param date Data dell'evento
	 */
	private void updatePeriodStats(LocalDate date)
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
	 * Metodo che controlla se i parametri inseriti dall'utente, a seguito dell'eliminazione di spazi superflui,
	 * corrispondono a quelli presenti
	 * 
	 * @throws InvalidNameException
	 */
	public void check(Filter filter) throws InvalidNameException
	{
		checkStates(filter.getStates());
		checkCities(filter.getCities());
		checkSegment(filter.getSegment());
		checkGenres(filter.getGenres());	
	}
	
	/**
	 * Metodo che controlla se lo stato inserito, dopo l'eliminazione di spazi superflui,
	 * è presente nella lista degli stati
	 * 
	 * @throws InvalidNameException
	 */
	private void checkStates(Vector<String> filterStates) throws InvalidNameException
	{
		if(filterStates != null && !filterStates.isEmpty())
		{							
			int i = 0;		
			
			while((i < filterStates.size()) && (this.states.contains(filterStates.get(i))))			
				i++;
			
			if(i != filterStates.size())
				throw new InvalidNameException("Invalid states'name");
		}		
	}
	
	/**
	 * Metodo che controlla se la città inserita, dopo l'eliminazione di spazi superflui,
	 * è presente nella lista delle città
	 * 
	 * @throws InvalidNameException
	 */
	private void checkCities(Vector<String> filterCities) throws InvalidNameException
	{
		if(filterCities != null && !filterCities.isEmpty())
		{
			int i = 0;
			
			while((i < filterCities.size()) && (this.cities.contains(filterCities.get(i))))
				i++;
			
			if(i != filterCities.size())
				throw new InvalidNameException("Invalid cities'name");
		}		
	}
	
	/**
	 * Metodo che controlla se il segmento inserito, dopo l'eliminazione di spazi superflui,
	 * è presente nella variabile che rappresenta il segmento
	 * 
	 * @throws InvalidNameException
	 */
	private void checkSegment(String filterSegment) throws InvalidNameException
	{
		if(filterSegment != null && !filterSegment.isEmpty())
		{
			if(!this.segments.contains(filterSegment))
				throw new InvalidNameException("Invalid segment's name");
		}		
	}
	
	/**
	 * Metodo che controlla se il genere inserito, dopo l'eliminazione di spazi superflui,
	 * è presente nella lista dei generi
	 * 
	 * @throws InvalidNameException
	 */
	private void checkGenres(Vector<String> filterGenres) throws InvalidNameException
	{
		if(filterGenres != null && !filterGenres.isEmpty())
		{
			int i = 0;
			
			while((i < filterGenres.size()) && (this.genres.contains(filterGenres.get(i))))
				i++;
			
			if(i != filterGenres.size())
				throw new InvalidNameException("Invalid genres'name");
		}		
	}
	
}
