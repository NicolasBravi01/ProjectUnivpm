package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.InvalidFilterException;
import it.univpm.app.ticketmaster.exception.InvalidNameException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.model.Event;

/**
* Classe che si occupa dell'inserimento del filtro immesso dall'utente
* 
* @author sup3r
*/
public class Filter implements Cloneable
{
	Vector<String> states;
	Vector<String> cities;
	LocalDate startDate;
	LocalDate endDate;
	String segment;
	Vector<String> genres;

	
	public Filter(Vector<String> states, Vector<String> cities, LocalDate startDate, LocalDate endDate, String segment, Vector<String> genres) //throws InvalidNameException
	{
		this.states = states;
		this.cities = cities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.segment = segment;
		this.genres = genres;
		
		//check();
	}
	
	public Filter(Vector<String> states, Vector<String> cities, String period, String segment, Vector<String> genres) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException, InvalidNameException
	{
		this.states = states;
		this.cities = cities;
		loadPeriod(period);
		this.segment = segment;
		this.genres = genres;
		
		check();
	}
	
	public Filter(String states, String cities, String period, String segment, String genres) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException, InvalidNameException
	{
		this.states = convertToVectorOfStrings(states);
		this.cities = convertToVectorOfStrings(cities);
		loadPeriod(period);
		this.segment = segment;
		this.genres = convertToVectorOfStrings(genres);
		
		check();
	}
	
	public Filter(String period) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException
	{
		reset();
		loadPeriod(period);
	}
	
	public Filter()
	{
		reset();
	}
	
	
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
	
	
	public void reset()
	{
		this.states = new Vector<String>();
		this.cities = new Vector<String>();
		this.startDate = null;
		this.endDate = null;
		this.segment = "";
		this.genres = new Vector<String>();
	}
	
	
	/**
	 * Metodo che restituisce una copia dell'oggetto stesso
	 * 
	 * @see it.univpm.app.ticketmaster.filter.stats.Stats
	 * 
	 * @return clone FilterImpl
	 */
	public Filter clone()
	{
		Filter clone;
		try
		{
			clone = (Filter) super.clone();
		}
		catch (CloneNotSupportedException e) 
		{
			clone = this.copy();
		}
		
		return clone;
	}
	
	
	/*
	 * Nel caso in cui il metodo FilterImpl.clone() generi l'eccezione CloneNotSupportedException,
	 * viene restituita l'istanza di un nuovo oggetto con i valori di quello che chiama questo metodo
	 * 
	 */
	private Filter copy()
	{
		return new Filter(this.states, this.cities, this.startDate, this.endDate, this.segment, this.genres);	
	}
	

	public boolean isIncludedState(String state)
	{
		boolean isIncluded = ((state == null) || (this.states.isEmpty()) || this.states.contains(state));
		return isIncluded;
	}	
	
	public boolean isIncludedCity(String city)
	{
		boolean isIncluded =  ((city == null) ||(this.cities.isEmpty()) || this.cities.contains(city));
		return isIncluded;
	}	
	
	public boolean isIncludedDate(LocalDate localDate) 
	{
		boolean isIncluded = true;
		
		if(this.startDate != null && this.endDate != null)
		{
			isIncluded = localDate.isAfter(this.startDate.minusDays(1)) && localDate.isBefore(this.endDate.plusDays(1));
		}
		
		return isIncluded;
	}
	
	public boolean isIncludedSegment(String segment)
	{
		boolean isIncluded = ((segment == null) || (this.segment.isEmpty()) || this.segment.equals(segment));
		return isIncluded;
	}
	
	public boolean isIncludedGenre(String genre)
	{
		boolean isIncluded = ((genre == null) || (this.genres.isEmpty()) || this.genres.contains(genre));
		return isIncluded;
	}
	
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
	 * @throws InvalidFilterException 
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
			
			this.startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
			this.endDate = LocalDate.parse(period.substring(period.indexOf(',') + 1, period.length()));
				
			if(this.startDate == null || this.endDate == null)
				throw new NullDateException("Period not identifed");
				
			if(this.startDate.isAfter(endDate))
				throw new IncorrectOrderOfDatesException("The first date can't be after the second one");		
		}
	}
	
	
	
	public void check() throws InvalidNameException
	{
		checkStates();
		checkCities();
		checkSegment();
		checkGenres();		
	}
	
	
	
	private void checkStates() throws InvalidNameException
	{
		if(this.states != null && !this.states.isEmpty())
		{							
			removeSpaces(this.states);
			
			int i = 0;		
			
			while((i < this.states.size()) && (EventsFilter.getStates().contains(this.states.get(i))))			
				i++;
			
			if(i != this.states.size())
				throw new InvalidNameException("Invalid states'name");
		}		
	}
	
	private void checkCities() throws InvalidNameException
	{
		if(this.cities != null && !this.cities.isEmpty())
		{
			removeSpaces(this.cities);
			
			int i = 0;
			
			while((i < this.cities.size()) && (EventsFilter.getCities().contains(this.cities.get(i))))
				i++;
			
			if(i != this.cities.size())
				throw new InvalidNameException("Invalid cities'name");
		}		
	}
	
	private void checkSegment() throws InvalidNameException
	{
		if(this.segment != null && !this.segment.isEmpty())
		{
			removeSpaces(this.segment);
			
			if(EventsFilter.getSegments().contains(this.segment))
				throw new InvalidNameException("Invalid segment's name");
		}		
	}
	
	private void checkGenres() throws InvalidNameException
	{
		if(this.genres != null && !this.genres.isEmpty())
		{
			removeSpaces(this.genres);
			
			int i = 0;
			
			while((i < this.genres.size()) && (EventsFilter.getGenres().contains(this.genres.get(i))))
				i++;
			
			if(i != this.genres.size())
				throw new InvalidNameException("Invalid genres'name");
		}		
	}
	
	
	
	/**
	 * Metodo che elimina tutti gli spazi prima e dopo la stringa d'interesse per ogni elemento in lista
	 */
	private void removeSpaces(Vector<String> list)
	{
		for(int i = 0; i < list.size(); i ++)
		{
			while(list.get(i).startsWith(" "))
				list.set(i, list.get(i).substring(1, list.get(i).length()));
			
			while(this.states.get(i).endsWith(" "))
				list.set(i, list.get(i).substring(0, list.get(i).length() - 1));
		}		
	}
	
	
	/**
	 * Metodo che elimina tutti gli spazi prima e dopo la stringa d'interesse
	 */
	private void removeSpaces(String str)
	{
		while(str.startsWith(" "))
			str = str.substring(1, str.length());
		
		while(str.endsWith(" "))
			str = str.substring(0, str.length() - 1);		
	}
	
}
