package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;

public class Event 
{
	/**
	 * Nome dell'evento
	 */
	private String name;
	
	/**
	 * Url dell'evento
	 */
	private String url;
	
	/**
	 * Orario locale in cui inizia l'evento
	 */
	private String localTime;
	
	/**
	 * Data in cui si verifica l'evento
	 */
	private LocalDate localDate;
	
	/**
	 * Nome del luogo in cui si verifica l'evento 
	 */
	private String venue;
	
	/**
	 * Città in cui si verifica l'evento 
	 */
	private String city;
	
	/**
	 * Stato in cui si verifica l'evento 
	 */
	private String state;
	
	/**
	 * Segmento a cui appartiene l'evento 
	 */
	private String segment;
	
	/**
	 * Genere a cui appartiene l'evento 
	 */
	private String genre;
	
	
	/**
	 * Costruttore della classe Event
	 * 
	 * @param name Stringa contenente il nome dell'evento
	 * @param url Stringa contenente l'url dell'evento
	 * @param localTime Stringa contenente l'orario locale dell'evento
	 * @param localDate LocalDate contenente la data dell'evento
	 * @param venue Stringa contenente il nome del luogo in cui si verifica l'evento
	 * @param city Stringa contenente il nome della città in cui si verifica l'evento
	 * @param state Stringa contenente il nome dello stato in cui si verifica l'evento
	 * @param segment Stringa contenente il nome del segmento a cui l'evento appartiene
	 * @param genre Stringa contenente il nome del genere a cui l'evento appartiene
	 */
	public Event(String name, String url, String localTime, LocalDate localDate, String venue, String city,
			String state, String segment, String genre)
	{
		this.name = name;
		this.url = url;
		this.localTime = localTime;
		this.localDate = localDate;
		this.venue = venue;
		this.city = city;
		this.state = state;
		this.segment = segment;
		this.genre = genre;
		
		checkValues();
	}
	
	
	/**
	 * Getter della variabile name
	 * 
	 * @return name Stringa contenente il nome dell'evento
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter della variabile name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Getter della variabile url
	 * 
	 * @return url Stringa contenente l'url dell'evento
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Setter della variabile url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	/**
	 * Getter della variabile localTime
	 * 
	 * @return localTime Stringa contenente l'orario locale dell'evento
	 */
	public String getLocalTime() {
		return localTime;
	}
	/**
	 * Setter della variabile localTime
	 */
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}
	
	
	/**
	 * Getter della variabile localDate
	 * 
	 * @return localDate LocalDate contenente la data dell'evento
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}
	/**
	 * Setter della variabile localDate
	 */
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	
	/**
	 * Getter della variabile venue
	 * 
	 * @return venue Stringa contenente il nome del luogo in cui si verifica l'evento
	 */
	public String getVenue() {
		return venue;
	}
	/**
	 * Setter della variabile localTime
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	
	/**
	 * Getter della variabile city
	 * 
	 * @return city Stringa contenente il nome della città in cui si verifica l'evento
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Setter della variabile city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	/**
	 * Getter della variabile state
	 * 
	 * @return state Stringa contenente il nome della città in cui si verifica l'evento
	 */
	public String getState() {
		return state;
	}
	/**
	 * Setter della variabile state
	 * 
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	/**
	 * Getter della variabile segment
	 * 
	 * @return segment Stringa contenente il nome del segmento a cui l'evento appartiene
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * Setter della variabile segment
	 * 
	 */
	public void setSegment(String segment) {
		this.segment = segment;
	}
	
	
	/**
	 * Getter della variabile genre
	 * 
	 * @return genre Stringa contenente il nome del genere a cui l'evento appartiene
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * Setter della variabile genre
	 * 
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Override del metodo toString 
	 */
	@Override
	public String toString() 
	{
		String message = 
				"Name: " + name + System.lineSeparator() +
				"Url: " + url + System.lineSeparator() +
				"LocalTime: " + localTime + System.lineSeparator() +
				"LocalDate: " + localDate + System.lineSeparator() +
				"Venue: " + venue + System.lineSeparator() +
				"City: " + city + System.lineSeparator() +
				"State: " + state + System.lineSeparator() +
				"Segment: " + segment + System.lineSeparator() +
				"Genre: " + genre + System.lineSeparator() +
				System.lineSeparator();
		
		return message;
	}
	
	/**
	 * Metodo che controlla che tutti i parametri inseriti non contengano spazi superflui
	 */
	private void checkValues()
	{
		this.state = removeSpaces(this.state);
		this.city = removeSpaces(this.city);
		this.segment = removeSpaces(this.segment);
		this.genre = removeSpaces(this.genre);
	}
	
	/**
	 * Metodo che elimina tutti gli spazi prima e dopo la stringa d'interesse
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
