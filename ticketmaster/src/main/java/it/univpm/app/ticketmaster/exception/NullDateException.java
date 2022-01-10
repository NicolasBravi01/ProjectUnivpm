package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se almeno una delle due date non è stata inserita correttamente
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class NullDateException extends InvalidFilterException
{

	public NullDateException()
	{
		super();
	}
	
	public NullDateException(String message)
	{
		super(message);	}

}
