package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata in caso di problemi nella lettura del file apiKey.txt
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