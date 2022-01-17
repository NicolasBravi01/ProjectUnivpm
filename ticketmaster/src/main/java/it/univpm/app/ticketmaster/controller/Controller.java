package it.univpm.app.ticketmaster.controller;

import it.univpm.app.ticketmaster.apiConnection.TicketmasterConnection;
import it.univpm.app.ticketmaster.service.TicketmasterService;

public class Controller 
{
	TicketmasterService ticketmasterService;
	
	
	public Controller()
	{
		TicketmasterConnection connection = new TicketmasterConnection();
		ticketmasterService = new TicketmasterService(connection.callEvents());
	}
	
	
	public TicketmasterService getTicketmasterService()
	{
		return ticketmasterService;
	}
	
	
	

}