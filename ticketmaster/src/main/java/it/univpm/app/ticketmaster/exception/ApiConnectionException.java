package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata in caso di problemi nella lettura del file apiKey.txt
 * 
 * @see it.univpm.app.ticketmaster.scanner
 * @see it.univpm.app.ticketmaster.controller.EventsController
 * @see it.univpm.app.ticketmaster.controller.StatsController
 * @see it.univpm.app.ticketmaster.controller.ListController
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class ApiConnectionException extends Exception 
{
	
	public ApiConnectionException()
	{
		super();
	}

	public ApiConnectionException(String message)
	{
		super(message);
	}
}