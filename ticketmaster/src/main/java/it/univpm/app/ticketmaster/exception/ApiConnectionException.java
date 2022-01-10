package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata in caso di problemi nella lettura del file apiKey.txt
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class ApiConnectionException extends Exception 
{
	private String message = "ApiKey not found";
	
	public ApiConnectionException()
	{
		super();
	}

	public String getMessage() {
		return message;
	}
}