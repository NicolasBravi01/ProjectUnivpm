package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;

public class Event 
{
	private String name;
	private String url;
	private String localTime;
	private LocalDate localDate;
	private String venue;
	private String segment;
	private String genre;
	
	
	public String getNome() {
		return name;
	}
	public void setNome(String name) {
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
	
	
	public Event(String name, String url, String localTime, LocalDate localDate, String venue, String segment, String genre) {
		this.name = name;
		this.url = url;
		this.localTime = localTime;
		this.localDate = localDate;
		this.venue = venue;
		this.segment = segment;
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
				"Segment: " + segment + System.lineSeparator() +
				"Genre: " + genre + System.lineSeparator() +
				System.lineSeparator();
		
		return message;
	}
	
	
	
}
