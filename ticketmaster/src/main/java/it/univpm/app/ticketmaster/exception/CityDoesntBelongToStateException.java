package it.univpm.app.ticketmaster.exception;


/**
 * Eccezione generata se almeno una città inserita non appartiene ad uno degli stati inseriti
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class CityDoesntBelongToStateException extends Exception 
{
	//private String msg = "ERROR: The city doesn't belong to the state";

	public CityDoesntBelongToStateException() {
		super();
	}

	public CityDoesntBelongToStateException(String msg) {
		super(msg);
	}
	
	
}

