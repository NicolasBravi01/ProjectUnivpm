package it.univpm.app.ticketmaster.exception;

/**
 * Eccezione generata se uno dei nomi inseriti dall'utente non è valido
 * 
 * @see it.univpm.app.ticketmaster.service
 * 
 * @author sup3r
 * @autor NicolasBravi01
 */
@SuppressWarnings("serial")
public class InvalidNameException extends InvalidFilterException
{
	/**
	 * Costruttore di default
	 */
	public InvalidNameException()
	{
		super();
	}

	/**
	 * Costruttore con messaggio
	 * 
	 * @param message Stringa contenente il messaggio di errore
	 */
	public InvalidNameException(String message)
	{
		super(message);
	}
}
