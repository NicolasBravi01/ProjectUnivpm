package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;

public class Event 
{
	private String name;
	private String url;
	private String localTime;
	private LocalDate localDate;
	private String venue;
	private String city;
	private String state;
	private String segment;
	private String genre;
	
	
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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocalTime() {
		return localTime;
	}
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	
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
	
	private void checkValues()
	{
		checkState();
		checkCity();
		checkSegment();
		checkGenre();		
	}
	
	
	private void checkState()
	{
		while(this.state.startsWith(" "))
			this.state = this.state.substring(1, this.state.length());
		
		while(this.state.endsWith(" "))
			this.state = this.state.substring(0, this.state.length() - 1);		
	}
	
	
	public void checkCity()
	{
		while(this.city.startsWith(" "))
			this.city = this.city.substring(1, this.city.length());
		
		while(this.city.endsWith(" "))
			this.city = this.city.substring(0, this.city.length() - 1);		
	}
	

	public void checkSegment()
	{
		while(this.segment.startsWith(" "))
			this.segment = this.segment.substring(1, this.segment.length());
		
		while(this.segment.endsWith(" "))
			this.segment = this.segment.substring(0, this.segment.length() - 1);		
	}
	
	public void checkGenre()
	{
		while(this.genre.startsWith(" "))
			this.genre = this.genre.substring(1, this.genre.length());
		
		while(this.genre.endsWith(" "))
			this.genre = this.genre.substring(0, this.genre.length() - 1);		
	}
	
}
