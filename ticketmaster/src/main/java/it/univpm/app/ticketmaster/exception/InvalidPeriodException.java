package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se ci sono errori nell'inserimento del periodo
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class InvalidPeriodException extends Exception
{
	
	public InvalidPeriodException()
	{
		super();
	}
	
	public InvalidPeriodException(String message)
	{
		super(message);
	}
	
}
