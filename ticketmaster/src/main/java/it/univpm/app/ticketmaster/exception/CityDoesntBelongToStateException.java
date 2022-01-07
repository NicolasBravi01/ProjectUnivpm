package it.univpm.app.ticketmaster.exception;

import java.util.Arrays;

/**
 * Eccezione generata se almeno una città inserita non appartiene ad uno degli stati inseriti
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class CityDoesntBelongToStateException extends Exception 
{
	private String msg = "ERROR: The city doesn't belong to the state";

	public CityDoesntBelongToStateException() {
		super();
	}

	public CityDoesntBelongToStateException(String msg) {
		super(msg);
		this.msg=msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	public String getMsg(String msg){
		this.msg = msg;
		return this.msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

