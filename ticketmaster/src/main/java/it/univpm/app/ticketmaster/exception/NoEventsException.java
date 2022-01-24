package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se il JSONObject contenente gli eventi è vuoto
 * 
 * @see it.univpm.app.ticketmaster.controller.EventsController
 * 
 * @author sup3r
 * @author NicolasBravi01
 */
@SuppressWarnings("serial")
public class NoEventsException extends Exception 
{

	/**
	 * Costruttore di default
	 */
	public NoEventsException()
	{
		super();
	}

	/**
	 * Costruttore con messaggio
	 * 
	 * @param message Stringa contenente il messaggio di errore
	 */
	public NoEventsException(String message)
	{
		super(message);
	}
}
