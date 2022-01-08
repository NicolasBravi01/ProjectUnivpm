package it.univpm.app.ticketmaster.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.TicketmasterApplication;
import it.univpm.app.ticketmaster.apiConnection.TicketmasterConnection;
import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.Filter;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.stats.Stats;

class TicketmasterTest 
{
	Filter filter;
	
	/*
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		
	}

	@BeforeEach
	void setUp() throws Exception 
	{
		
	}

	@AfterEach
	void tearDown() throws Exception 
	{
	
	}
	*/

	@Test
	void testDatesCorrectOrder() 
	{
		String period = "2023-04-04,2022-01-01";
		
		IncorrectOrderOfDatesException exc = assertThrows(IncorrectOrderOfDatesException.class, () -> {
			filter = new Filter("", "", period, "", "");
		});
	
		//assertEquals("ERROR: dates in incorrect order", exc.getMsg());
	}
	
	@Test
	void testCityBelongsToState() 
	{
		/*
		String period = "2023-04-04,2022-01-01";
		
		IncorrectOrderOfDatesException exc = assertThrows(IncorrectOrderOfDatesException.class, () -> {
			filter = new FilterImpl("", "", period, "", "");
		});
	
		assertEquals("ERROR: dates in incorrect order", exc.getMsg());
		*/
	}

	
	

}
