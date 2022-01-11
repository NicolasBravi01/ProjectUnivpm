package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se uno dei nomi inseriti dall'utente non è valido
 * 
 * @author sup3r
 */
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
