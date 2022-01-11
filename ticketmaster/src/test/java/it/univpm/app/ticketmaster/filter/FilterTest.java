package it.univpm.app.ticketmaster.filter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.exception.NullDateException;

public class FilterTest
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
	
		assertEquals("The first date can't be after the second one", exc.getMessage());
	}
	
	@Test
	void testCorrectdates() 
	{
		String period = "2023-03-02";
		
		NullDateException exc = assertThrows(NullDateException.class, () -> {
			filter = new Filter("", "", period, "", "");
		});
	
		assertEquals("Period not identifed", exc.getMessage());
	}
}