package it.univpm.app.ticketmaster.stats;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;
import it.univpm.app.ticketmaster.controller.EventsController;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

class StatsTest 
{/*
	
	Stats stats = new Stats();
	
	Vector<Event> events = new Vector<Event>();
	
	Filter filter;
	
	Event event1;
	Event event2;
	Event event3;
	
	String states; 
	String cities; 
	String period; 
	String segment; 
	String genres;

	@BeforeEach
	void setUp() throws Exception 
	{
		LocalDate locDt1 = LocalDate.parse("2022-01-01");
		LocalDate locDt2 = LocalDate.parse("2022-01-31");
		LocalDate locDt3 = LocalDate.parse("2022-02-14");
		
		event1 = new Event("Brooklyn Nets vs. Los Angeles Lakers",
				   "https://www.ticketmaster.com/event/Z7r9jZ1AdFUj9",
				   "19:30:00",
				   locDt1,
				   "Barclays Center",
				   "Brooklyn",
				   "New York",
				   "Sports",
                   "Basketball");
                
		event2 = new Event("Golden State Warriors vs. Phoenix Suns",
                	"https://www.ticketmaster.com/golden-state-warriors-vs-phoenix-suns-san-francisco-california-03-30-2022/event/1C005B12A59E3CBB",
                	"19:00:00",
                	locDt2,
		            "Chase Center",
		            "San Francisco",
		            "California",
		            "Sports",
		            "Basketball");

		event3 = new Event("Phoenix Suns vs. Indiana Pacers",
                	"https://www.ticketmaster.com/phoenix-suns-vs-indiana-pacers-phoenix-arizona-01-22-2022/event/19005B13479C3E4B",
                	"19:00:00",
                	locDt3,
                	"Footprint Center",
                	"Phoenix",
                	"Arizona",
                	"Sports",
                	"Basketball");

		events.add(event1);
		events.add(event2);
		events.add(event3);
	}
	

	@Test
	void testCorrectAverage() 
	{
		int numberEvents = events.size();
		String period = "2022-01-01,2022-02-14";
		double av = stats.average(numberEvents, period);
	
		assertEquals(av, 2);
	}
	
	@Test
	void testMin() 
	{
		String minMessage = stats.min(events);
		assertEquals(minMessage, "0, in March");
	}
	
	@Test
	void testMax() 
	{
		String maxMessage = stats.max(events);
		assertEquals(maxMessage, "2, in January");
	}
	
	*/
}
