package it.univpm.app.ticketmaster.exception;

import org.json.simple.JSONObject;

public class NullApiKeyException extends Exception 
{
	
	public NullApiKeyException()
	{
		super();
	}
	
	public NullApiKeyException(String message)
	{
		super(message);
	}

	
}