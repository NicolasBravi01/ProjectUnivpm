package it.univpm.app.ticketmaster.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.filter.EventsFilter;
import it.univpm.app.ticketmaster.filter.FilterImpl;
import it.univpm.app.ticketmaster.model.Event;
import it.univpm.app.ticketmaster.stats.Stats;

class TicketmasterTest 
{
	/*
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		
	}
	*/

	Stats stats = new Stats();
	FilterImpl filter = new FilterImpl();
	Vector<Event> events = EventsFilter.getEvents();
	
	@BeforeEach
	void setUp() throws ArrayIndexOutOfBoundsException 
	{
		try
		{
			int [] vetEventsStates = stats.getArrayStatsPerStates(filter);
			int monthMinStates = stats.minValueIndex(vetEventsStates);
			
			int [] vetEventsCities = stats.getArrayStatsPerCities(filter);
			int monthMinCities = stats.minValueIndex(vetEventsCities);
			
			int [] vetEventsSegments = stats.getArrayStatsPerSegments(filter);
			int monthMinSegments = stats.minValueIndex(vetEventsSegments);
			
			int [] vetEventsGenres = stats.getArrayStatsPerStates(filter);
			int monthMinGenres = stats.minValueIndex(vetEventsGenres);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array vuoto");
		}
		
	}

	@AfterEach
	void tearDown() throws Exception 
	{
	
	}

	@Test
	void testMinEventsStatesEqualsZero() 
	{
		assertEquals(stats.min(events), 0 + ", in " + stats.monthToString(monthMinStates));
	}
	
	@Test
	void testMinEventsCitiesEqualsZero() 
	{
		assertEquals(stats.min(events), 0 + ", in " + stats.monthToString(monthMinCities));
	}
	
	@Test
	void testMinEventsSegmentsEqualsZero() 
	{
		assertEquals(stats.min(events), 0 + ", in " + stats.monthToString(monthMinCities));
	}
	
	@Test
	void testMinEventsGenresEqualsZero() 
	{
		assertEquals(stats.min(events), 0 + ", in " + stats.monthToString(monthMinCities));
	}

}
