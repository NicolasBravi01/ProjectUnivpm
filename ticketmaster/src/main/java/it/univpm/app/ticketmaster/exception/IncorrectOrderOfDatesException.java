package it.univpm.app.ticketmaster.exception;

import java.util.Arrays;

/**
 * Eccezione generata se le date nella stringa periodo sono state inserite in ordine non cronologico
 * 
 * @author sup3r
 */
@SuppressWarnings("serial")
public class IncorrectOrderOfDatesException extends Exception 
{
	private String msg = "ERROR: dates in incorrect order";

	public IncorrectOrderOfDatesException() {
		super();
	}

	public IncorrectOrderOfDatesException(String msg) {
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

	
	
	