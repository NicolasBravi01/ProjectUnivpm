package it.univpm.app.ticketmaster.exception;

@SuppressWarnings("serial")
public class InvalidNameException extends InvalidFilterException
{
	public InvalidNameException()
	{
		super();
	}

	public InvalidNameException(String message)
	{
		super(message);
	}
}
