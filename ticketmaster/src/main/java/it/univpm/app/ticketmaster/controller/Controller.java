package it.univpm.app.ticketmaster.controller;

import it.univpm.app.ticketmaster.apiConnection.TicketmasterConnection;
import it.univpm.app.ticketmaster.service.TicketmasterService;

/**
 * Classe dalla quale ereditano gli altri controller che ha il compito di gestire le informazioni
 * 
 * @see it.univpm.app.ticketmaster.apiConnection
 * @see it.univpm.app.ticketmaster.controller.EventsController
 * @see it.univpm.app.ticketmaster.controller.StatsController
 * @see it.univpm.app.ticketmaster.controller.ListController
 * 
 * @author sup3r
 */
public class Controller 
{
	/**
	 * Variabile che contiene la lista degli eventi e i dati necessari per gestire le rotte
	 */
	TicketmasterService ticketmasterService;
	
	/**
	 * Costruttore della classe Controller. 
	 * Effettua la connessione con l'Api di Ticketmaster, da cui riceve la lista di tutti gli eventi
	 */
	public Controller()
	{
		TicketmasterConnection connection = new TicketmasterConnection();
		ticketmasterService = new TicketmasterService(connection.callEvents());
	}
	
	/**
	 * Getter della variabile ticketmasterService
	 * 
	 * @return ticketmasterService
	 */
	public TicketmasterService getTicketmasterService()
	{
		return ticketmasterService;
	}
	
}