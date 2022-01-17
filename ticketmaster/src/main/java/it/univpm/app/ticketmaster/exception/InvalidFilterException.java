package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se il filtro inserito non è valido
 * 
 * @see it.univpm.app.ticketmaster.exception.IncorrectOrderOfDatesException
 * @see it.univpm.app.ticketmaster.exception.InvalidNameException
 * @see it.univpm.app.ticketmaster.exception.NullDateException
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
@SuppressWarnings("serial")
public class InvalidFilterException extends Exception 
{
	/**
	 * Costruttore di default
	 */
	public InvalidFilterException() 
	{
		super();
	}
	
	/**
	 * Costruttore con messaggio
	 * 
	 * @param message Stringa contenente il messaggio di errore
	 */
	public InvalidFilterException(String message) 
	{
		super(message);
	}

}
