package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se il JSONObject contenente gli eventi è vuoto
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class NoEventsException extends Exception 
{
	private String message = "There are not events with your filters";
	
	public NoEventsException()
	{
		super();
	}

	public String getMessage() {
		return message;
	}
}
