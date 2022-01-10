package it.univpm.app.ticketmaster.exception;


/**
 * Eccezione generata se le date nella stringa periodo sono state inserite in ordine non cronologico
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class IncorrectOrderOfDatesException extends InvalidPeriodException 
{
	//private String msg = "ERROR: dates in incorrect order";

	public IncorrectOrderOfDatesException() {
		super();
	}

	public IncorrectOrderOfDatesException(String msg) {
		super(msg);
	}

}

	
	
	