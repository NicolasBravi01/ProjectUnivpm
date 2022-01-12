package it.univpm.app.ticketmaster.JSONBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.JSONHandler.JSONBuilder;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;

class JSONBuilderTest 
{
	JSONObject obj = new JSONObject();
	
	Vector<Event> eventsToFilter = new Vector<Event>();
	Vector<Event> filteredEvents = new Vector<Event>();
	
	JSONBuilder jb = new JSONBuilder();
	
	EventsFilter eventsFilter = new EventsFilter();
	Filter filter;
	
	Event event1;
	Event event2;
	Event event3;
	String localDate1, localDate2, localDate3;
	LocalDate locDt1, locDt2, locDt3;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@SuppressWarnings("static-access")
	@BeforeEach
	void setUp() throws Exception 
	{
		localDate1 = "2022-01-25";
		locDt1 = LocalDate.parse(localDate1);
		
		localDate2 = "2022-03-24";
		locDt2 = LocalDate.parse(localDate2);
		
		localDate3 = "2022-05-18";
		locDt3 = LocalDate.parse(localDate3);
		
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

		eventsToFilter.add(event1);
		eventsToFilter.add(event2);
		eventsToFilter.add(event3);
		
		eventsFilter.setEvents(eventsToFilter);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	void testGetJSONObjectEvents() 
	{
		obj = jb.getJSONObjectEvents(eventsToFilter);
		assertEquals(obj.get("number events"), 3);
	}
	
	@SuppressWarnings("static-access")
	@Test
	void testGetJSONObjectStats() throws Exception
	{
		filter = new Filter("New York", "", "2022-01-01,2022-06-01", "", "Basketball");
		filteredEvents = eventsFilter.getFilteredEvents(filter, eventsToFilter);
		obj = jb.getJSONObjectAllStats(filter, filteredEvents);
		
		JSONObject obj1 = (JSONObject) obj.get("general");
		assertEquals(obj1.get("number events"), 1);
	}
}