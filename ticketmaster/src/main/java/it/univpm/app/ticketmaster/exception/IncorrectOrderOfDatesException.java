package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se le date nella stringa periodo sono state inserite in ordine non cronologico
 * 
 * @see it.univpm.app.ticketmaster.filter
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
@SuppressWarnings("serial")
public class IncorrectOrderOfDatesException extends InvalidFilterException 
{
	/**
	 * Costruttore di default
	 */
	public IncorrectOrderOfDatesException() 
	{
		super();
	}
	
	/**
	 * Costruttore con messaggio
	 * 
	 * @param message Stringa contenente il messaggio di errore
	 */
	public IncorrectOrderOfDatesException(String message) 
	{
		super(message);
	}

}

	
	
	