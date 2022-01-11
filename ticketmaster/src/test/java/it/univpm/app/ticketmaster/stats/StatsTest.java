package it.univpm.app.ticketmaster.stats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatsTest 
{
	Stats stats = new Stats();;
	
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
	void testCorrectAverage() 
	{
		int numberEvents = 1;
		String period = "2022-01-24,2022-01-24";
		double av = stats.average(numberEvents, period);
	
		assertEquals(av, 30);
	}

}
