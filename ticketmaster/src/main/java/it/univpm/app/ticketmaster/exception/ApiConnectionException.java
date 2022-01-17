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
 * @autor NicolasBravi01
 */
@SuppressWarnings("serial")
public class ApiConnectionException extends Exception 
{
	/**
	 * Costruttore di default
	 */
	public ApiConnectionException()
	{
		super();
	}

	/**
	 * Costruttore con messaggio
	 * 
	 * @param message Stringa contenente il messaggio di errore
	 */
	public ApiConnectionException(String message)
	{
		super(message);
	}
}