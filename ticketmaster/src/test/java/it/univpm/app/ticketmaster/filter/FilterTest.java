package it.univpm.app.ticketmaster.filter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.InvalidNameException;
import it.univpm.app.ticketmaster.exception.NullDateException;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.service.TicketmasterService;

/**
 * Classe che testa la classe Filter
 * 
 * @see it.univpm.app.ticketmaster.filter
 * 
 * @author NicolasBravi01
 * @author sup3r
 */
public class FilterTest
{
	Vector<Event> eventsToFilter = new Vector<Event>();
	Vector<Event> filteredEvents = new Vector<Event>();
	
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
                	"Hockey");

		eventsToFilter.add(event1);
		eventsToFilter.add(event2);
		eventsToFilter.add(event3);
		
		ticketmasterService = new TicketmasterService(eventsToFilter);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test che si accerta del lancio dell'eccezione IncorrectOrderOfDatesException
	 */
	@Test
	void testDatesCorrectOrder() 
	{
		String period1 = "2023-04-04,2022-01-01";
		
		IncorrectOrderOfDatesException exc = assertThrows(IncorrectOrderOfDatesException.class, () -> {
			filter = new Filter("", "", period1, "", "");
			ticketmasterService.check(filter);
		});
	
		assertEquals("The first date can't be after the second one", exc.getMessage());
	}
	
	
	/**
	 * Test che si accerta del lancio dell'eccezione NullDateException
	 */
	@Test
	void testIdentifiedPeriod() 
	{
		String period2 = "2023-03-02";
		
		NullDateException exc = assertThrows(NullDateException.class, () -> {
			filter = new Filter("", "", period2, "", "");
			ticketmasterService.check(filter);
		});
	
		assertEquals("Period not identifed", exc.getMessage());
	}
	
	
	/**
	 * Test che si accerta del lancio dell'eccezione InvalidNameException
	 */
	@Test
	void testValidName() 
	{
		String period3 = "2022-01-01,2022-06-01";
		
		InvalidNameException exc = assertThrows(InvalidNameException.class, () -> {
			filter = new Filter("Italy", "", period3, "", "");
			ticketmasterService.check(filter);
		});
	
		assertEquals("Invalid states'name", exc.getMessage());
	}
	
	
	/**
	 * Test che si accerta del corretto filtraggio degli eventi
	 */
	@Test
	void testFilteredEvents1() throws Exception
	{
		filter = new Filter("New York", "", "2022-01-01,2022-06-01", "", "");
		filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
		assertEquals(filteredEvents.size(), 1);
	}
	
	
	/**
	 * Test che si accerta del corretto filtraggio degli eventi
	 */
	@Test
	void testFilteredEvents2() throws Exception
	{
		filter = new Filter("", "", "2022-01-01,2022-06-01", "", "Basketball");
		filteredEvents = filter.getFilteredEvents(eventsToFilter);
		
		assertEquals(filteredEvents.size(), 2);
	}
}