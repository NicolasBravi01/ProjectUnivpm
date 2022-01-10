package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se almeno una delle due date non è stata inserita correttamente
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class NullDateException extends InvalidPeriodException
{
	private String message = "Period not identifed";
	
	public NullDateException()
	{
		super();
	}

	public String getMessage() {
		return message;
	}
}
