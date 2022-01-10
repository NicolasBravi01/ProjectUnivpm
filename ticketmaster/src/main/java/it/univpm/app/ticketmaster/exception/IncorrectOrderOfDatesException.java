package it.univpm.app.ticketmaster.exception;


/**
 * Eccezione generata se le date nella stringa periodo sono state inserite in ordine non cronologico
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class IncorrectOrderOfDatesException extends InvalidPeriodException 
{
	private String message = "The first date can't be after the second one";

	public IncorrectOrderOfDatesException() 
	{
		super();
	}
	
	public String getMessage() {
		return message;
	}

	

}

	
	
	