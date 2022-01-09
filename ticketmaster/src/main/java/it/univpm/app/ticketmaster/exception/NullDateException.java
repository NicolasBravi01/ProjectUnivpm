package it.univpm.app.ticketmaster.exception;

@SuppressWarnings("serial")
public class NullDateException extends InvalidPeriodException
{
	
	public NullDateException()
	{
		super();
	}
	
	public NullDateException(String message)
	{
		super(message);
	}
	
}
