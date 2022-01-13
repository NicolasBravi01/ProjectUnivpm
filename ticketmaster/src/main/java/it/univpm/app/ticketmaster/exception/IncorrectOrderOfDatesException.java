package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se le date nella stringa periodo sono state inserite in ordine non cronologico
 * 
 * @see it.univpm.app.ticketmaster.filter.Filter
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class IncorrectOrderOfDatesException extends InvalidFilterException 
{

	public IncorrectOrderOfDatesException() 
	{
		super();
	}
	
	public IncorrectOrderOfDatesException(String message) 
	{
		super(message);
	}

}

	
	
	