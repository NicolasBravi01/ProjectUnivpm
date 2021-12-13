package it.univpm.app.ticketmaster.model;

import java.time.LocalDate;

public class Event 
{
	private String nome;
	private String url;
	private LocalDate localDate;
	private String localTime;
	private String segment;
	private String genre;
	private String city;
	private String state;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	
	public Event(String nome, String url, LocalDate localDate, String localTime, String segment, String genre,
			String city, String state) 
	{
		this.nome = nome;
		this.url = url;
		this.localDate = localDate;
		this.localTime = localTime;
		this.segment = segment;
		this.genre = genre;
		this.city = city;
		this.state = state;
	}
	
	
	@Override
	public String toString() 
	{
		return "Event [nome=" + nome + ", url=" + url + ", localDate=" + localDate + ", localTime=" + localTime
				+ ", segment=" + segment + ", genre=" + genre + ", city=" + city + ", state=" + state + "]";
	}
	
}
