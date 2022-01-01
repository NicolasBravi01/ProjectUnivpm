package it.univpm.app.ticketmaster.exception;

public class Error 
{
	String cause;
	String message;
	
	
	public Error(String cause, String message)
	{
		this.cause = cause;
		this.message = message;
	}
	
	
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
