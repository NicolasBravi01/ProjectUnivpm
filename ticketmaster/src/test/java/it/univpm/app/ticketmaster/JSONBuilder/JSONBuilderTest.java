package it.univpm.app.ticketmaster.JSONBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.JSONHandler.JSONEvents;
import it.univpm.app.ticketmaster.JSONHandler.JSONStats;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.service.TicketmasterService;


/**
 * Classe che testa le classi JSONEvents e JSONStats
 * 
 * @see it.univpm.app.ticketmaster.JSONHandler.JSONEvents
 * @see it.univpm.app.ticketmaster.JSONHandler.JSONStats
 * 
 * @author NicolasBravi01
 * @author sup3r
 */
class JSONBuilderTest 
{
	JSONObject obj = new JSONObject();
	
	Vector<Event> eventsToFilter = new Vector<Event>();
	Vector<Event> filteredEvents = new Vector<Event>();
	
	JSONEvents jE = new JSONEvents();
	JSONStats jS = new JSONStats();
	
	TicketmasterService ticketmasterService;
	Filter filter;
	
	Event event1;
	Event event2;
	Event event3;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception 
	{
		LocalDate locDt1 = LocalDate.parse("2022-01-25");
		LocalDate locDt2 = LocalDate.parse("2022-03-24");
		LocalDate locDt3 = LocalDate.parse("2022-05-18");
		
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
		
		ticketmasterService = new TicketmasterService(eventsToFilter);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test del metodo che restituisce il JSONObject associato alla rotta /events
	 */
	@Test
	void testGetJSONObjectEvents() 
	{
		obj = jE.getJSONObjectEvents(eventsToFilter);
		assertEquals(obj.get("number events"), 3);
	}
	
	/**
	 * Test del metodo che restituisce il JSONObject associato alla rotta /stats
	 */
	@Test
	void testGetJSONObjectStats() throws Exception
	{
		filter = new Filter("New York", "", "2022-01-01,2022-06-01", "", "Basketball");
		filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
		Vector<String> states = ticketmasterService.getStates();
		Vector<String> cities = ticketmasterService.getCities();
		Vector<String> segments = ticketmasterService.getSegments();
		Vector<String> genres = ticketmasterService.getGenres();
		
		obj = jS.getJSONObjectAllStats(filter, filteredEvents, states, cities, segments, genres);
		
		JSONObject obj1 = (JSONObject) obj.get("general");
		assertEquals(obj1.get("number events"), 1);
	}
}
