package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;

public class Event 
{
	private String name;
	private String url;
	private LocalDate localDate;
	private String localTime;
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
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	public String getLocalTime() {
		return localTime;
	}
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
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
	
	
	public Event(String name, String url, LocalDate localDate, String localTime, String segment, String genre) {
		this.name = name;
		this.url = url;
		this.localDate = localDate;
		this.localTime = localTime;
		this.segment = segment;
		this.genre = genre;
	}
	
}
