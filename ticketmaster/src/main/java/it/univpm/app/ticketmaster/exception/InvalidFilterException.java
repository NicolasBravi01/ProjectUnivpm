package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se il filtro inserito non è valido
 * 
 * @see it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException
 * @see it.univpm.app.ticketmaster.exception.InvalidNameException
 * @see it.univpm.app.ticketmaster.exception.NullDateException
 * 
 * @author sup3r
 */
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
