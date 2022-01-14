package it.univpm.app.ticketmaster.filter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import it.univpm.app.ticketmaster.exception.ApiConnectionException;
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.InvalidNameException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.model.Event;

/**
* Classe contenente i filtri per poter selezionare un gruppo di eventi
* 
* @author sup3r
* @author NicolasBravi01
*/
public class Filter
{
	/**
	 * Lista di stati attraverso i quali si vuole filtrare
	 */
	Vector<String> states;
	
	/**
	 * Lista di città attraverso le quali si vuole filtrare
	 */
	Vector<String> cities;
	
	/**
	 * Data che rappresenta l'inizio di un periodo
	 */
	LocalDate startDate;
	
	/**
	 * Data che rappresenta la fine di un periodo
	 */
	LocalDate endDate;
	
	/**
	 * Segmento attraverso il quale si vuole filtrare
	 */
	String segment;
	
	/**
	 * Lista di generi attraverso i quali si vuole filtrare
	 */
	Vector<String> genres;

	
	/**
	 * Costruttore con cui è possibile inizializzare tutti gli attributi
	 * 
	 * @param states Lista di stati attraverso i quali si vuole filtrare
	 * @param cities Lista di città attraverso le quali si vuole filtrare
	 * @param startDate Data dell'inizio di un periodo
	 * @param endDate Data della fine di un periodo
	 * @param segment Stringa che rappresenta il segmento attraverso il quale si vuole filtrare
	 * @param genres Lista di generi attraverso i quali si vuole filtrare
	 * 
	 * @throws IncorrectOrderOfDatesException
	 * @throws InvalidNameException
	 */
	public Filter(Vector<String> states, Vector<String> cities, LocalDate startDate, LocalDate endDate, String segment, Vector<String> genres) throws InvalidNameException, IncorrectOrderOfDatesException
	{
		this.states = states;
		this.cities = cities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.segment = segment;
		this.genres = genres;
		
		check();
	}
	
	
	/**
	 * Costruttore con cui è possibile inizializzare tutti gli attributi, inserendo una stringa contenente il periodo
	 * al posto di startDate e endDate
	 * 
	 * @param states Lista di stati attraverso i quali si vuole filtrare
	 * @param cities Lista di città attraverso le quali si vuole filtrare
	 * @param period Stringa nella quale è inserito il periodo, la prima data è separata dalla seconda da una virgola
	 * @param segment Stringa che rappresenta il segmento attraverso il quale si vuole filtrare
	 * @param genres Lista di generi attraverso i quali si vuole filtrare
	 * 
	 * @throws DateTimeParseException
	 * @throws NullDateException
	 * @throws IncorrectOrderOfDatesException
	 * @throws InvalidNameException
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
	
	
	/**
	 * Costruttore con cui è possibile inizializzare tutti gli attributi, inserendo una stringa contenente il periodo
	 * al posto di startDate e endDate ed inoltre delle stringhe che vengono convertite in liste di stringhe
	 * 
	 * @param states Stringa contenente una lista di stati attraverso i quali si vuole filtrare, separati da una virgola
	 * @param cities Stringa contenente una lista di città attraverso le quali si vuole filtrare, separati da una virgola
	 * @param period Stringa nella quale è inserito il periodo, la prima data è separata dalla seconda da una virgola
	 * @param segment Stringa che rappresenta il segmento attraverso il quale si vuole filtrare
	 * @param genres Stringa contenente una lista di generi attraverso i quali si vuole filtrare, separati da una virgola
	 * 
	 * @throws DateTimeParseException
	 * @throws NullDateException
	 * @throws IncorrectOrderOfDatesException
	 * @throws InvalidNameException
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
	
	/**
	 * Costruttore con cui è possibile inizializzare solo il periodo (gli altri parametri non vengono considerati)
	 * 
	 * @param period Stringa nella quale è inserito il periodo, la prima data è separata dalla seconda da una virgola
	 *
	 * @throws DateTimeParseException
	 * @throws NullDateException
	 * @throws IncorrectOrderOfDatesException
	 */
	public Filter(String period) throws DateTimeParseException, NullDateException, IncorrectOrderOfDatesException
	{
		reset();
		loadPeriod(period);
		checkPeriod();
	}
	
	
	/**
	 * Costruttore nel quale i filtri per il momento non vengono considerati
	 */
	public Filter()
	{
		reset();
	}
	
	
	/**
	 * Getter della variabile states
	 * 
	 * @return states Lista di stati attraverso i quali si vuole filtrare
	 */
	public Vector<String> getStates() {
		return states;
	}
	/**
	 * Setter della variabile states
	 * 
	 * @param states Lista di stati attraverso i quali si vuole filtrare
	 */
	public void setStates(Vector<String> states) {
		this.states = states;
	}
	/**
	 * Setter della variabile states
	 * 
	 * @param state Stringa contenente una lista di stati attraverso i quali si vuole filtrare, separati da una virgola
	 */
	public void setStates(String state) {
		this.states.removeAllElements();
		this.states.add(state);
	}
	
	
	/**
	 * Getter della variabile cities
	 * 
	 * @return cities Lista di città attraverso i quali si vuole filtrare
	 */
	public Vector<String> getCities() {
		return cities;
	}
	/**
	 * Setter della variabile cities
	 * 
	 * @param cities Lista di città attraverso le quali si vuole filtrare
	 */
	public void setCities(Vector<String> cities) {
		this.cities = cities;
	}
	/**
	 * Setter della variabile cities
	 * 
	 * @param city Stringa contenente una lista di città attraverso le quali si vuole filtrare, separate da una virgola
	 */
	public void setCities(String city) {
		this.cities.removeAllElements();
		this.cities.add(city);
	}
	
	
	/**
	 * Getter della variabile startDate
	 * 
	 * @return startDate Data dell'inizio di un periodo
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * Setter della variabile startDate
	 * 
	 * @param startDate Data dell'inizio di un periodo
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * Getter della variabile endDate
	 * 
	 * @return endDate Data della fine di un periodo
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	/**
	 * Setter della variabile endDate
	 * 
	 * @param endDate Data dell'inizio di un periodo
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	/**
	 * Setter che inizializza sia startDate sia endDate
	 * 
	 * @param startDate Data dell'inizio di un periodo
	 * @param endDate Data dell'inizio di un periodo
	 * 
	 */
	public void setPeriod(LocalDate startDate, LocalDate endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	/**
	 * Getter della stringa period
	 * 
	 * @return period Stringa contenente startDate e endDate separate da una virgola
	 */
	public String getPeriod(){
		String period = "";
		if((this.startDate != null) && (this.endDate != null))
			period = this.startDate + "," + this.endDate;
		
		return period;
	}
	
	
	/**
	 * Getter della variabile segment
	 * 
	 * @return segment Stringa che rappresenta il segmento attraverso il quale si vuole filtrare
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * Setter della variabile segment
	 * 
	 * @param segment Stringa che rappresenta il segmento attraverso il quale si vuole filtrare
	 * 
	 */
	public void setSegment(String segment) {
		this.segment = segment;
	}
	
	
	/**
	 * Getter della variabile genres
	 * 
	 * @return genres
	 */
	public Vector<String> getGenres() {
		return genres;
	}
	/**
	 * Setter della variabile genres
	 * 
	 * @param genres Lista di generi attraverso i quali si vuole filtrare
	 */
	public void setGenres(Vector<String> genres) {
		this.genres = genres;
	}
	/**
	 * Setter della variabile genre
	 * 
	 * @param genre Stringa contenente una lista di generi attraverso i quali si vuole filtrare, separati da una virgola
	 */
	public void setGenres(String genre) {
		this.genres.removeAllElements();
		this.genres.add(genre);
	}
	

	/**
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
	

	/**
	 * Metodo che ritorna true se lo stato passato per parametro appartiene alla lista di stati attraverso i quali filtrare,
	 * oppure se il filtro per gli stati non è stato inserito, ovvero non va preso in considerazione
	 * 
	 * @return isIncluded Boolean
	 */
	private boolean isIncludedState(String state)
	{
		boolean isIncluded = ((state == null) || (this.states.isEmpty()) || this.states.contains(state));
		return isIncluded;
	}	
	
	/**
	 * Metodo che ritorna true se la città passata per parametro appartiene alla lista di città attraverso le quali filtrare,
	 * oppure se il filtro per le città non è stato inserito, ovvero non va preso in considerazione
	 * 
	 * @return isIncluded Boolean
	 */
	private boolean isIncludedCity(String city)
	{
		boolean isIncluded =  ((city == null) ||(this.cities.isEmpty()) || this.cities.contains(city));
		return isIncluded;
	}	
	
	/**
	 * Metodo che ritorna true se la data passata come parametro è compresa nel periodo d'interesse;
	 * oppure se il filtro per il periodo non è stato inserito, ovvero non va preso in considerazione
	 * 
	 * @return isIncluded Boolean
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
	
	/**
	 * Metodo che ritorna true se il segmento passato come parametro coincide con il segmento attraverso il quale filtrare,
	 * oppure se il filtro per i segmenti non è stato inserito, ovvero non va preso in considerazione
	 * 
	 * @return isIncluded Boolean
	 */
	private boolean isIncludedSegment(String segment)
	{
		boolean isIncluded = ((segment == null) || (this.segment.isEmpty()) || this.segment.equals(segment));
		return isIncluded;
	}
	
	/**
	 * Metodo che ritorna true se il genere passato come parametro appartiene alla lista di generi attraverso i quali filtrare
	 * oppure se il filtro per i generi non è stato inserito, ovvero non va preso in considerazione
	 * 
	 * @return isIncluded Boolean
	 */
	private boolean isIncludedGenre(String genre)
	{
		boolean isIncluded = ((genre == null) || (this.genres.isEmpty()) || this.genres.contains(genre));
		return isIncluded;
	}
	
	/**
	 * Metodo che ritorna true se l'evento passato come parametro è coerente con tutti i filtri
	 * 
	 * @param e Evento
	 * 
	 * @return isIncluded Boolean
	 */
	public boolean isIncludedEvent(Event e)
	{
		boolean isIncluded = isIncludedState(e.getState()) && isIncludedCity(e.getCity()) && isIncludedDate(e.getLocalDate())
							&& isIncludedSegment(e.getSegment()) && isIncludedGenre(e.getGenre());		 
		return isIncluded;
	}
	
	
	/**
	 * Metodo che converte un elenco di parole separate da una virgola in un vettore di stringhe 
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
	 * Metodo che leggendo una stringa contenente due date (di tipo LocalDate) separate da una virgola
	 * assegna la prima data al valore startDate e la seconda data al valore endDate 
	 * 
	 * @param period Periodo scelto dall'utente
	 * 
	 * @throws DateTimeParseException 
	 * @throws NullDateException 
	 * @throws IncorrectOrderOfDatesException 
	 */
	private void loadPeriod(String period) throws DateTimeParseException, NullDateException
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
		}
	}
	
	
	/**
	 * Metodo che controlla se i parametri inseriti, a seguito dell'eliminazione di spazi superflui,
	 * corrispondono a quelli presenti
	 * 
	 * @throws InvalidNameException
	 * @throws IncorrectOrderOfDatesException 
	 */
	public void check() throws InvalidNameException, IncorrectOrderOfDatesException
	{
		checkStates();
		checkCities();
		checkSegment();
		checkGenres();	
		checkPeriod();
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
	 * Metodo che controlla se le date sono state inserite in ordine cronologico. Se il periodo non è stato
	 * inserito allora l'errore non può essere generato
	 * 
	 * @throws IncorrectOrderOfDatesException
	 */
	public void checkPeriod() throws IncorrectOrderOfDatesException
	{		
		if((this.startDate != null) && (this.startDate.isAfter(endDate)))
			throw new IncorrectOrderOfDatesException("The first date can't be after the second one");
	}
	
	
	/**
	 * Metodo che elimina tutti gli spazi prima e dopo la stringa d'interesse per ogni elemento in lista
	 * 
	 * @param list Lista di stringhe con spazi superflui
	 * 
	 * @return list Lista di stringhe senza spazi superflui
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
	 * @param str Stringa con spazi superflui
	 * 
	 * @return str Stringa senza spazi superflui
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
