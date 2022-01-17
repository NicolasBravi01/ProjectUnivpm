package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se almeno una delle due date non è stata inserita
 * 
 * @see it.univpm.app.ticketmaster.filter
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
@SuppressWarnings("serial")
public class NullDateException extends InvalidFilterException
{

	/**
	 * Costruttore di default
	 */
	public NullDateException()
	{
		super();
	}
	
	/**
	 * Costruttore con messaggio
	 * 
	 * @param message Stringa contenente il messaggio di errore
	 */
	public NullDateException(String message)
	{
		super(message);	}

}
