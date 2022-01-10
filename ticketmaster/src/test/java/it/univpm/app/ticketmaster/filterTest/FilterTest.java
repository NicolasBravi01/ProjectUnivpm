package it.univpm.app.ticketmaster.filterTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException;
import it.univpm.app.ticketmaster.filter.Filter;

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