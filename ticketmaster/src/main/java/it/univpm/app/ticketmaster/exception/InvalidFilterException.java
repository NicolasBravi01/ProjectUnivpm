package it.univpm.app.ticketmaster.exception;

@SuppressWarnings("serial")
public class InvalidFilterException extends Exception 
{
	public InvalidFilterException() 
	{
		super();
	}
	
	public InvalidFilterException(String message) 
	{
		super(message);
	}

}
