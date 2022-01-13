package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se il JSONObject contenente gli eventi è vuoto
 * 
 * @see it.univpm.app.ticketmaster.controller.EventsController
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class NoEventsException extends Exception 
{

	public NoEventsException()
	{
		super();
	}

	public NoEventsException(String message)
	{
		super(message);
	}
}
