package it.univpm.app.ticketmaster.exception;

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