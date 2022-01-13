package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.InvalidNameException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.model.Event;

/**
* Classe contenente i filtri per poter selezionare un gruppo di eventi
* 
* @author sup3r
*/
public class Filter
{
	/*
	 * Lista di stati per cui si vuole filtrare
	 */
	Vector<String> states;
	
	/*
	 * Lista di città per cui si vuole filtrare
	 */
	Vector<String> cities;
	
	/*
	 * Data che rappresenta l'inizio di un periodo
	 */
	LocalDate startDate;
	
	/*
	 * Data che rappresenta la fine di un periodo
	 */
	LocalDate endDate;
	
	/*
	 * Segmento per cui si vuole filtrare
	 */
	String segment;
	
	/*
	 * Lista di generi per cui si vuole filtrare
	 */
	Vector<String> genres;

	
	/*
	 * Costruttore con cui è possibile inizializzare tutti gli attributi
	 */
	public Filter(Vector<String> states, Vector<String> cities, LocalDate startDate, LocalDate endDate, String segment, Vector<String> genres) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException, InvalidNameException
	{
		this.states = states;
		this.cities = cities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.segment = segment;
		this.genres = genres;
		
		check();
	}
	
	
	/*
	 * Costruttore con cui è possibile inizializzare tutti gli attributi, inserendo una stringa contenente il periodo
	 * al posto di startDate e endDate
	 */
	public Filter(Vector<String> states, Vector<String> cities, String period, String segment, Vector<String> genres) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException, InvalidNameException
	{
		this.states = states;
		this.cities = cities;
		loadPeriod(period);
		this.segment = segment;
		this.genres = genres;
		
		check();
	}
	
	
	/*
	 * Costruttore con cui è possibile inizializzare tutti gli attributi, inserendo una stringa contenente il periodo
	 * al posto di startDate e endDate ed inoltre delle stringhe che vengono convertite in liste
	 */
	public Filter(String states, String cities, String period, String segment, String genres) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException, InvalidNameException
	{
		this.states = convertToVectorOfStrings(states);
		this.cities = convertToVectorOfStrings(cities);
		loadPeriod(period);
		this.segment = segment;
		this.genres = convertToVectorOfStrings(genres);
		
		check();
	}
	
	/*
	 * Costruttore con cui è possibile inizializzare solo il periodo, gli altri filtri per il momento non vengono considerati
	 */
	public Filter(String period) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException
	{
		reset();
		loadPeriod(period);
	}
	
	
	/*
	 * Costruttore con cui i filtri per il momento non vengono considerati
	 */
	public Filter()
	{
		reset();
	}
	
	
	
