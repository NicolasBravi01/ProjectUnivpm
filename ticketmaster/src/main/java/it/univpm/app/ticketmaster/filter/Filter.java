package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.model.Event;

public class Filter implements Cloneable
{
	Vector<String> states;
	Vector<String> cities;
	LocalDate startDate;
	LocalDate endDate;
	String segment;
	Vector<String> genres;
	
	
	boolean error = false;
	
	
	public Filter(Vector<String> states, Vector<String> cities, LocalDate startDate, LocalDate endDate, String segment, Vector<String> genres)
	{
		this.states = states;
		this.cities = cities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.segment = segment;
		this.genres = genres;
	}
	
	public Filter(Vector<String> states, Vector<String> cities, String period, String segment, Vector<String> genres)
	{
		this.states = states;
		this.cities = cities;
		loadPeriod(period);
		this.segment = segment;
		this.genres = genres;
	}
	
	public Filter(String states, String cities, String period, String segment, String genres)
	{
		this.states = convertToVectorOfStrings(states);
		this.cities = convertToVectorOfStrings(cities);
		loadPeriod(period);
		this.segment = segment;
		this.genres = convertToVectorOfStrings(genres);
	}
	
	public Filter(String period)
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
	
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
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
		boolean isIncluded = ((state == null) || (this.states.size() == 0) || this.states.contains(state));
		return isIncluded;
	}	
	
	public boolean isIncludedCity(String city)
	{
		boolean isIncluded =  ((city == null) ||(this.cities.size() == 0) || this.cities.contains(city));
		return isIncluded;
	}	
	
	public boolean isIncludedDate(LocalDate localDate) 
	{
		boolean isIncluded = true;
		
		if(this.startDate != null)
		{
			if(this.endDate == null)
				isIncluded = localDate.isAfter(this.startDate.minusDays(1));
			else
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
		boolean isIncluded = ((genre == null) || (this.genres.size() == 0) || this.genres.contains(genre));
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
	 * @return list Vettore di stringhe
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
	 * Metodo (invocato dal costruttore) che leggendo una stringa contenente due date (in formato LocalDate) separate da una virgola
	 * assegna la prima data al valore startDate e la seconda data al valore endDate 
	 * 
	 * 
	 * @param period Periodo scelto dall'utente
	 */
	private void loadPeriod(String period)
	{
		if(period.isEmpty())
		{
			this.startDate = null;
			this.endDate = null;
		}
		
		else
		{
			try
			{
				this.startDate = LocalDate.parse(period.substring(0, period.indexOf(',')));
				this.endDate = LocalDate.parse(period.substring(period.indexOf(',') + 1, period.length()));
				
				if(this.startDate.isAfter(endDate))
				{
					//this.error = true;
					throw new IncorrectOrderOfDatesException();
				}
			}
			catch(IncorrectOrderOfDatesException e)
			{
				LocalDate temp = startDate;
				startDate = endDate;
				endDate = temp;
			}
			catch(DateTimeParseException e)
			{
				//this.error = true; //Filtro periodo sbagliato
			}
				
		}
	}
	
	
	
}
