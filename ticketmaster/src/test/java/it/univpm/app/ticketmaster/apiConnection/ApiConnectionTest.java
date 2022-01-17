package it.univpm.app.ticketmaster.apiConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.app.ticketmaster.model.Event;

/**
 * Classe che testa la classe TicketmasterConnection
 * 
 * @see it.univpm.app.ticketmaster.apiConnection
 * 
 * @author NicolasBravi01
 * @author sup3r
 */
class ApiConnectionTest 
{
	TicketmasterConnection ticketmasterConnection = new TicketmasterConnection();
	Vector<Event> events = new Vector<Event>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test del metodo callEvents, che si occupa di restituire la lista di tutti gli eventi
	 * a seguito della connessione con l'api di Ticketmaster
	 */
	@Test
	void CallEventsTest() 
	{
		events = ticketmasterConnection.callEvents();
		assertEquals(events.size(),200);
	}

}
