package it.univpm.app.ticketmaster.stats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatsTest 
{
	Stats stats = new Stats();

	@Test
	void testCorrectAverage() 
	{
		int numberEvents = 1;
		String period = "2022-01-24,2022-01-24";
		double av = stats.average(numberEvents, period);
	
		assertEquals(av, 30);
	}
}