	/*
	 * @return
	 */
	public Vector<String> getStates() {
		return states;
	}
	public void setStates(Vector<String> states) {
		this.states = states;
	}
	public void setStates(String state) {
		this.states = new Vector<String>();
		this.states.add(state);
	}
	public Vector<String> getCities() {
		return cities;
	}
	public void setCities(Vector<String> cities) {
		this.cities = cities;
	}
	public void setCities(String city) {
		this.cities = new Vector<String>();
		this.cities.add(city);
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public void setPeriod(LocalDate startDate, LocalDate endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getPeriod(){
		String period = "";
		if((this.startDate != null) && (this.endDate != null))
			period = this.startDate + "," + this.endDate;
		
		return period;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public Vector<String> getGenres() {
		return genres;
	}
	public void setGenres(Vector<String> genres) {
		this.genres = genres;
	}
	public void setGenres(String genre) {
		this.genres = new Vector<String>();
		this.genres.add(genre);
	}
	
	
	/*
	 * Metodo che resetta tutti filtri
	 */
	public void reset()
	{
		this.states = new Vector<String>();
		this.cities = new Vector<String>();
		this.startDate = null;
		this.endDate = null;
		this.segment = "";
		this.genres = new Vector<String>();
	}
	

	
	/*
	 * Metodo che ritorna true se lo stato passato per parametro appartiene alla lista di stati per cui filtrare
	 */
	private boolean isIncludedState(String state)
	{
		boolean isIncluded = ((state == null) || (this.states.isEmpty()) || this.states.contains(state));
		return isIncluded;
	}	
	
	/*
	 * Metodo che ritorna true se la città passata per parametro appartiene alla lista di città per cui filtrare
	 */
	private boolean isIncludedCity(String city)
	{
		boolean isIncluded =  ((city == null) ||(this.cities.isEmpty()) || this.cities.contains(city));
		return isIncluded;
	}	
	
	
	/*
	 * Metodo che ritorna true se la data passata come parametro è compresa nel periodo per cui filtrare.
	 * Nel caso in cui il filtro per il periodo non è stato inserito, ritorna true
	 */
	private boolean isIncludedDate(LocalDate localDate) 
	{
		boolean isIncluded = true;
		
		if(this.startDate != null && this.endDate != null)
		{
			isIncluded = localDate.isAfter(this.startDate.minusDays(1)) && localDate.isBefore(this.endDate.plusDays(1));
		}
		
		return isIncluded;
	}
	
	
	/*
	 * Metodo che ritorna true se il segmento passato come parametro coincide con il segmento per cui filtrare
	 * oppure se il filtro per segmento è vuoto, ovvero non va preso in considerazione
	 */
	private boolean isIncludedSegment(String segment)
	{
		boolean isIncluded = ((segment == null) || (this.segment.isEmpty()) || this.segment.equals(segment));
		return isIncluded;
	}
	
	
	/*
	 * Metodo che ritorna true se il genere passato come parametro appartiene alla lista di generi per cui filtrare
	 * oppure se il filtro per generi è vuoto, ovvero non va preso in considerazione
	 */
	private boolean isIncludedGenre(String genre)
	{
		boolean isIncluded = ((genre == null) || (this.genres.isEmpty()) || this.genres.contains(genre));
		return isIncluded;
	}
	
	
	/*
	 * Metodo che ritorna true se l'evento passato come parametro è coerente con tutti i filtri
	 */
	public boolean isIncludedEvent(Event e)
	{
		boolean isIncluded = isIncludedState(e.getState()) && isIncludedCity(e.getCity()) && isIncludedDate(e.getLocalDate())
							&& isIncludedSegment(e.getSegment()) && isIncludedGenre(e.getGenre());		 
		return isIncluded;
	}
	
	
	/**
	 * Metodo (invocato dal costruttore) che converte un elenco di parole separate da una virgola in un vettore di stringhe 
	 * 
	 * @param toParse Stringa contenente parole separate da una virgola
	 * 
	 * @return list Lista di stringhe
	 */
	private Vector<String> convertToVectorOfStrings(String toParse)
	{
		Vector<String> list = new Vector<String>();
		
		if(!toParse.isEmpty())
		{						
			String [] splitString = toParse.split(",");
			
			for(int i = 0; i<splitString.length; i++)
				list.add(splitString[i]);
		}
		
		return list;
	}
	
	
	/**
	 * Metodo (invocato dal costruttore) che leggendo una stringa contenente due date (di tipo LocalDate) separate da una virgola
	 * assegna la prima data al valore startDate e la seconda data al valore endDate 
	 * 
	 * @param period Periodo scelto dall'utente
	 * 
	 * @throws DateTimeParseException 
	 * @throws NullDateException 
	 * @throws IncorrectOrderOfDatesException 
	 */
	private void loadPeriod(String period) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException
	{
		if(period.isEmpty())
		{
			this.startDate = null;
			this.endDate = null;
		}
		
		else
		{
			int indexComma = period.indexOf(',');
			
			if(indexComma < 0)
				throw new NullDateException("Period not identifed");
			
			String strStartDate = period.substring(0, indexComma);
			String strEndDate = period.substring(indexComma + 1, period.length());
			
			strStartDate = removeSpaces(strStartDate);
			strEndDate = removeSpaces(strEndDate);
			
			this.startDate = LocalDate.parse(strStartDate);
			this.endDate = LocalDate.parse(strEndDate);
				
			if(this.startDate == null || this.endDate == null)
				throw new NullDateException("Period not identifed");
				
			if(this.startDate.isAfter(endDate))
				throw new IncorrectOrderOfDatesException("The first date can't be after the second one");		
		}
	}
	
	
	/**
	 * Metodo che controlla se i parametri inseriti, dopo l'eliminazione di spazi superflui,
	 * corrispondono a quelli presenti
	 * 
	 * @throws InvalidNameException
	 */
	public void check() throws InvalidNameException
	{
		checkStates();
		checkCities();
		checkSegment();
		checkGenres();		
	}
	
	/**
	 * Metodo che controlla se lo stato inserito, dopo l'eliminazione di spazi superflui,
	 * è presente nella lista degli stati
	 * 
	 * @throws InvalidNameException
	 */
	private void checkStates() throws InvalidNameException
	{
		if(this.states != null && !this.states.isEmpty())
		{							
			this.states = removeSpaces(this.states);
			
			int i = 0;		
			
			while((i < this.states.size()) && (EventsFilter.getStates().contains(this.states.get(i))))			
				i++;
			
			if(i != this.states.size())
				throw new InvalidNameException("Invalid states'name");
		}		
	}
	
	/**
	 * Metodo che controlla se la città inserita, dopo l'eliminazione di spazi superflui,
	 * è presente nella lista delle città
	 * 
	 * @throws InvalidNameException
	 */
	private void checkCities() throws InvalidNameException
	{
		if(this.cities != null && !this.cities.isEmpty())
		{
			this.cities = removeSpaces(this.cities);
			
			int i = 0;
			
			while((i < this.cities.size()) && (EventsFilter.getCities().contains(this.cities.get(i))))
				i++;
			
			if(i != this.cities.size())
				throw new InvalidNameException("Invalid cities'name");
		}		
	}
	
	/**
	 * Metodo che controlla se il segmento inserito, dopo l'eliminazione di spazi superflui,
	 * è presente nella variabile che rappresenta il segmento
	 * 
	 * @throws InvalidNameException
	 */
	private void checkSegment() throws InvalidNameException
	{
		if(this.segment != null && !this.segment.isEmpty())
		{
			this.segment = removeSpaces(this.segment);
			
			if(!EventsFilter.getSegments().contains(this.segment))
				throw new InvalidNameException("Invalid segment's name");
		}		
	}
	
	/**
	 * Metodo che controlla se il genere inserito, dopo l'eliminazione di spazi superflui,
	 * è presente nella lista dei generi
	 * 
	 * @throws InvalidNameException
	 */
	private void checkGenres() throws InvalidNameException
	{
		if(this.genres != null && !this.genres.isEmpty())
		{
			this.genres = removeSpaces(this.genres);
			
			int i = 0;
			
			while((i < this.genres.size()) && (EventsFilter.getGenres().contains(this.genres.get(i))))
				i++;
			
			if(i != this.genres.size())
				throw new InvalidNameException("Invalid genres'name");
		}		
	}
	
	/**
	 * Metodo che elimina tutti gli spazi prima e dopo la stringa d'interesse per ogni elemento in lista
	 * 
	 * @param list Lista di stringhe
	 * 
	 * @return list Lista di stringhe
	 */
	private Vector<String> removeSpaces(Vector<String> list)
	{
		for(int i = 0; i < list.size(); i ++)
		{
			while(list.get(i).startsWith(" "))
				list.set(i, list.get(i).substring(1, list.get(i).length()));
			
			while(list.get(i).endsWith(" "))
				list.set(i, list.get(i).substring(0, list.get(i).length() - 1));
		}	
		
		return list;
	}
	
	/**
	 * Metodo che elimina tutti gli spazi prima e dopo la stringa d'interesse
	 * 
	 * @param str Stringa
	 * 
	 * @return str Stringa
	 */
	private String removeSpaces(String str)
	{
		while(str.startsWith(" "))
			str = str.substring(1, str.length());
		
		while(str.endsWith(" "))
			str = str.substring(0, str.length() - 1);
		
		return str;
	}
	
}
